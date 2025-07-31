package Base_Class;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;

import com.mysql.jdbc.Driver;

import GenericUtilites.DataBaseUtility;
import GenericUtilites.PropertyFileUtility;
import GenericUtilites.WebDriverUtility;
import GenericUtilities.DatabaseUtility;
import POMUtilities.HomePomPage;
import POMUtilities.LoginPomPage;

public class Base_Class {
	DatabaseUtility dbutil = new DatabaseUtility();
	Connection con;
	public WebDriver driver;
	public static WebDriver sDriver = null;
	GenericUtilities.PropertyFileUtility p = new GenericUtilities.PropertyFileUtility();
	//public WebDriverUtility wbutil = null;
	public GenericUtilities.WebDriverUtility wbutil = new GenericUtilities.WebDriverUtility();
	
	
	
	@BeforeSuite
	public void connectToDatabase() throws Exception {
		con=dbutil.getDatabaseConnection();
		
		
		
	}
	
	@BeforeTest
	public void conParallel() {
		System.out.println("Configure parllel");
		
		
	}
	@BeforeClass
	public void launchBrowser() throws IOException {
		
		GenericUtilities.PropertyFileUtility p = new GenericUtilities.PropertyFileUtility();
		String Browser = p.fetchDataFromPropFile("browser");
		if(Browser.equals("chrome")) {
			driver = new ChromeDriver();
			
		}
		else if(Browser.equals("edge")) {
			driver = new EdgeDriver();
		}
		else {
			driver = new ChromeDriver();
		}
		
		sDriver=driver;
	}
	 
	@BeforeMethod
	public void login() throws IOException {
		wbutil.maximizetheWindow(driver);
		String time = p.fetchDataFromPropFile("timeouts");
		//wbutil.implcitWaitStmt(driver, time);
		wbutil.waitUntilElementIsFound(driver, time);
		String url = p.fetchDataFromPropFile("url");
		wbutil.navigateToApplication(driver, url);
		String username = p.fetchDataFromPropFile("username");
		String password=p.fetchDataFromPropFile("password");
		LoginPomPage login = new LoginPomPage(driver);
		login.login(username, password);
		
		
		
	}
	@AfterMethod
	public void logOut() {
	
		HomePomPage home = new HomePomPage(driver);
		home.logout(driver);

	}
	@AfterClass
	public void closeBrowser() {
		wbutil.quitTheBrowser(driver);;
		
	}
	@AfterTest
	public void closeConParallel() {
		
		System.out.println("Close conf parellel connection");
		
	}
	@AfterSuite
	public void closeDatabaseConnection() throws SQLException {
		
		con.close();
		
		
		
	}
	
	

	
	
	
	

}

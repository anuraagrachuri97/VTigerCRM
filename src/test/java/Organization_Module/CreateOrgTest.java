package Organization_Module;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.Test;

import GenericUtilities.ExcelFileUtility;
import GenericUtilities.JavaUtility;
import GenericUtilities.PropertyFileUtility;
import GenericUtilities.WebDriverUtility;
import POMUtilities.LoginPomPage;

public class CreateOrgTest {
	
	@Test(groups = {"smoke"})
	public void createNewOrgTest() throws IOException, InterruptedException {
		
		/*FileInputStream fis=new FileInputStream("./src/test/resources/V_Tiger.properties");
		Properties p=new Properties();
		p.load(fis);
		//String Browser = p.getProperty("browser");
		String Url = p.getProperty("url");
		String Uname = p.getProperty("username");
		String Password = p.getProperty("password");
		String Time = p.getProperty("timeouts");*/
		// properties
		PropertyFileUtility p = new PropertyFileUtility();
		String browser = p.fetchDataFromPropFile("browser");
		String url = p.fetchDataFromPropFile("url");
		String username = p.fetchDataFromPropFile("username");
		String password = p.fetchDataFromPropFile("password");
		String Time = p.fetchDataFromPropFile("timeouts");
		JavaUtility jutil=new JavaUtility();
		int randomNum = jutil.generateRandomNumber();
		
		/*Fetching the data from the Excel File
		
		FileInputStream fisx=new FileInputStream("./src/test/resources/VTiger_OCData.xlsx");
		Workbook wb = WorkbookFactory.create(fisx);
		String org_name = wb.getSheet("Org_Data").getRow(1).getCell(2).toString();*/
		
		// Excel
		ExcelFileUtility exutil = new ExcelFileUtility();
		String Org=exutil.fetchDataFromExcelFile("Org_Data", 1, 2)+randomNum;
		exutil.closeExcelFile();
		
		WebDriver driver=new ChromeDriver();
		WebDriverUtility wutil=new WebDriverUtility();
		wutil.maximizetheWindow(driver);
		//long time = Long.parseLong(Time);
		//driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(time));
		wutil.waitUntilElementIsFound(driver, Time);
		
		//driver.get(Url);
		wutil.navigateToApplication(driver, url);
		/*driver.findElement(By.name("user_name")).sendKeys(username);
		driver.findElement(By.name("user_password")).sendKeys(password);
		driver.findElement(By.id("submitButton")).click();*/
		LoginPomPage lp=new LoginPomPage(driver);
		lp.login(username, password);
		driver.findElement(By.linkText("Organizations")).click();
		driver.findElement(By.xpath("//img[@title=\"Create Organization...\"]")).click();
		driver.findElement(By.name("accountname")).sendKeys(Org);
		driver.findElement(By.name("button")).click();
		WebElement header = driver.findElement(By.xpath("//span[contains(text(),'Organization Information')]"));
		if((header).getText().contains(Org)){
			System.out.println("Organization Created Succesfully");
			}
		else
		{
			System.out.println("Organization Creation Failed");
		}
		
		driver.findElement(By.linkText("Organizations")).click();
		
		driver.findElement(By.xpath("//a[text()='"+ Org +"']/ancestor::tr[@bgcolor=\"white\"]/descendant::a[text()='del']")).click();
		Thread.sleep(3000);
		//Alert al = driver.switchTo().alert();
		//al.accept();
		wutil.HandleAlertPopupAndClickOK(driver);
		WebElement admin = driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']"));
		wutil.mouseOverOnAnElement(driver, admin);
		//Actions act=new Actions(driver);
		//act.moveToElement(admin).perform();
		driver.findElement(By.xpath("//a[text()='Sign Out']")).click();
		//driver.quit();
		wutil.quitTheBrowser(driver);
		
		
		//driver.close();
		
		
	}

}

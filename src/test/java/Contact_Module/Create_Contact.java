package Contact_Module;

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

public class Create_Contact {
	
	@Test
	public void createCont() throws IOException {
		
		FileInputStream fis=new FileInputStream("./src/test/resources/V_Tiger.properties");
		Properties p=new Properties();
		p.load(fis);
		String Browser = p.getProperty("browser");
		String Url = p.getProperty("url");
		String Uname = p.getProperty("username");
		String Password = p.getProperty("password");
		String Time = p.getProperty("timeouts");
		//Fetching Data from Excel File
		FileInputStream fisx=new FileInputStream("./src/test/resources/VTiger_OCData.xlsx");
		Workbook wb = WorkbookFactory.create(fisx);
		String cont = wb.getSheet("Contact_Data").getRow(1).getCell(2).toString();
		
		WebDriver driver=new ChromeDriver();
		driver.manage().window().maximize();
		long time = Long.parseLong(Time);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(time));
		driver.get(Url);
		driver.findElement(By.name("user_name")).sendKeys(Uname);
		driver.findElement(By.name("user_password")).sendKeys(Password);
		driver.findElement(By.id("submitButton")).click();
		driver.findElement(By.linkText("Contacts")).click();
		driver.findElement(By.xpath("//img[@title='Create Contact...']")).click();
		driver.findElement(By.name("lastname")).sendKeys(cont);
		driver.findElement(By.name("button")).click();
		
	   WebElement c_header = driver.findElement(By.xpath("//span[contains(text(),'Contact Information')]"));
	   
    if(c_header.getText().contains(cont)){
			
			System.out.println("Contact Created Successfully");		
		}
		else {
			System.out.println("Contact Creation Failed");
		}
		
		driver.findElement(By.linkText("Contacts")).click();
		
		driver.findElement(By.xpath("//a[text()='"+ cont +"']/ancestor::tr[@bgcolor=\"white\"]/descendant::a[text()='del']")).click();
		
		Alert al = driver.switchTo().alert();
		al.accept();
		WebElement prof = driver.findElement(By.xpath("//img[@src=\"themes/softed/images/user.PNG\"]"));
		Actions act=new Actions(driver);
		act.moveToElement(prof).perform();
		driver.findElement(By.xpath("//a[text()='Sign Out']")).click();
		driver.quit();
		
	}

}

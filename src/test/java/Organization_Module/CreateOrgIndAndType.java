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
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Test;

public class CreateOrgIndAndType {
	
	@Test
	public void createNewOrgIndAndType() throws IOException, InterruptedException
	{
		//Fetching the data from the Property File
		FileInputStream fis=new FileInputStream("./src/test/resources/V_Tiger.properties");
		Properties p=new Properties();
		p.load(fis);
		String Browser = p.getProperty("browser");
		String Url = p.getProperty("url");
		String Uname = p.getProperty("username");
		String Password = p.getProperty("password");
		String Time = p.getProperty("timeouts");
		
		//Fetching the data from the Excel File
		
        FileInputStream fise=new FileInputStream("./src/test/resources/VTiger_OCData.xlsx");
		Workbook wb = WorkbookFactory.create(fise);
		String org = wb.getSheet("Org_Data").getRow(9).getCell(2).toString();
		String ind = wb.getSheet("Org_Data").getRow(9).getCell(3).toString();
		String type = wb.getSheet("Org_Data").getRow(9).getCell(4).toString();
		
		WebDriver driver=new ChromeDriver();
		driver.manage().window().maximize();
		//Converting String into Long Data Type
		long time = Long.parseLong(Time);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(time));
		
		driver.get(Url);
		driver.findElement(By.name("user_name")).sendKeys(Uname);
		driver.findElement(By.name("user_password")).sendKeys(Password);
		driver.findElement(By.id("submitButton")).click();
		driver.findElement(By.linkText("Organizations")).click();
		driver.findElement(By.xpath("//img[@title='Create Organization...']")).click();
		driver.findElement(By.name("accountname")).sendKeys(org);
	    //Handling Industry Drop Down
		WebElement ind_dd = driver.findElement(By.name("industry"));
		Select s=new Select(ind_dd);
		s.selectByValue(ind);
		//Handling Type Drop Down
		WebElement type_dd = driver.findElement(By.name("accounttype"));
		Select s1=new Select(type_dd);
		s1.selectByValue(type);
		driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();
		//Validations
		WebElement header = driver.findElement(By.xpath("//span[contains(text(),'Organization Information')]"));
		if((header).getText().contains(org)){
			System.out.println("Organization Created Succesfully");
			}
		else
		{
			System.out.println("Organization Creation Failed");
		}
		
		WebElement ind_v = driver.findElement(By.id("mouseArea_Industry"));
		if(ind_v.getText().contains(ind)) {
			System.out.println("Industry type Validation Successfull");
		}
		else {
			System.out.println("Industry type Validation Unsuccessfull");
		}
		
		WebElement type_v = driver.findElement(By.id("mouseArea_Type"));
		if(type_v.getText().contains(type))
		{
			System.out.println("Type Validation Successfull");
		}
		else
		{
			System.out.println("Type Validation Unsuccessfull");
		}
		
		driver.findElement(By.linkText("Organizations")).click();
		
		driver.findElement(By.xpath("//a[text()='"+ org +"']/ancestor::tr[@bgcolor=\"white\"]/descendant::a[text()='del']")).click();
		Thread.sleep(3000);
		Alert al = driver.switchTo().alert();
		al.accept();
		WebElement admin = driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']"));
		Actions act=new Actions(driver);
		act.moveToElement(admin).perform();
		driver.findElement(By.xpath("//a[text()='Sign Out']")).click();
		driver.quit();
		
		
	}

}

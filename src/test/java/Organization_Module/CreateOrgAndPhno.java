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

public class CreateOrgAndPhno {
	
	@Test
	public void createOrgPhno() throws IOException, InterruptedException {
		
		FileInputStream fis=new FileInputStream("./src/test/resources/V_Tiger.properties");
		Properties p=new Properties();
		p.load(fis);
		//String Browser = p.getProperty("browser");
		String Url = p.getProperty("url");
		String Uname = p.getProperty("username");
		String Password = p.getProperty("password");
		String Time = p.getProperty("timeouts");
		
		//Fetching the data from the Excel File
		
		FileInputStream fisx=new FileInputStream("./src/test/resources/VTiger_OCData.xlsx");
		Workbook wb = WorkbookFactory.create(fisx);
		String org_name = wb.getSheet("Org_Data").getRow(5).getCell(2).toString();
		String pno = wb.getSheet("Org_Data").getRow(5).getCell(3).toString();
		
		WebDriver driver=new ChromeDriver();
		driver.manage().window().maximize();
		long time = Long.parseLong(Time);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(time));
		driver.get(Url);
		driver.findElement(By.name("user_name")).sendKeys(Uname);
		driver.findElement(By.name("user_password")).sendKeys(Password);
		driver.findElement(By.id("submitButton")).click();
		driver.findElement(By.linkText("Organizations")).click();
		driver.findElement(By.xpath("//img[@title=\"Create Organization...\"]")).click();
		driver.findElement(By.name("accountname")).sendKeys(org_name);
		driver.findElement(By.id("phone")).sendKeys(pno);
		driver.findElement(By.name("button")).click();
		WebElement header = driver.findElement(By.xpath("//span[contains(text(),'Organization Information')]"));
		if((header).getText().contains(org_name)){
			System.out.println("Organization Created Succesfully");
			}
		else
		{
			System.out.println("Organization Creation Failed");
		}
		//Validate Phone Number
		WebElement pnv = driver.findElement(By.id("mouseArea_Phone"));
		
		if(pnv.getText().contains(pno)) {
			
			System.out.println("Phone Number Created Succesfully");
		}
		
		else {
			
			System.out.println("Phone Number Creation Failed");
			
		}
		driver.findElement(By.linkText("Organizations")).click();
		
		driver.findElement(By.xpath("//a[text()='"+ org_name +"']/ancestor::tr[@bgcolor=\"white\"]/descendant::a[text()='del']")).click();
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



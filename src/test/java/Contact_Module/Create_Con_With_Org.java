package Contact_Module;

import java.io.FileInputStream;

import java.io.IOException;
import java.text.DecimalFormat;
import java.time.Duration;
import java.util.Properties;
import java.util.Set;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
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

public class Create_Con_With_Org {

	@Test
	public void createContactModule_3rd() throws IOException, InterruptedException {

		// *******************************************//

		// Fetch the Common Data From the Properties File
		FileInputStream fis = new FileInputStream("./src/test/resources/VTiger_Commondata.properties");

		// Creating a Java Object Object File
		Properties p = new Properties();

		// Loading all the data From fis file to p java object
		p.load(fis);
		fis.close();

		// *******************************************//

		// Fetching Contact Excel File

		// Convert the physical file into java object
		FileInputStream fis_exl = new FileInputStream("./src/test/resources/VTiger.xlsx");

		// Creating a dummy workbook
		Workbook wb = WorkbookFactory.create(fis_exl);

		// Fetching the Sheet from the Physical file
		Sheet sh_con = wb.getSheet("Contact_Data");

		// Fetch the Row
		Row r_con = sh_con.getRow(7);

		// Fetch the Cell
		Cell c2 = r_con.getCell(2);

		Cell c3 = r_con.getCell(3);

		Cell c4 = r_con.getCell(4);

		// Fetch the data
		String con_Name = c2.toString();

		String last_Name = c3.toString();

		String gender = c4.toString();

		// *******************************************//
		// Fetching Organization Execel workBook

		// Fetch the Sheet
		Sheet sh_org = wb.getSheet("Org_Data");

		// Fetch the Row
		Row r_org = sh_org.getRow(4);

		// Fetch the Cell
		Cell c2_org = r_org.getCell(2);

		// Fetch the Cell
		Cell c3_org = r_org.getCell(3);

		// Fetch the data
		String orgName = c2_org.toString();

		// Fetch the data
		double phno1 = c3_org.getNumericCellValue();

		// No decimal point
		DecimalFormat df = new DecimalFormat("0");
		String phno = df.format(phno1);

		// *******************************************//

		// Open The Browser
		WebDriver driver = new ChromeDriver();

		// Maximize the Browser
		driver.manage().window().maximize();

		String timeouts = p.getProperty("timeouts");

		int timeout = Integer.parseInt(timeouts);

		// Implicit wait
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(timeout));

		// Navigate to an application
		driver.get(p.getProperty("url"));

		// Identify Username
		driver.findElement(By.xpath("//input[@name='user_name']")).sendKeys(p.getProperty("username"));

		// Indentify Password
		driver.findElement(By.xpath("//input[@name='user_password']")).sendKeys(p.getProperty("password"));

		// Click on login
		driver.findElement(By.xpath("//input[@id='submitButton']")).click();

		// *******************************************//
		// Creating a Organization

		// Identify organization link and click
		driver.findElement(By.linkText("Organizations")).click();

		// Identify create plus icon n click
		driver.findElement(By.xpath("//img[@title=\"Create Organization...\"]")).click();

		// click on org name textfield enter data
		driver.findElement(By.name("accountname")).sendKeys(orgName);

		// Click on Phone Number textfield to enter the data
		driver.findElement(By.xpath("//input[@id='phone']")).sendKeys(phno);

		// identify save button and click
		driver.findElement(By.xpath("//input[@title=\"Save [Alt+S]\"]")).click();

		// identify header in org info page and validate
		WebElement header = driver.findElement(By.xpath("//span[contains(text(),'Organization Information')]"));

		// identify phno
		WebElement verifyphno = driver.findElement(By.id("mouseArea_Phone"));

		if (header.getText().contains(orgName) && verifyphno.getText().contains(phno)) {
			System.out.println("create Org with phno Verified OrgName and phno test pass");

		} else {

			System.out.println("create Org with phno Verified OrgName and phno test pass");

		}

		// Identify organization link and click
		driver.findElement(By.linkText("Organizations")).click();

		// *******************************************//

		// Identify organization link and click

		driver.findElement(By.linkText("Contacts")).click();

		// Identify create plus icon n click
		driver.findElement(By.xpath("//img[@title='Create Contact...']")).click();

		// *******************************************//

		// Industry dropdown
		WebElement mr = driver.findElement(By.xpath("//select[@name='salutationtype']"));
		mr.click();
		Select dropmr = new Select(mr);
		dropmr.selectByValue(gender);

		// click on org name textfield enter data
		driver.findElement(By.xpath("//input[@name='firstname']")).sendKeys(con_Name);

		// Click on Phone Number textfield to enter the data
		driver.findElement(By.xpath("//input[@name='lastname']")).sendKeys(last_Name);

		// *******************************************//

		// Store the main window handle
		String mainWindow = driver.getWindowHandle();

		WebElement org = driver.findElement(By.xpath("//tbody/tr[5]/td[2]/img[1]"));
		org.click();

		// Get all window handles
		Set<String> allWindows = driver.getWindowHandles();

		// Switch to the new window
		for (String window : allWindows) {
			if (!window.equals(mainWindow)) {
				driver.switchTo().window(window);
				break;
			}
		}

		driver.findElement(By.xpath("//input[@id='search_txt']")).sendKeys(orgName);

		driver.findElement(By.xpath("//input[@name='search']")).click();

		WebElement org_drop = driver.findElement(By.xpath("//a[text()='" + orgName + "']"));
		org_drop.click();

		Thread.sleep(3000);

		driver.switchTo().window(mainWindow);

		// Click on Phone Number textfield to enter the data
		driver.findElement(By.xpath("//input[@class='crmButton small save']")).click();

		// *******************************************//

		// identify ContactName in Contact info page and validate
		WebElement ContactName = driver.findElement(By.xpath("//span[@id='dtlview_First Name']"));

		// identify LastName in Contact info page and validate
		WebElement lastName = driver.findElement(By.xpath("//span[@id='dtlview_Last Name']"));

		// identify Organization in Contact info page and validate
		WebElement organizationName = driver
				.findElement(By.xpath("//td[@id='mouseArea_Organization Name']//a[contains(text(),'Qsp')]"));

		if (ContactName.getText().contains(con_Name)) {
			System.out.println("create Contact with Contact Name test pass");

		} else {

			System.out.println("create Contact with Contact Name test fail");

		}

		if (lastName.getText().contains(last_Name)) {
			System.out.println("create Contact with Contact Name test pass");

		} else {

			System.out.println("create Contact with Contact Name test fail");

		}

		if (organizationName.getText().contains(orgName)) {
			System.out.println("create Contact with Contact Name test pass");

		} else {

			System.out.println("create Contact with Contact Name test fail");

		}

		driver.findElement(By.linkText("Contacts")).click();

		// *******************************************//

		// Delete Contact

		driver.findElement(
				By.xpath("//a[text()='" + con_Name + "']/ancestor::tr[@bgcolor=\"white\"]/descendant::a[text()='del']"))
				.click();

		// Handle delete pop up
		Alert a1 = driver.switchTo().alert();

		a1.accept();

		// *******************************************//

		// Delete Organization

		// Identify organization link and click
		driver.findElement(By.linkText("Organizations")).click();

		// identify and click on del link
		driver.findElement(
				By.xpath("//a[text()='" + orgName + "']/ancestor::tr[@bgcolor=\"white\"]/descendant::a[text()='del']"))
				.click();

		// Handle delete pop up
		Alert a2 = driver.switchTo().alert();

		a2.accept();

		// *******************************************//

		// Sign Out

		Thread.sleep(3000);

		WebElement acts = driver.findElement(By.xpath("//img[@style=\"padding: 0px;padding-left:5px\"]"));

		Actions act = new Actions(driver);

		act.moveToElement(acts).perform();

		driver.findElement(By.xpath("//a[text()='Sign Out']")).click();

		Thread.sleep(2000);

		driver.quit();
		// *******************************************//

	}

}



	

		
		
	



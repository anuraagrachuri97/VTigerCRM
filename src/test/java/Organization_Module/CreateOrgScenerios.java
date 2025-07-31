package Organization_Module;
import java.io.IOException;

import Base_Class.*;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Listeners;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;

import GenericUtilites.ExcelFileUtility;
import GenericUtilites.JavaUtility;
import GenericUtilites.PropertyFileUtility;
import GenericUtilites.WebDriverUtility;
import POMUtilities.Create_Org_Pom_Page;
import POMUtilities.HomePomPage;
import POMUtilities.LoginPomPage;
import POMUtilities.Organization_Details_Pom_Page;
import POMUtilities.Organization_Pom_Page;

@Listeners(Listeners_Utilities.Listeners.class)
public class CreateOrgScenerios extends Base_Class  {
	
	//@Parameters("browser")
	@Test (groups = "Smoke", retryAnalyzer = Listeners_Utilities.IRetryAnalyserUtility.class)
	public void createNewOrgTest() throws InterruptedException, IOException {
		
		//Fetch Random Number
		Listeners_Utilities.Listeners.test.log(Status.INFO, "Fetching Random Number");
		GenericUtilities.JavaUtility jutil=new GenericUtilities.JavaUtility();
		int randomNum = jutil.generateRandomNumber();
		/* java utility
		GenericUtilities.JavaUtility jutil = new GenericUtilities.JavaUtility();
		int randomnum=jutil.generateRandomNumber();*/
	   // Fetch data from the Excel File
		Listeners_Utilities.Listeners.test.log(Status., "Fetching data from the excel file");
		GenericUtilities.ExcelFileUtility exutil = new GenericUtilities.ExcelFileUtility();
		String Org=exutil.fetchDataFromExcelFile("Org_Data", 1, 2)+randomNum;
		exutil.closeExcelFile();

		
		//Home page pom utility
		// identify org link
		HomePomPage homeUtil = new HomePomPage(driver);
		homeUtil.getOrg_link().click();

		//Organization page pom utility
		//Click on add org btn
		Organization_Pom_Page orgUtil = new Organization_Pom_Page(driver);
		orgUtil.getclickonOrgAddbtn();
		
		//Create Organization Pom page
		Create_Org_Pom_Page createOrgUtil = new Create_Org_Pom_Page(driver);
		
		//Org name TF
		createOrgUtil.getOrgNameTF().sendKeys(Org);

		// click in save
		createOrgUtil.getSaveBtn().click();
		
		//Organization Details pom utility
		Organization_Details_Pom_Page orgDetailUtil = new Organization_Details_Pom_Page(driver);

		// Org value
		String orgNameVal = orgDetailUtil.getOrgNameVal();
		
		Assert.assertEquals(orgNameVal,Org);
		Reporter.log("create ORG pass",true);
		

		/*if (orgNameVal.contains(Org)) {

			System.out.println("Create Org test pass");

		} else {
			System.out.println("Create org test faill ");
		}*/
		// identify org link
		homeUtil.getOrg_link().click();

		// delete the org
		driver.findElement(
				By.xpath("//a[text()='" + Org + "']/ancestor::tr[@bgcolor=\"white\"]/descendant::a[text()='del']"))
				.click();
		Thread.sleep(3000);
		// for alert
		wbutil.HandleAlertPopupAndClickOK(driver);
		Thread.sleep(3000);

	}
	
	
	//@Parameters("browser")
	@Test(groups = "regression",retryAnalyzer = Listeners_Utilities.IRetryAnalyserUtility.class)
	public void createOrgWithIndustryType() throws IOException, InterruptedException {
		
		// java utility
		GenericUtilities.JavaUtility jutil = new GenericUtilities.JavaUtility();
		int randomnum = jutil.generateRandomNumber();

//	    //Excel File DDt
		GenericUtilities.ExcelFileUtility exutil = new GenericUtilities.ExcelFileUtility();
		String org = exutil.fetchDataFromExcelFile("Org_Data", 9, 2) + randomnum;
		String industry = exutil.fetchDataFromExcelFile("Org_Data", 9, 3);
		String type = exutil.fetchDataFromExcelFile("Org_Data", 9, 4);
		exutil.closeExcelFile();

		
		// Home page pom utility
		// identify org link
		HomePomPage homeUtil = new HomePomPage(driver);
		homeUtil.getOrg_link().click();

		// Organization page pom utility
		// Click on add org btn
		Organization_Pom_Page orgUtil = new Organization_Pom_Page(driver);
		orgUtil.getclickonOrgAddbtn();

		// Create Organization Pom page
		Create_Org_Pom_Page createOrgUtil = new Create_Org_Pom_Page(driver);

		// Org name TF
		createOrgUtil.getOrgNameTF().sendKeys(org);

//	    //Industry Dropdown
		WebElement industry_dd = createOrgUtil.getIndustry_DD();
//	    
//	    Select ind_s = new Select(industry_dd);
//	    
//	    ind_s.selectByValue(industry);
		// handle dropdown using webdriver utilities
		wbutil.SelectDD_UsingValue(driver, industry_dd, type);
		wbutil.Select(industry_dd, industry);

		// Type dropdown

		WebElement type__dd = createOrgUtil.getAccounttype_DD();

		wbutil.SelectValueinDropdown(type__dd, type);

		// click in save
		createOrgUtil.getSaveBtn().click();
		
		Organization_Details_Pom_Page orgDetailsUtil = new Organization_Details_Pom_Page(driver);

		// Validation of org
		String orgval = orgDetailsUtil.getOrgNameVal();

		// Validation value of Industry
		String ind_val = orgDetailsUtil.getIndDDValue();

		// validation value of type
		String typ_val = orgDetailsUtil.getTypDDValue();

		// Validation condition for Org and Industry and Type
		Assert.assertEquals(orgval,org);
		Reporter.log("Create Org with Type and Industry is pass-(Org name)",true);
		Assert.assertEquals(ind_val, industry);
		Reporter.log("Create Org with Type and Industry is pass-(Industry DD)",true);
		Assert.assertEquals(typ_val,type);
		Reporter.log("Create Org with Type and Industry is pass-(Type DD)",true);
		/*if (orgval.contains(org) ) {
			System.out.println("Create Org with Type and Industry is pass-(Org name)");
		} else {
			System.out.println("Create Org with Type and Industry is fail-(Org name)");
		}
		
		if (ind_val.contains(industry)) {
			System.out.println("Create Org with Type and Industry is pass-(Industry DD)");
		} else {
			System.out.println("Create Org with Type and Industry is fail-(Industry DD)");
		}
		
		if (typ_val.contains(type)) {
			System.out.println("Create Org with Type and Industry is pass-(Type DD)");
		} else {
			System.out.println("Create Org with Type and Industry is fail-(Type DD)");
		}*/

		// click on org link
		homeUtil.getOrg_link().click();

		// delete the org
		driver.findElement(
				By.xpath("//a[text()='" + org + "']/ancestor::tr[@bgcolor=\"white\"]/descendant::a[text()='del']"))
				.click();

		// for alert
		wbutil.HandleAlertPopupAndClickOK(driver);

		Thread.sleep(3000);

	}

	//@Parameters("browser")
	@Test(groups = "regression",retryAnalyzer = Listeners_Utilities.IRetryAnalyserUtility.class)
	public void createOrgWithPhoneNum() throws IOException, InterruptedException {

		// java utility
		GenericUtilities.JavaUtility jutil = new GenericUtilities.JavaUtility();
		int randomnum = jutil.generateRandomNumber();

		// Excel File DDt
		GenericUtilities.ExcelFileUtility exeutil = new GenericUtilities.ExcelFileUtility();
		String org = exeutil.fetchDataFromExcelFile("Org_Data", 5, 2) + randomnum;
		String phno = exeutil.fetchDataFromExcelFile("Org_Data", 5, 3);
		exeutil.closeExcelFile();


		// Home page pom utility
		// identify org link
		HomePomPage homeUtil = new HomePomPage(driver);
		homeUtil.getOrg_link().click();

		// Organization page pom utility
		// Click on add org btn
		Organization_Pom_Page orgUtil = new Organization_Pom_Page(driver);
		orgUtil.getclickonOrgAddbtn();

		// Create Organization Pom page
		Create_Org_Pom_Page createOrgUtil = new Create_Org_Pom_Page(driver);

		// Org name TF
		createOrgUtil.getOrgNameTF().sendKeys(org);

		/*
		 * conversion of phone number String - long - String long
		 * Ph_no=Long.parseLong(phno); DecimalFormat df = new DecimalFormat("0"); String
		 * ph_no = df.format(Ph_no);
		 */

		// enter phone number
		createOrgUtil.getPhoneNumTF().sendKeys(phno);

		// click in save
		createOrgUtil.getSaveBtn().click();

		// Organization Details pom utility
		Organization_Details_Pom_Page orgDetailUtil = new Organization_Details_Pom_Page(driver);

		// Org value
		String orgNameVal = orgDetailUtil.getOrgNameVal();
		
		Assert.assertEquals(orgNameVal,org);
		Reporter.log("Create ORG pass - ph number",true);

		// validation of phone number
		String phno_val = orgDetailUtil.getPhNumVal();

		// Validation condition for both Org and phone number
		Assert.assertEquals(phno_val, phno);
		Reporter.log("Create Org with Phone number is pass",true);

		// click on org link
		homeUtil.getOrg_link().click();

		// delete the org
		driver.findElement(
				By.xpath("//a[text()='" + org + "']/ancestor::tr[@bgcolor=\"white\"]/descendant::a[text()='del']"))
				.click();

		// for alert
		wbutil.HandleAlertPopupAndClickOK(driver);

		Thread.sleep(3000);

	}
	

}

	
		
	





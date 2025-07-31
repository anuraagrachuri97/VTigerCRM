package Listeners_Utilities;

import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.ISuite;
import org.testng.ISuiteListener;
import org.testng.Reporter;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import java.io.File;
import java.io.IOException;
import java.sql.Date;

import org.apache.commons.io.FileUtils;


public class Listeners implements ITestListener, ISuiteListener {
	
	public ExtentSparkReporter spark;
	public ExtentReports report;
	public static ExtentTest test;

	@Override
	public void onStart(ISuite suite) {
		Reporter.log("Suite Execution started - Configure the Reports", true);
		String time= new java.util.Date().toString().replace(":", "_").replace("", "_");
		//Configure the Report
		spark=new ExtentSparkReporter("/.AdvancedReports/report_"+time+".html");
		spark.config().setDocumentTitle("Vtiger CRM Project");
		spark.config().setReportName("Test Report");
		spark.config().setTheme(Theme.DARK);
		
		report=new ExtentReports();
		report.attachReporter(spark);
		report.setSystemInfo("OS", "Windows 10");
		report.setSystemInfo("Browser","Chrome");
		
		ExtentTest test=report.createTest("Test");
		test.log(Status.INFO,"Configuring the report");
	}
	@Override
	public void onFinish(ISuite suite) {
		Reporter.log("Suite Execution ended - Backup the Reports", true);
	}

	@Override
	public void onTestStart(ITestResult result) {
		String testname = result.getMethod().getMethodName();
		Reporter.log(testname + " Test Execution started", true);
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		String testname = result.getMethod().getMethodName();
		Reporter.log(testname + " Test Execution Success", true);
	}

	@Override
	public void onTestFailure(ITestResult result) {
		String testname = result.getMethod().getMethodName();
		String time=new java.util.Date().toString().replace(" ", "_").replace(":", "_");
		Reporter.log(testname + " Test Execution Failed", true);
		TakesScreenshot ts = (TakesScreenshot) Base_Class.sdriver;
		String ss = ts.getScreenshotAs(OutputType.BASE64);
		test.addScreenCaptureFromBase64String(ss,""+ testname + time+ "");
	/*	File dest = new File("./Screenshot/" + testname + ".png");
		try {
			FileUtils.copyFile(src, dest);
		} catch (IOException e) {
			e.printStackTrace();
		}*/
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		String testname = result.getMethod().getMethodName();
		Reporter.log(testname + " Test Execution Skipped", true);
		test.log(Status.SKIP,""+testname+"Test Execution Skipped");
	}
}

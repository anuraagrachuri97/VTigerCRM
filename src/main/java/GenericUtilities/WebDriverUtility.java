package GenericUtilities;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WebDriverUtility {
public void maximizetheWindow(WebDriver driver) {
	driver.manage().window().maximize();
}
/**@Note: 
 * 
 * @param driver
 * @param time
 */
public void waitUntilElementIsFound(WebDriver driver,String time) {
	
	long timeouts = Long.parseLong(time);
	driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(timeouts));
}
public void waitUntilElementIsVisible(WebDriver driver,WebElement ele) {
	WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(10));
	wait.until(ExpectedConditions.visibilityOf(ele));
}
public void waitUntilElementIsClickable(WebDriver driver,WebElement ele) {
	WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(10));
	wait.until(ExpectedConditions.elementToBeClickable(ele));
}
public void waitUntilTitleIsVisible(WebDriver driver,String title) {
	WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(10));
	wait.until(ExpectedConditions.titleContains(title));
}
public void navigateToApplication(WebDriver driver,String url) {
	driver.get(url);
}
public void SelectDD_UsingValue(WebDriver driver,WebElement ddEle,String value) {
	Select s=new Select(ddEle);
	s.selectByValue(value);
}
public void SelectDD_UsingIndex(WebDriver driver,WebElement ddEle,int index) {
	Select s=new Select(ddEle);
	s.selectByIndex(index);
}
public void SelectDD_UsingVisibleText(WebDriver driver,WebElement ddEle,String text) {
	Select s=new Select(ddEle);
	s.selectByVisibleText(text);
}
public void HandleAlertPopupAndClickOK(WebDriver driver) {
	driver.switchTo().alert().accept();
}
public void HandleAlertPopupAndClickCancel(WebDriver driver) {
	driver.switchTo().alert().dismiss();
}

public String HandleAlertPopUpAndFetchText(WebDriver driver) {
	String text = driver.switchTo().alert().getText();
	return text;
}

public void HandleAlertPopupAndPassTheText(WebDriver driver,String text) {
	driver.switchTo().alert().sendKeys(text);
}
public void mouseOverOnAnElement(WebDriver driver, WebElement element) {
	Actions act=new Actions(driver);
	act.moveToElement(element).perform();	
}
public void switchToFrameByIndex(WebDriver driver,int index) {
	driver.switchTo().frame(index);
}
public void switchToFrameByID_Name(WebDriver driver,String id_name) {
	driver.switchTo().frame(id_name);
}
public void switchToFrameByWebElement(WebDriver driver,WebElement frameele) {
	driver.switchTo().frame(frameele);

}
public void quitTheBrowser(WebDriver driver) {
	driver.quit();
}
}

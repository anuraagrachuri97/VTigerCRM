package POMUtilities;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPomPage {
	
	//Declaration
	
	@FindBy(linkText ="vtiger")
	private WebElement header;
	
	@FindBy(name="user_name")
	private WebElement usernameTF;

	@FindBy(name="user_password")
	private WebElement passwordTF;

	@FindBy(id="submitButton")
	private WebElement login_btn;
	
	//Initialization
	
	public LoginPomPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
		// TODO Auto-generated constructor stub
	}
	
	
	//Utilization
	public WebElement getHeader() {
		return header;
	}
   public WebElement getUserName() {
		return usernameTF;
	}
	
	public WebElement getPassword() {
		return passwordTF;
	}
	public WebElement getLogin_btn() {
		return login_btn;
	}
 
	 //Business LOgic for Login
	public void login(String user,String password) {
		usernameTF.sendKeys(user);
		passwordTF.sendKeys(password);
		login_btn.click();
	}
}

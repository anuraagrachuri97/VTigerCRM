package POMUtilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Create_Org_Pom_Page {
	
	//declaration
	
	@FindBy(xpath = "//span[text()='Creating New Organization']")
	private WebElement header;
	
	@FindBy(name = "accountname")
	private WebElement orgNameTF;
	
	@FindBy(id = "phone")
	private WebElement phoneNumTF;
	
	@FindBy(name = "industry")
	private WebElement industry_DD;
	
	@FindBy(name = "accounttype")
	private WebElement accounttype_DD;
	
	@FindBy(xpath = "//input[@title='Save [Alt+S]']")
	private WebElement saveBtn;
	
	//intialization
	
	public Create_Org_Pom_Page(WebDriver driver) {
		
		PageFactory.initElements(driver,this);
	}
	
	//utilization
	public String getHeader() {
		return header.getText();
	}

	public WebElement getOrgNameTF() {
		return orgNameTF;
	}

	public WebElement getPhoneNumTF() {
		return phoneNumTF;
	}

	public WebElement getIndustry_DD() {
		return industry_DD;
	}

	public WebElement getAccounttype_DD() {
		return accounttype_DD;
	}

	public WebElement getSaveBtn() {
		return saveBtn;
	}
	

}

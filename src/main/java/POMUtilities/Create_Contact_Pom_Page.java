package POMUtilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Create_Contact_Pom_Page {
	
	@FindBy(name = "lastname")
	private WebElement lastname;
	
	@FindBy(name = "support_start_date")
	private WebElement supportStartDate;
	
	@FindBy(name = "support_end_date")
	private WebElement supportEndDate;
	
	
	@FindBy(xpath = "//input[@title='Save [Alt+S]']")
	private WebElement saveBtn;
	
	@FindBy(xpath = "//img[contains(@onclick,'module=Accounts&action=Popup&popuptype=specific_contact_account_address&')]")
	private WebElement orgAddBtn;
	
	@FindBy(id = "search_txt")
	private WebElement orgSearchTF;
	
	@FindBy(name = "search")
	private WebElement orgSearchBtn;
	
	
	//Initialization
	
	public Create_Contact_Pom_Page(WebDriver driver) {
		PageFactory.initElements(driver,this);
	}


	public WebElement getLastname() {
		return lastname;
	}


	public WebElement getSupportStartDate() {
		return supportStartDate;
	}


	public WebElement getSupportEndDate() {
		return supportEndDate;
	}


	public WebElement getSaveBtn() {
		return saveBtn;
	}


	public WebElement getOrgAddBtn() {
		return orgAddBtn;
	}


	public WebElement getOrgSearchTF() {
		return orgSearchTF;
	}


	public WebElement getOrgSearchBtn() {
		return orgSearchBtn;
	}
	
	
	
	

}

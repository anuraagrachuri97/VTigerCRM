package POMUtilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Contact_Details_Pom_Page {
	
	@FindBy (xpath = "//td[text()='Contact Information']")
	private WebElement header;
	
	@FindBy (id = "dtlview_Last Name")
	private WebElement lastnameVal;
	
	@FindBy (id = "mouseArea_Organization Name")
	private WebElement orgVal;
	
	@FindBy (id = "dtlview_Support Start Date")
	private WebElement supportStrtDateVal;
	
	@FindBy (id = "dtlview_Support End Date")
	private WebElement supportEndDateVal;

	public Contact_Details_Pom_Page(WebDriver driver) {
		PageFactory.initElements(driver,this);
	}

	public String getHeader() {
		return header.getText();
	}

	public String getLastnameVal() {
		return lastnameVal.getText();
	}

	public String getOrgVal() {
		return orgVal.getText();
	}

	public String getSupportStrtDateVal() {
		return supportStrtDateVal.getText();
	}

	public String getSupportEndDateVal() {
		return supportEndDateVal.getText();
	}
	
	
	
	
	

	

}

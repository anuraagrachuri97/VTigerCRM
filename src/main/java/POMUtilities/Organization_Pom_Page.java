package POMUtilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Organization_Pom_Page {

	// declaration
	
	//Organization header
	@FindBy(linkText = "Organizations")
	private WebElement header;

	// Organization add button
	@FindBy(xpath = "//img[@title='Create Organization...']")
	private WebElement orgAddbtn;

	// initialization

	public Organization_Pom_Page(WebDriver driver) {
		PageFactory.initElements(driver, this);

	}

	// utilization
	
	public String getHeader() {
		
		return header.getText();
		
	}
	public void getclickonOrgAddbtn() {
		orgAddbtn.click();
	}

}

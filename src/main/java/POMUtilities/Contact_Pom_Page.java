package POMUtilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Contact_Pom_Page {
	
	//declaration
	
	@FindBy(linkText = "Contacts")
	private WebElement header;
	
	@FindBy(xpath = "//img[@title='Create Contact...']")
	private WebElement addContact;
	
	//intialization
	
	
	 public Contact_Pom_Page(WebDriver driver) {
		 PageFactory.initElements(driver,this);
	 }
	 
	//utilization
	public String getHeader() {
		return header.getText();
	}

	public void getAddContact() {
		 addContact.click();;
	}
	 
	 
	 

}

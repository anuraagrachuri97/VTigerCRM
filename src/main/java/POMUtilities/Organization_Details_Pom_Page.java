package POMUtilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Organization_Details_Pom_Page {
	
	@FindBy (xpath = "//td[text()=' Organization Information']")
	private WebElement header;
	
	
	@FindBy(xpath = "//td[@id=\"mouseArea_Organization Name\"]/span")
	//(id = "mouseArea_Organization Name"
	private WebElement orgNameVal ;
	
	@FindBy(xpath = "//td[@id=\"mouseArea_Phone\"]/span")
	private WebElement phNumVal;
	
	@FindBy(id = "mouseArea_Industry")
	private WebElement indDDValue;
	
	@FindBy(id = "mouseArea_Type")
	private WebElement typDDValue;
	
	// intialization
	
	public Organization_Details_Pom_Page(WebDriver driver) {
		PageFactory.initElements(driver,this);
	}

	public String getOrgNameVal() {
		return orgNameVal.getText();
	}

	public String getPhNumVal() {
		return phNumVal.getText();
	}

	public String getIndDDValue() {
		return indDDValue.getText();
	}

	public String getTypDDValue() {
		return typDDValue.getText();
	}
	
	//utilization
	
	
	

}

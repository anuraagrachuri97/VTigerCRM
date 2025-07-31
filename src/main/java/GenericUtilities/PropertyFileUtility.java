package GenericUtilities;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * @author Anuraag
 * This class contains all the Reusable methods of Properties
 */

public class PropertyFileUtility {
	/**
	 * This method is used to fetch data from property file
	 * @param key
	 * @return String
	 * @throws IOException
	 */

	public String fetchDataFromPropFile(String key) throws IOException {

		FileInputStream fis = new FileInputStream("./src/test/resources/V_Tiger.properties");

		Properties p = new Properties();

		p.load(fis);

		String data = p.getProperty(key);

		return data;

	}
	
	/**
	 * This method is used to Write back the data to property file
	 * @param key
	 * @param value
	 * @param comments
	 * @throws IOException
	 */

	public void writeBackDataToPropFile(String key, String value, String comments) throws IOException {
		FileInputStream fis = new FileInputStream("./src/test/resources/Vtiger.properties");

		Properties p = new Properties();

		p.load(fis);
		
		p.put(key, value);
		FileOutputStream fos = new FileOutputStream("/Vtiger_CRM_App/src/test/resources/Vtiger.properties");
		p.store(fos, comments);

	}

}


package GenericUtilities;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class JsonUtility {
	
	public String FetchDataFromJson(String key) throws FileNotFoundException, IOException, ParseException
	{
		//Converting physical JSON File to Java object using parse(Non-static method) from JSONParserClass 
		JSONParser json=new JSONParser();
		Object obj = json.parse(new FileReader("./src/test/resources/V_Tiger.json"));
		
		//Converting java Object to JSON Object by Downcasting
		JSONObject js=(JSONObject) obj;
		 
		//Fetching the data from the JSON Object using get()
		
		String data = js.get(key).toString();
		
		return data;
		
		
	}

}

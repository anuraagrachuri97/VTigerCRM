package GenericUtilities;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

public class JavaUtility {
	
	
	public int generateRandomNumber() {
		Random r=new Random();
		int rannum = r.nextInt(1000);
		return rannum;
	}
public String getSystemCurrentDate() {
	Date dobj=new Date();
	SimpleDateFormat sim=new SimpleDateFormat("yyyy-MM-dd");
	String currentdate = sim.format(dobj);
	return currentdate;
}


public String getDateAfterSpecificDays(int days) {
	Date dobj=new Date();
	SimpleDateFormat sim=new SimpleDateFormat("yyyy-MM-dd");
	String currentdate=sim.format(dobj);
	Calendar cal=sim.getCalendar();
	cal.add(Calendar.DAY_OF_MONTH,days);
	String date=sim.format(cal.getTime());
	return date;
	
}

}

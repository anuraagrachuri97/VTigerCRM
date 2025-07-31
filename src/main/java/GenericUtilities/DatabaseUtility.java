package GenericUtilities;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.mysql.jdbc.Driver;

public class DatabaseUtility {
	
	public Connection con;

	
	public ResultSet fetchDataFromDatabase(String url,String un,String pswd,String query) throws SQLException {
		Driver driver=new Driver();
		DriverManager.registerDriver(driver);
		con = DriverManager.getConnection(url,un,pswd);
		Statement stat = con.createStatement();
		ResultSet res = stat.executeQuery(query);
		return res;
		
	}
	
	public ResultSet fetchDataFromDatabase(String query) throws SQLException {
		Driver driver=new Driver();
		DriverManager.registerDriver(driver);
		con = DriverManager.getConnection("http://localhost:8888/project","root","anuraag");
		Statement stat = con.createStatement();
		ResultSet res = stat.executeQuery(query);
		return res;
		
	}
	
   public int WritebackDatatoDatabase(String query) throws Exception {
		
		//create the driver
		
		Driver driver=new Driver();
		
		//register the driver
		
		DriverManager.registerDriver(driver);
		
		//get connection
		
		 con=DriverManager.getConnection("http://localhost:8888/project","root","anuraag");
		 
		 //Create statement
		 
		Statement stmt=con.createStatement();
		
		int result=stmt.executeUpdate(query);
		
		return result;
		
		}
   
   public void ClosetheDatabaseConnection() throws SQLException

   {
   	
	   con.close();	
   	
   }
   
   public Connection getDatabaseConnection() throws Exception {
		
		Driver driver=new Driver();
		
		DriverManager.registerDriver(driver);
		
		 con=DriverManager.getConnection("jdbc:mysql://localhost:3306/project","root","anuraag");
		 
		 return con;
	}
		


   
   
   

}

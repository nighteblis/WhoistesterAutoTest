package cf.lihao.testlib;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.mysql.jdbc.Connection;

import cf.lihao.Vars;
import cf.lihao.report.TestReporter;
import cf.lihao.util.JSONUtil;

public class Mysql extends TestLibInterface{

		
	public boolean execute(String[] para)
	{

		this.returnCode = "";
		this.returnCookie = "";
		this.returnResponse = "";
		this.returnHeader = null;
		
		boolean ret = false;
		
		if(para[0].equals("mysql"))
		{
			try {
				mysql(para);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		    ret = true;
		}
		
		Vars.putKey("lastResponseBody", this.returnResponse);
		Vars.putKey("lastResponseCode", this.returnCode);
		Vars.putKey("lastResponseCookie", this.returnCookie);		
		
		TestReporter.writeToReport(para, "returnCode: " + this.returnCode + "</br>returnCookie: " + this.returnCookie
				+ "</br>returnResponse: " + this.returnResponse);
		
		return ret;
	}
	
	
	public  void mysql(String[] para) throws SQLException
	{
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		
		Connection connect = null;
		Statement stmt = null;
		  String jsonstring = null;
		try {
			connect = (Connection) DriverManager
			          .getConnection(para[1]);
						   
		    String query = para[2];
		    
		        stmt = connect.createStatement();
		        ResultSet rs = stmt.executeQuery(query);
		        
		       jsonstring = JSONUtil.ResultSet2JSONString(rs);
		       this.returnCode = "ok";
		    } catch (SQLException e ) {
		    	this.returnCode = "failed";
		        e.printStackTrace();
		    }		
	         finally {
		        if (stmt != null) { stmt.close(); }
		    }	
		this.returnResponse =  jsonstring;
		
		
	}
	
	
	
	
 public static void main(String[] params)
 
 {
//	 String [] a = {};
//	 try {
//		mysql(a);
//	} catch (SQLException e) {
//		// TODO Auto-generated catch block
//		e.printStackTrace();
//	}

 }
	
	
}

package cf.lihao.testlib;

import cf.lihao.Vars;
import cf.lihao.report.TestReporter;

public class Echo extends TestLibInterface{

		
	public boolean execute(String[] para)
	{
		
		this.returnCode = "";
		this.returnCookie = "";
		this.returnResponse = "";
		this.returnHeader = null;
		
		boolean ret = false;
		
		if(para[0].equals("echo"))
		{
			echo(para);
		    ret = true;
		}
		
		Vars.putKey("lastResponseBody", this.returnResponse);
		Vars.putKey("lastResponseCode", this.returnCode);
		Vars.putKey("lastResponseCookie", this.returnCookie);
		
		TestReporter.writeToReport( para, "returnCode: "+this.returnCode+"</br>returnCookie: "+this.returnCookie+"</br>returnResponse: "+this.returnResponse);	
		
		
		
		return ret;
	}
	
	
	public void echo(String[] para)
	{
		
		if(para.length > 2)
		{
			Vars.putKey(para[1].trim(), para[2].trim());
			this.returnResponse = para[1]+" have setted the value "+para[2];
		}
		else{
		String value = Vars.getKey(para[1]);
		
		this.returnResponse = value;
		}
		
	}
	
	
	
	
 public static void main(String[] params)
 
 {
	 
	 
	 
	 
 }
	
	
	
	

}

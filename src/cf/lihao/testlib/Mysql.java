package cf.lihao.testlib;

import cf.lihao.Vars;

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
			mysql(para);
		    ret = true;
		}
		
		return ret;
	}
	
	
	public void mysql(String[] para)
	{
		
		
		
		
		
	}
	
	
	
	
 public static void main(String[] params)
 
 {
	 
	 
	 
	 
 }
	
	
	
	

}

package cf.lihao.testlib;

import cf.lihao.Vars;

public class Redis extends TestLibInterface{

		
	public boolean execute(String[] para)
	{
		
		this.returnCode = "";
		this.returnCookie = "";
		this.returnResponse = "";
		this.returnHeader = null;
		
		boolean ret = false;
		
		if(para[0].equals("redis"))
		{
			redis(para);
		    ret = true;
		}
		
		return ret;
	}
	
	
	public void redis(String[] para)
	{
		
		
		
		
		
	}
	
	
	
	
 public static void main(String[] params)
 
 {
	 
	 
	 
	 
 }
	
	
	
	

}

package cf.lihao.testlib;

import cf.lihao.Vars;

public class JsonParser extends TestLibInterface{

		
	public boolean execute(String[] para)
	{
		
		this.returnCode = "";
		this.returnCookie = "";
		this.returnResponse = "";
		this.returnHeader = null;
		
		boolean ret = false;
		
		if(para[0].equals("jsonget"))
		{
			jsonget(para);
		    ret = true;
		}
		
		return ret;
	}
	
	
	public void jsonget(String[] para)
	{
		
		
		
		
		
	}
	
	
	
	
 public static void main(String[] params)
 
 {
	 
	 
	 
	 
 }
	
	
	
	

}

package cf.lihao.testlib;

import cf.lihao.Vars;
import cf.lihao.report.TestReporter;
import redis.clients.jedis.Jedis;

public class Redis extends TestLibInterface{

		
	public boolean execute(String[] para)
	{
		
		this.returnCode = "";
		this.returnCookie = "";
		this.returnResponse = "";
		this.returnHeader = null;
		
		boolean ret = false;
		
		if(para[0].equals("redisget"))
		{
			redisget(para);
		    ret = true;
		}
		else if(para[0].equals("redisset"))
		{
			redisset(para);
		    ret = true;
		}		
		
		Vars.putKey("lastResponseBody", this.returnResponse);

		TestReporter.writeToReport(para, "returnCode: " + this.returnCode + "</br>returnCookie: " + this.returnCookie
				+ "</br>returnResponse: " + this.returnResponse);		
		
		
		return ret;
	}
	
	
	public void redisget(String[] para)
	{
		
		   String[] hostport = para[1].split(":");
		   Jedis jedis  = null;
				   
		   if(hostport.length == 2)
		
			 jedis = new Jedis(hostport[0],Integer.valueOf(hostport[1]));
		   
		   else
			 jedis  = new Jedis(para[1]);
			
			
			this.returnResponse = jedis.get(para[2]);
						 
		
	}
	
	public void redisset(String[] para)
	{
		
		  Jedis jedis  = null;
		  String[] hostport = para[1].split(":");
		  
		   if(hostport.length == 2)				
			 jedis = new Jedis(hostport[0],Integer.valueOf(hostport[1]));		   
		   else
			 jedis  = new Jedis(para[1]);
		   		   
			String result = "";
			
			if(para.length < 5)
			
				result = jedis.set(para[2], para[3]);
			else 
			    result = jedis.setex(para[2],Integer.valueOf(para[4]), para[3]);
			
			this.returnResponse = result;
		
	}	
		
 public static void main(String[] params)
 
 {
	 
	 
	 
	 
 }
	
	
	
	

}

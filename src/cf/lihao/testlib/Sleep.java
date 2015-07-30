package cf.lihao.testlib;

import cf.lihao.Vars;
import cf.lihao.report.TestReporter;

public class Sleep extends TestLib{

	
		
	public boolean execute(String[] para)
	{			
		
		boolean ret = false;
		
		if(para[0].equals("sleep"))
		{
			sleep(para);
		    ret = true;
		}
		
		Vars.putKey("lastEchoBody", this.returnResponse);
		Vars.putKey("lastEchoCode", this.returnCode);
		Vars.putKey("lastEchoCookie", this.returnCookie);
		
		TestReporter.writeToReport( para, "sleep "+para[1]+" seconds");	
				
		return ret;
	}
	
	
	public void sleep(String[] para)
	{
		
		if(para.length > 1)
		{
			System.out.println("sleeping ");
		  try {
			Thread.sleep(Long.valueOf(para[1])*1000);
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			
		}
		
		//this.returnResponse = "sleep "+para[1]+" second";
		
	}
	
	
 public static void main(String[] params)
 
 {
	 
	 
	 
	 
 }


}

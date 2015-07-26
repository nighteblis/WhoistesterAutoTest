package cf.lihao.testlib;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import cf.lihao.Vars;

public class ResponseMatch extends TestLibInterface{

		
	public boolean execute(String[] para)
	{
		
		this.returnCode = "";
		this.returnCookie = "";
		this.returnResponse = "";
		this.returnHeader = null;
		
		boolean ret = false;
		
		if(para[0].equals("pregmatch"))
		{
			match(para);
		    ret = true;
		}
		
		return ret;
	}
	
	
	public void match(String[] para)
	{
				
		String pattern = para[1];
		Pattern r = Pattern.compile(pattern);
		
		Matcher m = r.matcher(para[2]);
		int i  =0;
		// System.out.println(line);
		if(null == para[3] ||  para[3].equals(""))
		{		
		
			this.returnCode = "-1";
			this.returnCookie = "";
			this.returnResponse = "error parameters! need the 4th parameter!";
			this.returnHeader = null;
			return;
		}
		while (m.find()) {

			if(null == m.group(1) || m.group(1).equals(""))
			{
				this.returnCode = "-1";
				this.returnCookie = "";
				this.returnResponse = "error parameters! please check your 2rd parameters";
				this.returnHeader = null;				
				return;
			}

			  if(i ==0)
				  Vars.putKey(para[3], m.group(1));
			  else 
				  Vars.putKey(para[3]+(i+1), m.group(1));
			i++;

		}
		this.returnCode = String.valueOf(i);
		this.returnCookie = "";
		this.returnResponse = "find "+i+" matches named "+para[3];
		this.returnHeader = null;		
		
		
	}
	
	
	
	
 public static void main(String[] params)
 
 {
	 
	 
	 
	 
 }
	
	
	
	

}

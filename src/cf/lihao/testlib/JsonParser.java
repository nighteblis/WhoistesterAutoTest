package cf.lihao.testlib;

import java.util.List;

import com.nebhale.jsonpath.JsonPath;

import cf.lihao.Vars;
import cf.lihao.report.TestReporter;

public class JsonParser extends TestLib{

		
	public boolean execute(String[] para)
	{
		
		
		boolean ret = false;
		
		if(para[0].equals("jsongetstring"))
		{
			jsonget(para);
		    ret = true;
		}
		else 
			if(para[0].equals("jsongetarray"))
			{
				jsongetarray(para);
			    ret = true;
			}	
			else 
				if(para[0].equals("jsongetarraycount"))
				{
					jsongetarraycount(para);
				    ret = true;
				}	
		
		TestReporter.writeToReport(para, "returnCode: " + this.returnCode 
				+ "</br>returnResponse: " + this.returnResponse);	
		return ret;
	}
	
	
	public void jsonget(String[] para)
	{
		System.out.println(para[1]+" "+para[2]);
		
		
		String names = JsonPath.read(para[1], para[2], String.class);
		
		this.returnResponse = names;

	}
	
	public void jsongetarray(String[] para)
	{
		System.out.println(para[1]+" "+para[2]);
		
		
		List<String> names = JsonPath.read(para[1], para[2], List.class);
		
		this.returnResponse = names.toString();

	}
	
	public void jsongetarraycount(String[] para)
	{
		System.out.println(para[1]+" "+para[2]);
				
		List<String> names = JsonPath.read(para[1], para[2], List.class);
		
		this.returnResponse = String.valueOf(names.size());

	}
	
 public static void main(String[] params)
 
 {
	 
	 
	 
	 
 }
	
	
	
	

}

package cf.lihao.textlib;

import java.util.HashMap;
import java.util.Map;

import cf.lihao.Vars;
import cf.lihao.textlib.TestLibInterface;

import com.github.kevinsawicki.http.HttpRequest;

public class HttpNormalRequest extends TestLibInterface{

	public boolean execute(String[] para)
	{
		boolean ret = false;
		
		if(para[0].equals("get"))
		{
			get(para);
		    ret = true;
		}
		
		Vars.putKey("lastResponseBody", returnResponse);
		Vars.putKey("lastReturnCode", this.returnCode);
		Vars.putKey("lastResponseCookie", this.returnCookie);
		
		
		return ret;
	}
	
	public void get(String[] para)
	{
		
		if(!(para.length >= 2))  
	    {
			   return;
	    }
		
		HttpRequest request = HttpRequest.get(para[1]);
		
		//Map<String, String> headers = new HashMap<String, String>();
		
		//.header("cookie","JSESSIONID=abcdefg;").header("User-Agent","Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/31.0.1650.63 Safari/537.36");
		
		if(para.length > 3)
		{
			
			for(int i = 2; i < para.length; i++)
			{
				String[] header = para[i].split(":");
				//headers.put(header[0], header[1]);
				request.header(header[0], header[1]);
			}
			
			
		}
		
		

		System.out.println(para[1]);
		
		this.returnCode = String.valueOf(request.code());
		
		this.returnResponse = request.body();
		
		this.returnCookie = request.header("Set-Cookie");
		//this.returnHeader = request.header();
	
		
		System.out.println(returnCode+" "+returnResponse+" "+this.returnCookie);

	}
	public void post(String[] para)
	{
		
		
		
	}
	
	
	
	
}

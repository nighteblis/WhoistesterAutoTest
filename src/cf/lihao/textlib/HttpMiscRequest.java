package cf.lihao.textlib;

import cf.lihao.textlib.TestLibInterface;

import com.github.kevinsawicki.http.HttpRequest;

public class HttpMiscRequest extends TestLibInterface{


	public boolean execute(String[] para)
	{
		
		System.out.println(para[0]+" lll "+para[1]+" lll "+para[2]);
		
		if(para[0].equals("get"))
		{
			get(para);
		}
		
		
		return false;
	}
	
	public void get(String[] para)
	{
		
		HttpRequest request = HttpRequest.get(para[1]+"?"+para[2]).header("cookie","JSESSIONID=abcdefg;").header("User-Agent","Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/31.0.1650.63 Safari/537.36");
		System.out.println(para[1]+"?"+para[2]);
		
		this.returnCode = String.valueOf(request.code());
		
		this.returnResponse = request.body();
	
		
		System.out.println(returnCode+" "+returnResponse+" "+request.header("set-cookie"));

	}
	public void post(String[] para)
	{
		
		
		
	}
	
	
}

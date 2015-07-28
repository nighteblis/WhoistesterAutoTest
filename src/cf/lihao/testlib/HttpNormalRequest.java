package cf.lihao.testlib;

import cf.lihao.Vars;
import cf.lihao.report.TestReporter;
import cf.lihao.testlib.TestLibInterface;

import com.github.kevinsawicki.http.HttpRequest;

public class HttpNormalRequest extends TestLibInterface {

	public boolean execute(String[] para) {

		this.returnCode = "";
		this.returnCookie = "";
		this.returnResponse = "";
		this.returnHeader = null;

		boolean ret = false;

		if (para[0].equals("httpget")) {
			get(para);
			ret = true;
		} else if (para[0].equals("httppost")) {
			post(para);
			ret = true;
		} else if (para[0].equals("httpmultipost")) {
			get(para);
			ret = true;
		}

		Vars.putKey("lastResponseBody", this.returnResponse);
		Vars.putKey("lastResponseCode", this.returnCode);
		Vars.putKey("lastResponseCookie", this.returnCookie);

		TestReporter.writeToReport(para, "returnCode: " + this.returnCode + "</br>returnCookie: " + this.returnCookie
				+ "</br>returnResponse: " + this.returnResponse);

		return ret;
	}

	public void get(String[] para) {

		if (!(para.length >= 3)) {
			return;
		}

		HttpRequest request = null;
		
		if("no".equals(para[2].trim()))
			 request = HttpRequest.get(para[1]);
		else
			request = HttpRequest.get(para[1] + "?" + para[2]);

		System.out.println("------ " + para[1] + "?" + para[2]);
		// HttpRequest.proxyHost("127.0.0.1");
		// HttpRequest.proxyPort(8888);
		// Map<String, String> headers = new HashMap<String, String>();

		// .header("cookie","JSESSIONID=abcdefg;").header("User-Agent","Mozilla/5.0
		// (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko)
		// Chrome/31.0.1650.63 Safari/537.36");

		if (para.length > 3) {

			for (int i = 3; i < para.length; i++) {
				System.out.println(para[i]);
				String[] header = para[i].split(":");
				// headers.put(header[0], header[1]);
				
				if(header.length < 2) continue;
				if (null == header[0] || header[0].equals("")) {
					continue;
				}
				
				request.header(header[0], header[1]);
				// request.parameter(headerName, paramName)
				System.out.println("headers here===" + header[0] + ":" + header[1]);

			}

		}

		System.out.println(para[1]);

		this.returnCode = String.valueOf(request.code());
		System.out.println("return code: " + this.returnCode);
		this.returnResponse = request.body();

		this.returnCookie = request.header("Set-Cookie");
		// this.returnHeader = request.header();

		System.out.println(returnCode + " " + returnResponse + " " + this.returnCookie);

	}

	public void post(String[] para) {

		if (!(para.length >= 2)) {
			return;
		}

		HttpRequest request = HttpRequest.post(para[1]);
		// HttpRequest.proxyHost("127.0.0.1");
		// HttpRequest.proxyPort(8888);
		// Map<String, String> headers = new HashMap<String, String>();

		// .header("cookie","JSESSIONID=abcdefg;").header("User-Agent","Mozilla/5.0
		// (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko)
		// Chrome/31.0.1650.63 Safari/537.36");

		if (para.length > 3) {

			for (int i = 3; i < para.length; i++) {
				String[] header = para[i].split(":");
				if (null == header[0] || header[0].equals("")) {
					continue;
				}
				// headers.put(header[0], header[1]);
				request.header(header[0], header[1]);
				System.out.println("headers here===" + header[0] + ":" + header[1]);
			}

		}

		System.out.println(para[1]);

		request = request.send(para[2]);

		this.returnCode = String.valueOf(request.code());
		System.out.println("return code: " + this.returnCode);
		this.returnResponse = request.body();

		this.returnCookie = request.header("Set-Cookie");
		// this.returnHeader = request.header();

		System.out.println(returnCode + " " + returnResponse + " " + this.returnCookie);

	}

}

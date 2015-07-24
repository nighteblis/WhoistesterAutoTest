package cf.lihao.testlib;

import cf.lihao.Vars;
import cf.lihao.testlib.TestLibInterface;

import com.github.kevinsawicki.http.HttpRequest;

public class HttpMiscRequest extends TestLibInterface {

	public boolean execute(String[] para) {

		this.returnCode = "";
		this.returnCookie = "";
		this.returnResponse = "";
		this.returnHeader = null;

		boolean ret = false;

		if (para[0].equals("httpmiscget")) {
			get(para);
			ret = true;
		} else if (para[0].equals("httpmiscpost")) {
			post(para);
			ret = true;
		} else if (para[0].equals("httpmiscmultipost")) {
			get(para);
			ret = true;
		}

		Vars.putKey("lastResponseBody", this.returnResponse);
		Vars.putKey("lastReturnCode", this.returnCode);
		Vars.putKey("lastResponseCookie", this.returnCookie);

		return ret;
	}

	public void get(String[] para) {

		if (!(para.length >= 2)) {
			return;
		}

		HttpRequest request = HttpRequest.get(para[1]);

		// Map<String, String> headers = new HashMap<String, String>();

		// .header("cookie","JSESSIONID=abcdefg;").header("User-Agent","Mozilla/5.0
		// (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko)
		// Chrome/31.0.1650.63 Safari/537.36");

		if (para.length > 3) {

			for (int i = 2; i < para.length; i++) {
				String[] header = para[i].split(":");
				// headers.put(header[0], header[1]);
				request.header(header[0], header[1]);
			}

		}

		System.out.println(para[1]);

		this.returnCode = String.valueOf(request.code());

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

		// Map<String, String> headers = new HashMap<String, String>();

		// .header("cookie","JSESSIONID=abcdefg;").header("User-Agent","Mozilla/5.0
		// (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko)
		// Chrome/31.0.1650.63 Safari/537.36");

		if (para.length > 4) {

			for (int i = 3; i < para.length; i++) {
				String[] header = para[i].split(":");
				// headers.put(header[0], header[1]);
				request.header(header[0], header[1]);
			}

		}

		System.out.println(para[1]);

		request = request.send(para[2]);

		this.returnCode = String.valueOf(request.code());

		this.returnResponse = request.body();

		this.returnCookie = request.header("Set-Cookie");
		// this.returnHeader = request.header();

		System.out.println(returnCode + " " + returnResponse + " " + this.returnCookie);

	}

}

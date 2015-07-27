package cf.lihao.testlib;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import cf.lihao.Vars;
import cf.lihao.testlib.TestLibInterface;

public class HttpMiscRequest extends TestLibInterface {

	public boolean execute(String[] para) {

		this.returnCode = "";
		this.returnCookie = "";
		this.returnResponse = "";
		this.returnHeader = null;

		if (para[0].equals("httpmiscget")) {
			get(para);
		} else if (para[0].equals("httpmiscpost")) {
			post(para);

		} else if (para[0].equals("httpmiscmultipost")) {
			get(para);
		}

		System.out.println("===misc request ===");

		Vars.putKey("lastResponseBody", this.returnResponse);
		Vars.putKey("lastResponseCode", this.returnCode);
		Vars.putKey("lastResponseCookie", this.returnCookie);

		if (this.returnResponse.equals("200"))
			return true;
		else
			return false;

	}

	public void get(String[] para) {

		if (!(para.length >= 3)) {
			return;
		}
		StringBuffer oldPara = new StringBuffer();
		oldPara.append(para[0]);

		for (int i = 1; i < para.length; i++) {
			oldPara.append(" " + para[i]);
		}

		para[0] = "httpget";

		int diclength = Vars.getDictionary().size();
		int urldatalength = para[2].split("&").length;

		System.out.println("why" + diclength + urldatalength);
		(new HttpNormalRequest()).execute(para);

		for (int j = 0; j < urldatalength; j++) {
			for (int i = 0; i < diclength; i++) {
				para = oldPara.toString().split("\\s+");
				para[0] = "httpget";
				System.out.println("======= " + i + " " + j);
				String newParaString = reConstructPara(para[2].split("&"), j, i);
				if ("".equals(newParaString))
					continue;
				para[2] = newParaString;
				(new HttpNormalRequest()).execute(para);
			}
		}

	}

	public void post(String[] para) {

		if (!(para.length >= 2)) {
			return;
		}
		StringBuffer oldPara = new StringBuffer();
		oldPara.append(para[0]);

		for (int i = 1; i < para.length; i++) {
			oldPara.append(" " + para[i]);
		}

		para[0] = "httppost";

		int diclength = Vars.getDictionary().size();
		int urldatalength = para[2].split("&").length;

		System.out.println("why" + diclength + urldatalength);
		(new HttpNormalRequest()).execute(para);

		for (int j = 0; j < urldatalength; j++) {
			for (int i = 0; i < diclength; i++) {
				para = oldPara.toString().split("\\s+");
				para[0] = "httppost";
				System.out.println("======= " + i + " " + j);
				String newParaString = reConstructPara(para[2].split("&"), j, i);
				if ("".equals(newParaString))
					continue;
				para[2] = newParaString;
				(new HttpNormalRequest()).execute(para);
			}
		}
	}

	// regroup the parameter string
	private String reConstructPara(String[] para, int paraindex, int dicindex) {

		int length = para.length;
		StringBuffer paraString = new StringBuffer();
		String[] newPara = para[paraindex].split("=");
		try {
			newPara[1] = URLEncoder.encode(Vars.getDictionary(dicindex), "UTF-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "";
		}
		para[paraindex] = newPara[0] + "=" + newPara[1];
		System.out.println("regroup1: " + para[paraindex] + " " + Vars.getDictionary(dicindex));
		System.out.println("getting the dictionary :" + Vars.getDictionary(dicindex));

		System.out.println("regroup1: " + para[0]);
		paraString.append(para[0]);
		for (int i = 1; i < length; i++) {
			System.out.println("regroup1: " + para[i]);
			paraString.append("&" + para[i]);
		}
		System.out.println("regroup2: " + paraString);
		return paraString.toString();

	}

}

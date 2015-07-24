package cf.lihao.testlib;

import cf.lihao.Vars;
import cf.lihao.testlib.TestLibInterface;

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

		System.out.println("===misc request ===");

		Vars.putKey("lastResponseBody", this.returnResponse);
		Vars.putKey("lastReturnCode", this.returnCode);
		Vars.putKey("lastResponseCookie", this.returnCookie);

		return ret;
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

		for (int i = 0; i < diclength; i++) {
			for (int j = 0; j < urldatalength; j++) {
				para = oldPara.toString().split("\\s+");
				System.out.println("======= " + i + " " + j);
				String newParaString = reGroup(para[2].split("&"), j, i);
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

		for (int i = 0; i < diclength; i++) {
			for (int j = 0; j < urldatalength; j++) {
				para = oldPara.toString().split("\\s+");
				System.out.println("======= " + i + " " + j);
				String newParaString = reGroup(para[2].split("&"), j, i);
				para[2] = newParaString;
				(new HttpNormalRequest()).execute(para);
			}
		}
	}

	// regroup the parameter string
	private String reGroup(String[] para, int paraindex, int dicindex) {

		int length = para.length;
		StringBuffer paraString = new StringBuffer();
		String[] newPara = para[paraindex].split("=");
		newPara[1] = Vars.getDictionary(dicindex);
		para[paraindex] = newPara[0] + "=" + newPara[1];
		System.out.println("regroup1: " + para[paraindex] + " "
				+ Vars.getDictionary(dicindex));
		System.out.println("getting the dictionary :"
				+ Vars.getDictionary(dicindex));

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

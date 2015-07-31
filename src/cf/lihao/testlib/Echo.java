package cf.lihao.testlib;

import cf.lihao.Vars;
import cf.lihao.report.TestReporter;

public class Echo extends TestLib implements TestLibInterface {

	private final String[] description = { "sleep", "单位秒数（数字）" };

	public boolean execute(String[] para) {

		boolean ret = false;

		if (para[0].equals("echo")) {
			echo(para);
			ret = true;
		}

		Vars.putKey("lastEchoBody", this.returnResponse);
		Vars.putKey("lastEchoCode", this.returnCode);
		Vars.putKey("lastEchoCookie", this.returnCookie);
		this.para = para;
		TestReporter.writeToReport(para, "returnCode: " + this.returnCode
				+ "</br>returnCookie: " + this.returnCookie
				+ "</br>returnResponse: " + this.returnResponse);

		return ret;
	}

	@TestLibAnnotation( key = "echo")
	public void echo(String[] para) {

		if (para.length > 2) {
			Vars.putKey(para[1].trim(), para[2].trim());
			this.returnResponse = para[1] + " have setted the value " + para[2];
		} else {
			String value = Vars.getKey(para[1]);

			this.returnResponse = value;
		}

	}

	@Override
	public void testResult() {
		// TODO Auto-generated method stub
		TestReporter.writeToReport(para, "returnCode: " + this.returnCode
				+ "</br>returnCookie: " + this.returnCookie
				+ "</br>returnResponse: " + this.returnResponse);
	}

}

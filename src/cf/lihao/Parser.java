package cf.lihao;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import cf.lihao.report.TestReporter;
import cf.lihao.testlib.Echo;
import cf.lihao.testlib.HttpMiscRequest;
import cf.lihao.testlib.HttpNormalRequest;
import cf.lihao.testlib.JsonParser;
import cf.lihao.testlib.ResponseMatch;

public class Parser {

	String pattern = "(\\$\\{.+?\\})";
	Pattern r = Pattern.compile(pattern);

	public boolean parse(String scriptPath, String dicPath, String reportPath) {

		// The name of the file to open.
		String fileName = dicPath;

		// This will reference one line at a time
		String line = null;

		TestReporter.writeHeaderToReport(reportPath);

		TestReporter.reportPath = reportPath;

		File file = new File(dicPath);
		if (file.isFile() && file.exists()) {

			try {
				InputStreamReader insReader = new InputStreamReader(new FileInputStream(file), "UTF-8");

				BufferedReader bufReader = new BufferedReader(insReader);

				String dicline = new String();
				while ((dicline = bufReader.readLine()) != null) {
					Vars.addDictionary(dicline.trim());
				}
				bufReader.close();
				insReader.close();
			}

			catch (IOException ex) {
				System.out.println("Error reading file '" + fileName + "'");
				// Or we could just do this:
				// ex.printStackTrace();
			}

		}
		else {
			
			System.out.println("Error reading file '" + fileName + "'");
		}

		fileName = scriptPath;
		line = null;

		String delimiter = "\\s+";
		if(scriptPath.endsWith(".csv"))
		{
			delimiter = ",";
		}
		
		
		try {
			// FileReader reads text files in the default encoding.
			FileReader fileReader = new FileReader(fileName);

			// Always wrap FileReader in BufferedReader.
			BufferedReader bufferedReader = new BufferedReader(fileReader);

			while ((line = bufferedReader.readLine()) != null) {

				if (line.startsWith(";")) {

					// if starts with ; , means the vars definition components

					int index = line.indexOf('=');
					Vars.putKey(line.substring(1, index).trim(), line.substring(index + 1).trim());
					continue;

				}
				
				else if(line.startsWith("#"))
				{
					continue;
				}

				else {

					// case parser started

					String[] firsta = line.split(delimiter);

					// not replace the all line , but for every arguments ,
					// because of the replace string may
					// contain the space , that will break the retrieving the
					// arguments from the original test case line.

					for (int i = 0; i < firsta.length; i++) {
						Matcher m = r.matcher(firsta[i]);
						// System.out.println(line);
						while (m.find()) {
							String tmp = m.group(1).substring(2, m.group(1).length() - 1);
							System.out.println("\\$\\{" + tmp + "\\}" + " " + Vars.getKey(tmp) + " "
									+ m.group(1).substring(2, m.group(1).length() - 1));
							if(null == Vars.getKey(tmp)) 
							
								firsta[i] = firsta[i].replace(m.group(1), "vars_not_found");
							
							else
							firsta[i] = firsta[i].replace(m.group(1), Vars.getKey(tmp));

						}

					}

					if (firsta.length > 1) {
						System.out.println(firsta.length + "----" + firsta[0]);

						if (firsta[0].equals("httpget")) {
							new HttpNormalRequest().execute(firsta);

						} else if (firsta[0].equals("httppost")) {
							new HttpNormalRequest().execute(firsta);

						} else if (firsta[0].equals("httpmultipost")) {
							new HttpNormalRequest().execute(firsta);

						} else if (firsta[0].equals("httpmiscget")) {
							new HttpMiscRequest().execute(firsta);

						} else if (firsta[0].equals("httpmiscpost")) {
							new HttpMiscRequest().execute(firsta);
						} else if (firsta[0].equals("echo")) {
							new Echo().execute(firsta);
						}
						else if (firsta[0].equals("pregmatch")) {
							new ResponseMatch().execute(firsta);
						}
						else if (firsta[0].startsWith("json")) {

							new JsonParser().execute(firsta);

						}
					}
				}

				// System.out.println(line);
			}

			// Always close files.
			bufferedReader.close();
		} catch (FileNotFoundException ex) {
			System.out.println("Unable to open file '" + fileName + "'");
		} catch (IOException ex) {
			System.out.println("Error reading file '" + fileName + "'");
			// Or we could just do this:
			// ex.printStackTrace();
		}

		TestReporter.writeFooterToReport(reportPath);

		return false;
	}

}

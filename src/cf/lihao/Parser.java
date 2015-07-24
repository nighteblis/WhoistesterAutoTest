package cf.lihao;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import cf.lihao.testlib.HttpMiscRequest;
import cf.lihao.testlib.HttpNormalRequest;

public class Parser {

	String pattern = "(\\$\\{.+?\\})";
	Pattern r = Pattern.compile(pattern);

	public boolean parse(String scriptPath, String dicPath) {

		// The name of the file to open.
		String fileName = dicPath;

		// This will reference one line at a time
		String line = null;

		try {
			// FileReader reads text files in the default encoding.
			FileReader fileReader = new FileReader(fileName);

			// Always wrap FileReader in BufferedReader.
			BufferedReader bufferedReader = new BufferedReader(fileReader);

			while ((line = bufferedReader.readLine()) != null) {

				Vars.addDictionary(line.trim());
				System.out.println(line);

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

		fileName = scriptPath;
		line = null;
		try {
			// FileReader reads text files in the default encoding.
			FileReader fileReader = new FileReader(fileName);

			// Always wrap FileReader in BufferedReader.
			BufferedReader bufferedReader = new BufferedReader(fileReader);

			while ((line = bufferedReader.readLine()) != null) {

				if (line.startsWith(";")) {

					int index = line.indexOf('=');
					Vars.putKey(line.substring(1, index).trim(), line
							.substring(index + 1).trim());

				}

				else {

					Matcher m = r.matcher(line);
					// System.out.println(line);
					while (m.find()) {
						String tmp = m.group(1).substring(2,
								m.group(1).length() - 1);
						System.out.println("\\$\\{"
								+ tmp
								+ "\\}"
								+ " "
								+ Vars.getKey(tmp)
								+ " "
								+ m.group(1).substring(2,
										m.group(1).length() - 1));
						line = line.replace(m.group(1), Vars.getKey(tmp));

					}

					String[] firsta = line.split("\\s+");

					if (firsta.length > 1) {
						System.out.println(firsta.length);

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

		return false;
	}

}

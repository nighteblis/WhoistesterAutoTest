package cf.lihao;

import java.util.HashMap;
import java.util.Map;

public class Vars {
	
	// store all the vars defined in the test scripts
	// define convension:
	// ;var=value
	
	private static Map<String, String> vars = new HashMap<String, String>();
	
	public static void putKey(String key ,String value)
	{
		
		vars.put(key, value);
	}

	public static String getKey(String key)
	{
		return vars.get(key);
	}

}

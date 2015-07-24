package cf.lihao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Vars {
	
	// store all the vars defined in the test scripts
	// define convension:
	// ;var=value
	
	private static Map<String, String> vars = new HashMap<String, String>();
	private static List<String> dictionary = new ArrayList<String>();
	
	public static void putKey(String key ,String value)
	{
		
		vars.put(key, value);
	}

	public static String getKey(String key)
	{
		return vars.get(key);
	}
	
	public static List<String> getDictionary() {
		return dictionary;
	}

	public static void setDictionary(List<String> dictionary) {
		Vars.dictionary = dictionary;				
	}
	
	public static String getDictionary(int index)
	{
		
		return dictionary.get(index);
		
	}
	
	public static void addDictionary(String newWord)
	{
		dictionary.add(newWord);
	}

}

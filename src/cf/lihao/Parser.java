package cf.lihao;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import cf.lihao.textlib.HttpMiscRequest;
import cf.lihao.textlib.HttpNormalRequest;
public class Parser {
	
	String pattern = "(\\$\\{.+?\\})";
	Pattern r = Pattern.compile(pattern);
	
	public boolean parse(String script , String dic)
	{
		
        // The name of the file to open.
        String fileName = script;

        // This will reference one line at a time
        String line = null;

        try {
            // FileReader reads text files in the default encoding.
            FileReader fileReader = 
                new FileReader(fileName);

            // Always wrap FileReader in BufferedReader.
            BufferedReader bufferedReader = 
                new BufferedReader(fileReader);

            while((line = bufferedReader.readLine()) != null) {
            	
            	if(line.startsWith(";"))
            	{
            		
            		int index = line.indexOf('=');            		
            		Vars.putKey(line.substring(1,index), line.substring(index+1));            		   		
            		
            	}
            	
            	else{
            		
            		Matcher m = r.matcher(line);
            	//	System.out.println(line);
            		while(m.find())
            		{ 		
            		String tmp = m.group(1).substring(2,m.group(1).length()-1);
            		System.out.println("\\$\\{"+tmp+"\\}"+" "+Vars.getKey(tmp)+" "+m.group(1).substring(2,m.group(1).length()-1));
            		line = line.replace(m.group(1), Vars.getKey(tmp));
           	
            		}          		
            		
            		String[] firsta = line.split("\\s+");
            		
            		if(firsta.length > 1)
            		{
            		    System.out.println(firsta.length);
            		    new HttpNormalRequest().execute(firsta);
            		}
            		
            	}
            	
               // System.out.println(line);
            }    

            // Always close files.
            bufferedReader.close();            
        }
        catch(FileNotFoundException ex) {
            System.out.println(
                "Unable to open file '" + 
                fileName + "'");                
        }
        catch(IOException ex) {
            System.out.println(
                "Error reading file '" 
                + fileName + "'");                   
            // Or we could just do this: 
            // ex.printStackTrace();
        }
    
		
		return false;
	}
	
	
	
	
	
	
	
	public static void main(String []args)
	{
		
		if(args.length != 2)
		     System.out.println("error parameters");
			
		else
			
		{
			new Parser().parse(args[0],args[1]);
			
		}
		
		
		
		
		
		
	}
	
	

}

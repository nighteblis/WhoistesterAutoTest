package cf.lihao.testlib;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class TestLib {
	

	// return code , self deinfed 
	public String returnCode = "";
	public String returnResponse = "";
	public Map<String,List<String>> returnHeader = null;
	public String returnCookie = "";
	public List<String> paraDescription = null;
	public String[] para = null;
	


    public void paraDescription(String [] paradesciption)
    {
    	paraDescription = new ArrayList<String>();    	    	
    	int length = paradesciption.length;
    	for ( int i = 0 ; i < length ; i++)
    	{
    		paraDescription.add(paradesciption[i]);
    	}    	    	
    	
    }
	

}

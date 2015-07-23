package cf.lihao;

public class RunTime {

	
	public static void main(String []args)
	{
		
		if(! (args.length < 2))
		{
		     System.out.println("error parameters");
		     help();
		}	
		else
			
		{
			new Parser().parse(args[0],args[1]);
			
		}	
	}
	
	private static void help()
	{
		
		System.out.println("help output!");
		
	}
	
}

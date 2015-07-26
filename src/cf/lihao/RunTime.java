package cf.lihao;

public class RunTime {

	
	public static void main(String []args)
	{
		
		if(args.length < 2)
		{
		     System.out.println("error parameters "+args.length);
		     help();
		}	
		else
			
		{
			new Parser().parse(args[0],args[1],args[2]);
			
		}	
	}
	
	private static void help()
	{
		
		System.out.println("help output!");
		
	}
	
}

package cf.lihao.test;

public class Test {

	
	
	public static void main(String[] param)
	
	
	{
		
		String line = "          abcd edf ";
		String line2 = "sdfsdf sdfsdf ";
		String line3 = "sdfsdf sdfsdf";
		String[] firsta = line.split("\\s+");
		
		System.out.println(firsta[0]+" --- "+firsta[1]);
		System.out.println(line2.split("\\s+").length);
		System.out.println(line3.split("\\s+").length);
		
	}
	
}

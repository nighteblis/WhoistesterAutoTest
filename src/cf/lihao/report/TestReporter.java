package cf.lihao.report;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.nio.charset.Charset;
import java.nio.charset.CharsetEncoder;

public class TestReporter {
	
	
	public static String reportPath ="";
	public static final String css ="<style>"
			+ ".stepwrapper {border:1px solid #0bb;margin:8px;background-color:#0bb;padding:8px;}"
			+ ".step , .response {float:left;padding:5px;margin:5px 5px; border:1px solid #aaa;background-color:white;}"
			+ ".clr {clear:both}"
			+ ".caseHeader {width: 100%;background-color: #bbb;padding: 10px;font-size: 1.2em;font-weight: bold;}"
			+ ""
			+ ""
			+ "</style>";
		
	public static void writeHeaderToReport(String reportPath)
	{
		try(PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(reportPath)))) {
		
	    
			
	    out.println("<!doctype html><html><head><meta charset=\"UTF-8\"><title>TestReport</title>"+css+"</head><body>"
	    		+ "<h1>TestReport</h1><hr>");
			
		}catch (IOException e) {
		    //exception handling left as an exercise for the reader
			e.printStackTrace();
		}
		
	}
	
	public static void writeFooterToReport(String reportPath)
	{
		try(PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(reportPath, true)))) {
		    
			   out.println("</body><html>");
					
				}catch (IOException e) {
				    //exception handling left as an exercise for the reader
					e.printStackTrace();
				}		
		
	}	
	
	
	public static void writeCaseStartToReport(String reportPath)
	{
		try(PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(reportPath, true)))) {
		    
			   out.println("<div class=\"case\">");
					
				}catch (IOException e) {
				    //exception handling left as an exercise for the reader
					e.printStackTrace();
				}		
		
	}	
	
	public static void writeCaseEndToReport(String reportPath)
	{
		try(PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(reportPath, true)))) {
		    
			   out.println("</div>");
					
				}catch (IOException e) {
				    //exception handling left as an exercise for the reader
					e.printStackTrace();
				}		
		
	}		
	
	public static void writeToReport( String[] args , String reponse)
	{
		CharsetEncoder encoder = Charset.forName("UTF-8").newEncoder();
		try( OutputStreamWriter out = new OutputStreamWriter(new FileOutputStream(reportPath, true),encoder)) {
		    
			out.append("<div class=\"stepwrapper\"><div class=\"step\">请求内容:</br>");
			
			int i = args.length;
			
			for (int j =0 ;j < i ; j++)
			{
				out.append("<div class=\"stepCol\">");
				out.append((j+1)+". "+args[j]);			
				out.append("</div>");
			}
			out.append("</div>");
			
			out.append("<div class=\"response\">返回结果:</br>");
			
			out.append(reponse);
			out.append("</div>");
			out.append("<div class=\"clr\"></div></div>");
			
			out.close();
		}catch (IOException e) {
		    //exception handling left as an exercise for the reader
			e.printStackTrace();
		}
		
		
	}
	
	public static void writeCaseHeaderToReport( String reportPath , String caseName)
	{
		CharsetEncoder encoder = Charset.forName("UTF-8").newEncoder();
		try( OutputStreamWriter out = new OutputStreamWriter(new FileOutputStream(reportPath, true),encoder)) {
		    
			out.append("<div class=\"caseHeader\">"+caseName+"</div>");
		
			out.close();
		}catch (IOException e) {
		    //exception handling left as an exercise for the reader
			e.printStackTrace();
		}
		
		
	}	
	

}

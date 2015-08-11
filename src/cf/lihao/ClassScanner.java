package cf.lihao;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.Arrays;

import cf.lihao.testlib.TestLibAnnotation;

public class ClassScanner {
	
	private static File RunningLocation; 

	public void Scanner() throws IOException, ClassNotFoundException, InstantiationException, IllegalAccessException {

		String current = new java.io.File(".").getCanonicalPath();
		System.out.println("Current dir:" + current);
		String currentDir = System.getProperty("user.dir");
		System.out.println("Current dir using System:" + currentDir);
		ArrayList<File> a = new ArrayList<File>();
		listf(currentDir, a);

	}

	public void listf(String directoryName, ArrayList<File> files) throws MalformedURLException, ClassNotFoundException, InstantiationException, IllegalAccessException {
		File directory = new File(directoryName);

		// get all the files from a directory
		File[] fList = directory.listFiles();
		for (File file : fList) {
			if (file.isFile()) {
			//	if (file.getName().contains("$")) continue;
				if (/* file.getName().endsWith(".jar") || */file.getName()
						.endsWith(".class")) {
					files.add(file);
					System.out.println(file.getPath());
					processclass(file);
					
				}
			} else if (file.isDirectory()) {
				listf(file.getAbsolutePath(), files);
			}
		}
	}

	public void processclass(File file) throws MalformedURLException, ClassNotFoundException, InstantiationException, IllegalAccessException {

		String filepath = file.getParent();
	    String runninglocationString = this.RunningLocation.toString();
	    int length = runninglocationString.length();
	     String packageString = filepath.substring(length+1); 
	    System.out.println("package "+packageString);
	    packageString = packageString.replace("/", ".").replace("\\", ".");
	    
	    
		URL dirUrl = new URL("file:/"+file.getParent()); // 1
		URLClassLoader cl = new URLClassLoader(new URL[] { dirUrl }, this
				.getClass().getClassLoader()); // 2
		System.out.println(packageString+"."+ file.getName().substring(0, file.getName().length() - 6));
		Class loadedClass = cl.loadClass(packageString+"."+ file.getName().substring(0, file.getName().length() - 6));
		
		try {
		Object obj = loadedClass.newInstance();

		
		// AnnotationRunner runner = new AnnotationRunner();
		Method[] methods = obj.getClass().getMethods();

		for (Method method : methods) {
			TestLibAnnotation annos = method
					.getAnnotation(TestLibAnnotation.class);
			System.out.println("======method===="+method.getName());
			if (annos != null) {
	
					System.out
							.println("got the annotation " + method.getName());
					// method.invoke(obj);

			}
		}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public static void main(String[] para) {
		
		RunningLocation = new java.io.File(ClassScanner.class.getProtectionDomain().getCodeSource().getLocation().getPath());
		
		System.out.println(RunningLocation);


			try {
				new ClassScanner().Scanner();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InstantiationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

	}

	public File getRunningLocation() {
		return RunningLocation;
	}

	public void setRunningLocation(File runningLocation) {
		RunningLocation = runningLocation;
	}

}

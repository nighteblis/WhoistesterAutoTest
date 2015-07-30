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

	public void Scanner() throws IOException, ClassNotFoundException, InstantiationException, IllegalAccessException {

		String current = new java.io.File(".").getCanonicalPath();
		System.out.println("Current dir:" + current);
		String currentDir = System.getProperty("user.dir");
		System.out.println("Current dir using System:" + currentDir);

		File f = null;
		File[] paths;

		try {
			// create new file
			f = new File(currentDir);

			// returns pathnames for files and directory
			paths = f.listFiles();

			// for each pathname in pathname array
			for (File path : paths) {
				if (path.toString().endsWith(".jar")
						|| path.toString().endsWith(".class"))
					// prints file and directory paths
					System.out.println(path);

			}

			String[] directories = f.list(new FilenameFilter() {
				public boolean accept(File current, String name) {
					return new File(current, name).isDirectory();
				}
			});
			System.out.println(Arrays.toString(directories));

		} catch (Exception e) {
			// if any error occurs
			e.printStackTrace();
		}

		ArrayList<File> a = new ArrayList<File>();

		listf(currentDir, a);

	}

	public void listf(String directoryName, ArrayList<File> files) throws MalformedURLException, ClassNotFoundException, InstantiationException, IllegalAccessException {
		File directory = new File(directoryName);

		// get all the files from a directory
		File[] fList = directory.listFiles();
		for (File file : fList) {
			if (file.isFile()) {
				if (/* file.getName().endsWith(".jar") || */file.getName()
						.endsWith(".class")) {
					files.add(file);
					System.out.println(file.getParent());
					processclass(file);
					
				}
			} else if (file.isDirectory()) {
				listf(file.getAbsolutePath(), files);
			}
		}
	}

	public void processclass(File file) throws MalformedURLException, ClassNotFoundException, InstantiationException, IllegalAccessException {

		URL dirUrl = new URL("file:/"+file.getParent()); // 1
		URLClassLoader cl = new URLClassLoader(new URL[] { dirUrl }, this
				.getClass().getClassLoader()); // 2
		Class loadedClass = cl.loadClass("cf.lihao.util."+ file.getName().substring(0, file.getName().length() - 6));
		Object obj = loadedClass.newInstance();

		// AnnotationRunner runner = new AnnotationRunner();
		Method[] methods = obj.getClass().getMethods();

		for (Method method : methods) {
			TestLibAnnotation annos = method
					.getAnnotation(TestLibAnnotation.class);
			if (annos != null) {
				try {
					System.out
							.println("got the annotation " + method.getName());
					// method.invoke(obj);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}

	}

	public static void main(String[] para) {
		
		File location = new java.io.File(ClassScanner.class.getProtectionDomain().getCodeSource().getLocation().getPath());
		
		System.out.println(location);


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

}

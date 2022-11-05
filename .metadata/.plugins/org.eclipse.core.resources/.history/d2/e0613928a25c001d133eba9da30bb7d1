package t5;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.tools.JavaCompiler;
import javax.tools.ToolProvider;

import org.json.JSONObject;

import util.Utils;

public class ServerMain {
	public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException, IOException {
		GUIServer gui = new GUIServer(8989);
		JSONObject o = Utils.loadJSON();
		o.keys().forEachRemaining((a) -> System.out.println(a));
		
		//Write file
		String source = "package userClasses; public class Test { static { System.out.println(\"hello\"); } public Test() { System.out.println(\"world\"); } }";

		// Save source in .java file.
		//File root = new File("/java"); // On Windows running on C:\, this is C:\java.
		File sourceFile = new File("userClasses/Test.java");
		sourceFile.getParentFile().mkdirs();
		Files.write(sourceFile.toPath(), source.getBytes(StandardCharsets.UTF_8));
		
		//Compile file
		JavaCompiler jCompiler = ToolProvider.getSystemJavaCompiler();
		int results = jCompiler.run(System.in, System.out, System.err, sourceFile.getPath());;
	    System.out.println("Success: " + results);
	    
	    //Load .class file
	    
	    
	    
	    //Test
	    //Class a = ClassLoader.getSystemClassLoader().loadClass("ServerMain.class");
	    //System.out.println(Class.getName());
	    //Class a = Class.forName("ServerMain", false, ClassLoader.getSystemClassLoader());
	    //System.out.println(a);
	}
	
	public static void print(String text) {
		System.out.println(text);
	}
}

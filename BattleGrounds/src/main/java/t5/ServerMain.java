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

import javax.tools.ToolProvider;

import org.json.JSONObject;

import t4.JavaEngine;
import util.Utils;

public class ServerMain {
	public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException, IOException {
		JavaEngine jEngine = new JavaEngine();
		
		GUIServer gui = new GUIServer(8989, jEngine);
		//JSONObject o = Utils.loadJSON();
		//o.keys().forEachRemaining((a) -> System.out.println(a)); //TODO use config for routing
	}
	
	public static void print(String text) {
		System.out.println(text);
	}
}

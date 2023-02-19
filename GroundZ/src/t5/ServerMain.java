package t5;

import java.io.IOException;

import t4.JavaEngine;

public class ServerMain {
	public static void main(String[] args){
		JavaEngine jEngine = null;//new JavaEngine();
		
		GUIServer gui = new GUIServer(8989, "resources/config.json", jEngine);
		//JSONObject o = Utils.loadJSON();
		//o.keys().forEachRemaining((a) -> System.out.println(a)); //TODO use config for routing
	}
	
	public static void print(String text) {
		System.out.println(text);
	}
}

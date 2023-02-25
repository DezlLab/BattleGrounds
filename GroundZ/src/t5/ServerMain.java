package t5;

import java.io.IOException;

import t4.CodeRunner;

public class ServerMain {
	public static void main(String[] args){
		CodeRunner jEngine = new CodeRunner(false);
		
		GUIServer gui = new GUIServer(8989, "resources/config.json", jEngine);
		//JSONObject o = Utils.loadJSON();
		//o.keys().forEachRemaining((a) -> System.out.println(a)); //TODO use config for routing
	}
}

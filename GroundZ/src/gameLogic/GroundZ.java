package gameLogic;

import java.util.ArrayList;

import org.json.JSONObject;

import t1.Grid;
import t1.Player;
import t1.Vector2Df;
import t4.CodeRunner;
import t5.ClientSystem;
import t5.GUIServer;
import t5.ServerPacket;
import util.Utils;

public class GroundZ {
	private static Player player;
	
	public static void main(String[] args) {
		Grid grid = new Grid(20,20);
		player = new Player(new Vector2Df(5.0f,5.0f),grid);
		
		ClientSystem clientSystem = new ClientSystem();
		String[] clientArgs = {"Luca", "Theo"};
		CodeRunner jEngine = new CodeRunner(clientSystem, player, false);
		jEngine.setVar("clientSystem", clientSystem);
		jEngine.setVar("player", player);
		jEngine.setVar("args", clientArgs);
		GUIServer gui = new GUIServer(8989, "resources/config.json", jEngine);
	}
}


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
	private Player player;
	private Grid grid ;
	public GroundZ() {
		grid = new Grid(4,4);
		newPlayer();
		
		ClientSystem clientSystem = new ClientSystem();
		String[] clientArgs = {"Luca", "Theo"};
		CodeRunner jEngine = new CodeRunner(clientSystem, player, false ,grid);
		jEngine.setVar("clientSystem", clientSystem);
		jEngine.setVar("player", player);
		jEngine.setVar("args", clientArgs);
		GUIServer gui = new GUIServer(8989, "resources/config.json", jEngine);
	}
	public void newPlayer()
	{
		System.out.println("NEw Pläayor");
		player = new Player(new Vector2Df(1.0f,1.0f),grid);
	}
}


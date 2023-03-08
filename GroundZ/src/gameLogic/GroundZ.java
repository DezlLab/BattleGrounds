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
		grid = new Grid(5,4);
		player = new Player(new Vector2Df(1.0f,1.0f),grid);
		
		ClientSystem clientSystem = new ClientSystem();
		String[] clientArgs = {"Luca", "Theo"};
		CodeRunner jEngine = new CodeRunner(clientSystem, player, false ,grid);
		jEngine.setVar("clientSystem", clientSystem);
		jEngine.setVar("player", player);
		jEngine.setVar("args", clientArgs);
		GUIServer gui = new GUIServer(8989, "resources/config.json", jEngine, this);
	}
	
	public void newPlayer()
	{
		player = new Player(new Vector2Df(1.0f,1.0f),grid);
	}
	
	public void newGrid() {
		grid.newRandomGrid();
	}
	
	public void sendGrid(ServerPacket packet, boolean newGrid) {
		if(newGrid) {
			grid.newRandomGrid();
		}
		JSONObject dataToSend = new JSONObject();
		dataToSend.accumulate("grid", grid.objectsToPng());
		//System.out.println("::"+player.toString());
		dataToSend.accumulate("size", grid.size());
		dataToSend.accumulate("textData", "");

		dataToSend.accumulate("playerMoves", "");
		player.resetPlayer();
		
		packet.sendResponse(dataToSend.toString().getBytes());
	}
}


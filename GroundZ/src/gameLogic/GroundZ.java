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
		CodeRunner jEngine = new CodeRunner(clientSystem, false);
		GUIServer gui = new GUIServer(8989, "resources/config.json", jEngine);
		jEngine.setVar("player", player, "");
		jEngine.setVar("jEngine", jEngine, "");//TODO make CodeHandler or Client to split jEngine and this

//		while(true) {
//			try {
//				Thread.sleep(500);
//			} catch (InterruptedException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//	        ArrayList<ServerPacket> packetList = gui.getPacketList();
//	        boolean[] clientRunning = {true};
//	        System.out.println("Process Queue"+packetList);
//	        if(packetList.size() > 0) {
//	        	if(clientRunning[0]) {
//	        		String toSend = clientSystem.stripString();
//	        		
//	        		JSONObject dataToClient = new JSONObject();
//	        		dataToClient.accumulate("endOfData", false);
//	        		dataToClient.accumulate("textData", toSend);
//	        		packetList.get(0).sendResponse(dataToClient.toString().getBytes());
//			        packetList.remove(0);
//	        	}
//	        }
//		}	
	}
	
	public static Player getPlayer() {
		return player;
	}
}

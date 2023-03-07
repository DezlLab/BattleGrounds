package t5;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.ArrayList;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpServer;

import gameLogic.GroundZ;
import t1.Grid;
import t1.Player;
import t1.Vector2Df;
import t4.CodeRunner;
import util.Utils;



public class GUIServer{
	private HttpServer server;
	private CodeRunner jEngine;
	private GroundZ groundZ;
	
	public GUIServer() { this(8080);}
	
	public GUIServer(int port) { 
		ServerPacket.setResourcesPath("resources");
		
		try {
			server = HttpServer.create(new InetSocketAddress(port), 0);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public GUIServer(int port, String configPath, CodeRunner jEngine, GroundZ groundZ) {
		this(port);
		this.groundZ = groundZ;
		
		this.jEngine = jEngine;
		server.createContext("/", httpExchange -> requestHandler(httpExchange));
		server.setExecutor(null);
		server.start();
		Utils.display("Run at : http://127.0.1.1:"+port);
		int index = configPath.lastIndexOf('/');
		if(index >= 0) {
			ServerPacket.setResourcesPath( configPath.substring(0, index));
		}
	}
	
	private void requestHandler(HttpExchange httpExchange) {
		ServerPacket packet = new ServerPacket(httpExchange);
		if(packet.isResource()) {
			packet.sendResponse(packet.getBytes());
		}
		else {
			if(packet.getResourceType().equals("newGrid")) {
				groundZ.newGrid();
			}
			jEngine.handel(packet);
		}
	}
}
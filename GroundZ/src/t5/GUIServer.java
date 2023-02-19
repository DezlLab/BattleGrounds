package t5;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.InetSocketAddress;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.Base64;
import java.util.HashMap;

import org.json.JSONObject;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

import t4.JavaEngine;
import util.Utils;



public class GUIServer {
	private HttpServer server;
	private JavaEngine jEngine;
	
	public GUIServer() { this(8080);}
	
	public GUIServer(int port) { 
		ServerPacket.setResourcesPath("resources");
		
		try {
			server = HttpServer.create(new InetSocketAddress(port), 0);
			server.createContext("/", httpExchange -> requestHandler(httpExchange));
			server.setExecutor(null);
			server.start();
			Utils.display("Run at : http://127.0.1.1:"+port);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public GUIServer(int port, String configPath, JavaEngine jEngine) {
		this(port);
		this.jEngine = jEngine;
		
		int index = configPath.lastIndexOf('/');
		if(index >= 0) {
			ServerPacket.setResourcesPath( configPath.substring(0, index));
		}
	}
	
	private void requestHandler(HttpExchange httpExchange) {
		ServerPacket packet = new ServerPacket(httpExchange);
		try {
			Thread.sleep(0);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(packet.isResource()) {
			packet.sendResponse(packet.getBytes());
		}
		else {
			System.out.println("jError");
			//jEngine.run(packet);
			//packet.sendResponse("The http is sus".getBytes());
		}
	}
}
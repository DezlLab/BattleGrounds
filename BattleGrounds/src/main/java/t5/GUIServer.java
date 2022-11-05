package t5;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;

import org.json.JSONObject;

import com.sun.net.httpserver.HttpServer;
import com.sun.net.httpserver.HttpExchange;


public class GUIServer {
	private HttpServer server;
	private JSONObject config;
	
	public GUIServer() { this(8080);}
	
	public GUIServer(int port) { 
		this.config = null;
		try {
			server = HttpServer.create(new InetSocketAddress(port), 0);
			
			server.createContext("/", httpExchange -> requestHandler(httpExchange));
			
			server.start();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public GUIServer(int port, JSONObject config) {
		this(port);
		this.config = config;
	}
	
	private void requestHandler(HttpExchange httpExchange) throws IOException {
		String requestURI = httpExchange.getRequestURI().toASCIIString();
		String response = requestURI.equals("/") ? "<h1>GUIServer 0.3<h1> <button onclick=\"fetch('java.left')\"><<</button><button onclick=\"fetch('java.right')\">>></button>" : "404 ERROR";
		System.out.println(requestURI);
		
	    httpExchange.getResponseHeaders().add("Content-Type", "text/html; charset=UTF-8");
	    httpExchange.sendResponseHeaders(200, response.length());

	    OutputStream out=httpExchange.getResponseBody();
	    out.write(response.getBytes("UTF-8"));
	    out.close();
	}
}

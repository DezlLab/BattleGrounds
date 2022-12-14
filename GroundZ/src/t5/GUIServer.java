package t5;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.InetSocketAddress;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;

import org.json.JSONObject;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

import t4.JavaEngine;
import util.Utils;



public class GUIServer {
	private HttpServer server;
	private String resourcesPath;
	private String configFileName;
	private HashMap<String, String> responses;
	private ClientSystem clientSystem;
	
	public GUIServer() { this(8080);}
	
	//TODO only test Constructor
	private JavaEngine jEngine;
	
	public GUIServer(int port) { 
		this.resourcesPath = "resources";
		this.responses = new HashMap<String, String>();
		
		try {
			server = HttpServer.create(new InetSocketAddress(port), 0);
			server.createContext("/", httpExchange -> requestHandler(httpExchange));
			server.start();
			System.out.println("Run at : http://127.0.1.1:"+port);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public GUIServer(int port, String configPath, JavaEngine jEngine) {
		this(port);
		this.jEngine = jEngine;
		this.clientSystem = jEngine.getClientSystem();
		
		int index = configPath.lastIndexOf('/');
		if(index >= 0) {
			this.resourcesPath = configPath.substring(0, index);
			this.configFileName = configPath.substring(index);
		}
		loadResponses();
		System.out.println(this.responses.keySet());
	}
	
	private void loadResponses() {
		responses.clear();
		JSONObject config = Utils.loadJSON(resourcesPath + "/config.json");
		if(config == null) {
			//Setup in case of no config
			responses.put("/", "<h1>GUI Server v0.96</h1><p>Project by Luca P. and Theo L.</p><p style=\"color:#fff; background:#f00; padding:10px; border-radius:10px;\"> No config was found </p>");
			responses.put("default", "<h3>404 ERROR</h3><p>Project by Luca P. and Theo L.</p><p style=\"color:#fff; background:#f00; padding:10px; border-radius:10px;\"> No config was found</p>");
			System.err.println("loadResponses :: ERROR at : no config");
		}
		else {
			config.keys().forEachRemaining((request) -> {
				//Put each request String for later requestHandling in Key and use the value of config to locate the html file
				String page = Utils.readFile(resourcesPath +"/"+config.getString(request), StandardCharsets.UTF_8);
				if (page == null) {
					System.err.print("loadPage :: ERROR");
					page = "Page not found";
				}
				//System.out.println(request+","+config.getString(request));
				
				responses.put(request, page);
			});
		}
		
		
	}
	
	private void requestHandler(HttpExchange httpExchange) throws IOException {
		//debug
		loadResponses();
		
		String requestURI = httpExchange.getRequestURI().toASCIIString();

		
		String response = responses.get(requestURI);
		if (response == null) {
			response = responses.get("default");
			if (response == null) {
				System.err.println("No Default Page found");
			}
		}
		
		
		//TODO add to switch case loop at moment only one reaction
		if(requestURI.indexOf("java.") != -1) {
			String sourceCode = Utils.inputStreamToString(httpExchange.getRequestBody());
			jEngine.setSourceCode(sourceCode);
			jEngine.run(new String[] {"Conny", "LOL"});
			//response = sysBuffer.toString();
			//System.out.println(":::"+response);
			//response = "lol";
			response = clientSystem.stripString();
		}
		String textType = "html";
		int index = requestURI.indexOf('.');
		if(index != -1) {
			textType = requestURI.substring(index + 1);
		}
		
		System.err.println(":::"+requestURI+"; >"+textType);
		System.err.println(Utils.inputStreamToString(httpExchange.getRequestBody()));
		
		httpExchange.getResponseHeaders().add("Content-Type", "text/"+textType+"; charset=UTF-8");
	    httpExchange.sendResponseHeaders(200, response.length());

	    OutputStream out = httpExchange.getResponseBody();
	    out.write(response.getBytes("UTF-8"));
	    out.close();
	}
}

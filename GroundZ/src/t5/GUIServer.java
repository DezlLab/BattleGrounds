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
	private String resourcesPath;
	private String configFileName;
	private HashMap<String, byte[]> responses;
	private ClientSystem clientSystem;
	
	public GUIServer() { this(8080);}
	
	//TODO only test Constructor
	private JavaEngine jEngine;
	
	public GUIServer(int port) { 
		this.resourcesPath = "resources";
		this.responses = new HashMap<String, byte[]>();
		
		try {
			server = HttpServer.create(new InetSocketAddress(port), 0);
			server.createContext("/", httpExchange -> requestHandler(httpExchange));
			server.setExecutor(null);
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
		//System.out.println(this.responses.values());
	}
	
	private void loadResponses() {
		responses.clear();
		JSONObject config = Utils.loadJSON(resourcesPath + "/config.json");
		if(config == null) {
			//Setup in case of no config
			responses.put("/", "Page ERROR 404".getBytes());
			responses.put("default", "ERROR 404".getBytes());
			System.err.println("loadResponses :: ERROR at : no config");
		}
		else {
			config.keys().forEachRemaining((request) -> {
				//Put each request String for later requestHandling in Key and use the value of config to locate the html file
				byte[] data = Utils.readFile(resourcesPath +"/"+config.getString(request));
				if (data == null) {
					System.err.print("loadPage :: ERROR");
					data = "Page not found".getBytes();
				}
				//System.out.println(request+","+config.getString(request));
				
				responses.put(request, data);
			});
		}
		
		
	}
	
	private void requestHandler(HttpExchange httpExchange) throws IOException {
		
		httpExchange.getResponseHeaders().add("Content-Type", "image/png");
		File file = new File ("resources/icon.png");
		byte[] bytes  = new byte [(int)file.length()];
        System.out.println(file.getAbsolutePath());
        System.out.println("length:" + file.length());
        
        FileInputStream fileInputStream = new FileInputStream(file);
        BufferedInputStream bufferedInputStream = new BufferedInputStream(fileInputStream);
        bufferedInputStream.read(bytes, 0, bytes.length);
        
        System.out.println(">>>"+Base64.getEncoder().encodeToString(bytes));
        
        httpExchange.sendResponseHeaders(200, file.length());
        OutputStream outputStream = httpExchange.getResponseBody();
        outputStream.write(bytes, 0, bytes.length);
        outputStream.close();
		
        ////
        
//		String requestURI = httpExchange.getRequestURI().toASCIIString();
//		System.out.println("=="+requestURI);
//		
//		byte[] response = "".getBytes();
//		short statusFlag = 205;
//		String textType = "image/png";
//		
//		File file = new File("resources/icon.png");
//		
//		httpExchange.getResponseHeaders().add("Content-Type", ""+textType+"");
//		httpExchange.sendResponseHeaders(statusFlag, response.length);
//		
//	    OutputStream out = httpExchange.getResponseBody();
//	    
//	    Files.copy(file.toPath(), out);
	    
        ///
        
		//out.write(response);
	    //out.close();
//		//debug
//		loadResponses();
//		
//		String requestURI = httpExchange.getRequestURI().toASCIIString();
//		
//		
//		System.out.println(httpExchange.getRequestHeaders().keySet());
//		System.out.println(httpExchange.getRequestHeaders().values());
//		byte[] response = responses.get(requestURI);
//		if (response == null) {
//			response = responses.get("default");
//			if (response == null) {
//				System.err.println("No Default Page found");
//			}
//		}
//		
//		
//		//TODO add to switch case loop at moment only one reaction
//		if(requestURI.indexOf("java.") != -1) {
//			String sourceCode = Utils.inputStreamToString(httpExchange.getRequestBody());
//			jEngine.setSourceCode(sourceCode);
//			jEngine.run(new String[] {"Conny", "LOL"});
//			//response = sysBuffer.toString();
//			//System.out.println(":::"+response);
//			//response = clientSystem.stripString();
//			response = "lol".getBytes();
//		}
//		String textType = "html";
//		int index = requestURI.indexOf('.');
//		if(index != -1) {
//			textType = requestURI.substring(index + 1);
//		}
//		
//		System.err.println(":::"+requestURI+"; >"+textType);
//		//System.err.println(Utils.inputStreamToString(httpExchange.getRequestBody()));
//		int state = 200;
//		switch(textType) {
//		case "html": textType = "text/html"; break;
//		case "css": textType = "text/css"; break;
//		case "js": textType = "text/javascript"; break;
//		case "png": textType = "image/png"; state = 205; break;
//		}
//		//textType = httpExchange.getRequestHeaders().getFirst("Sec-fetch-dest") +"/"+ textType;
//		//textType = textType.substring(0, textType.indexOf(",")); 
//		System.out.println(">>>"+textType);
//		System.out.println(">>>"+new String(response));
//		//System.out.println(">>>"+Base64.getEncoder().encodeToString(response));
//		
//		//httpExchange.getResponseHeaders().add("Content-Type", ""+textType+"; charset=UTF-8");
//		httpExchange.getResponseHeaders().add("Content-Type", ""+textType+"");
//		httpExchange.sendResponseHeaders(state, response.length);
//		
//	    OutputStream out = httpExchange.getResponseBody();
//	    out.write(response);
//	    out.close();
	}
}

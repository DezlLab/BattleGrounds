package t5;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;

import com.sun.net.httpserver.HttpExchange;

import util.Utils;

public class ServerPacket
{
	private static String resourcesPath = "resources";
	//all needed from https://www.iana.org/assignments/media-types/media-types.xhtml
	//TODO add in findResourceType the mimeTypes in switch case
	//private static HashMap<String, String> mimeTypes = new HashMap();
	private HttpExchange httpExchange;
	private PacketInfo packetInfo;
//	private String resourceType;
//	private boolean isResource;
	private File file;
	private int statusFlag = 200;

	static public void setResourcesPath(String path) {
		resourcesPath = path;
	}
	//TODO at moment no way to go to server
	public ServerPacket() {
		file = loadFallback();
	}
	
	public ServerPacket(HttpExchange httpExchange) {
		this.httpExchange = httpExchange;
		String requestURI = httpExchange.getRequestURI().toASCIIString();
		packetInfo = new PacketInfo(requestURI);
		if(packetInfo.isResource()) {
			file = load(requestURI);
			Utils.debug("Load : "+requestURI);
		}
	}
	
	public void sendResponse(byte[] bytes) {
		httpExchange.getResponseHeaders().add("Content-Type", packetInfo.getResourceType());
		
	    try {
			httpExchange.sendResponseHeaders(statusFlag, bytes.length);
			
			OutputStream outputStream = httpExchange.getResponseBody();
	    	outputStream.write(bytes);
	    	outputStream.close();
		    } catch (IOException e) {
		    	Utils.logErr("Load Error : "+file.toURI());
			}
	}
	
	public byte[] getBytes() {
		
		InputStream inputStream;
		byte[] bytes = null;
        try {
        	if(isResource()) {
        		inputStream = new FileInputStream(file);
        		bytes = new byte [(int)file.length()];
        	}
        	else {
        		inputStream = httpExchange.getRequestBody();
        		bytes = new byte [inputStream.available()];
        	}
        	BufferedInputStream bufferedInputStream = new BufferedInputStream(inputStream);
			bufferedInputStream.read(bytes, 0, bytes.length);
		} catch (IOException e) {
			e.printStackTrace();
			statusFlag = 400;
		}
        return bytes;
	}
	
	public HttpExchange getHttpExchange() {
		return this.httpExchange;
	}
	
	public int getStatusFlag() {
		return statusFlag;
	}
	
	public File load(String path) {
		if(path.contentEquals("/"))
		{
			path = "/index.html";
		}
		if(path.indexOf(".") == -1)
		{
			path += ".html";
		}
		File file = new File(resourcesPath+path);
		if(file.canRead())
		{
			this.statusFlag = 200;
		}
		else
		{
			file = loadFallback();
		}
		return file;
	}
	
	public File loadFallback() {
		File file = new File(resourcesPath+"/error404.html");
		return file;
	}
	
	public String getResourceType() {
		return packetInfo.getResourceType();
	}
	
	public boolean isResource() {
		return packetInfo.isResource();
	}
}
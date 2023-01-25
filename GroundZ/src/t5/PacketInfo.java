package t5;

import java.sql.SQLSyntaxErrorException;

import util.Utils;

public class PacketInfo {
	
	//all needed from https://www.iana.org/assignments/media-types/media-types.xhtml
	//TODO add in findResourceType the mimeTypes in switch case
	//private static HashMap<String, String> mimeTypes = new HashMap();
	private boolean isResource;
	private String resourceType;
	
	public PacketInfo(String path) {
		System.err.println(path);
		isResource = true;
		int index = path.indexOf(".");
		resourceType = "text";
		if(index != -1) {
			resourceType = path.substring(index + 1);
			if(path.indexOf("java.") != -1) {
				isResource = false;
			}
			else {
				switch(resourceType) {
					case "html": resourceType = "text/html"; break;
					case "css": resourceType = "text/css"; break;
					case "js": resourceType = "text/javascript"; break;
					case "png": resourceType = "image/png"; break;
				}
			}
		}
	}
	
	public boolean isResource() {
		return isResource;
	}
	
	public String getResourceType() {
		return resourceType;
	}
}

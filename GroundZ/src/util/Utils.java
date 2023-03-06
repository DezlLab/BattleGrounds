package util;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.json.JSONException;
import org.json.JSONObject;

public class Utils {
	
	private final static boolean DEBUG = true;
	private final static boolean LOGERR = true;
	private final static boolean DISPLAY = true;
	
	public static void debug(String str) {
		if(DEBUG) {
			System.out.println(str);
		}
	}
	
	public static void logErr(String str) {
		if(LOGERR) {
			System.err.println(str);
		}
	}
	
	public static void display(String str) {
		if(DISPLAY) {
			System.out.println(str);
		}
	}
	
	public static JSONObject loadJSON(String path) {
		
		JSONObject result = null;
		try {
			result = new JSONObject(new String(readFile(path)));
		}catch (JSONException err){
			System.err.println("loadJSON :: ERROR");
		}
		return result;
	}
	
	public static byte[] readFile(String path){
		byte[] encoded = null;
		try {
			encoded = Files.readAllBytes(Paths.get(path));
		} catch (IOException e) {
			System.err.println("readFile :: ERROR at :"+path);
			e.printStackTrace();
			return null;
		}
		return encoded;
	}
	
	public static String inputStreamToString(InputStream inputStream) {
		//Source https://stackoverflow.com/questions/309424/how-do-i-read-convert-an-inputstream-into-a-string-in-java
	ByteArrayOutputStream result = new ByteArrayOutputStream();
	byte[] buffer = new byte[1024];//TODO dynamic
	try {
		for (int length; (length = inputStream.read(buffer)) != -1; ) {
		    result.write(buffer, 0, length);
		}
		return result.toString("UTF-8");
	} catch (IOException e) {
		e.printStackTrace();
	}
	return "inputStreamToString READ Error";
	}
}

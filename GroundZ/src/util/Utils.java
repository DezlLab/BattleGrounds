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
	public static JSONObject loadJSON(String path) {
		
		JSONObject result = null;
		try {
			result = new JSONObject(readFile(path, StandardCharsets.UTF_8));
		}catch (JSONException err){
			System.err.println("loadJSON :: ERROR");
		}
		return result;
	}
	
	public static String readFile(String path, Charset encoding){
		byte[] encoded = null;
		try {
			encoded = Files.readAllBytes(Paths.get(path));
		} catch (IOException e) {
			System.err.println("readFile :: ERROR at :"+path);
			e.printStackTrace();
			return null;
		}
		return new String(encoded, encoding);
	}
	
	public static String inputStreamToString(InputStream inputStream) throws IOException {
		//Source https://stackoverflow.com/questions/309424/how-do-i-read-convert-an-inputstream-into-a-string-in-java
	ByteArrayOutputStream result = new ByteArrayOutputStream();
	byte[] buffer = new byte[1024];
	for (int length; (length = inputStream.read(buffer)) != -1; ) {
	    result.write(buffer, 0, length);
	}
	// StandardCharsets.UTF_8.name() > JDK 7
	return result.toString("UTF-8");
	}
}

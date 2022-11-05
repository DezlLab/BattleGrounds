package util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.json.JSONException;
import org.json.JSONObject;

public class Utils {
	public static JSONObject loadJSON() {
		
		JSONObject result = null;
		try {
			result = new JSONObject(readFile("src/main/resources/config.json", StandardCharsets.UTF_8));
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
			System.err.println("readFile :: ERROR");
			e.printStackTrace();
		}
		return new String(encoded, encoding);
	}
}

package t4;

import java.awt.Color;
import java.util.ArrayList;
import java.util.HashMap;

public class InterpreterPlan {
	
	private String name;
	private class ConversionData{
		private String conversionType;
		private String[] conversionParameters;
		public ConversionData(String conversionType, String[] conversionParameters) {
			this.conversionType = conversionType;
			this.conversionParameters = conversionParameters;
		}
		public String getConversionType() {
			return this.conversionType;
		}
		public String[] getConversionParameters() {
			return this.conversionParameters;
		}
	}
	
	
	private ArrayList<ConversionData> conversionData;
	
	public interface ConversionOperation{
		String convert(String code, String[] parameters);
	}
	private static final HashMap<String, ConversionOperation> conversionOperations = new HashMap<String, ConversionOperation>() {
		{
			put("replc", (String code, String[] parameters) ->{
				while(code.indexOf(parameters[0]) != -1) {
					code = code.replace(parameters[0], parameters[1]);
				}
				return code;
			});
			put("add", (String code, String[] parameters) ->{
				int index = -1;
				switch(parameters[0]) {
				case "outer": index = 0; break;
				case "inner": index = code.indexOf("{") + 1; break;
				case "end": index = code.length(); break;
				}
				code = code.substring(0, index) + parameters[1] + code.substring(index);
				return code;
			});
			put("remov", (String code, String[] parameters) ->{
				while(code.indexOf(parameters[0]) != -1) {
					code = code.replace(parameters[0], "");
				}
				return code;
			});
			//put("",conversionOperationFunctions[2]);
		}
	};
	
	public InterpreterPlan() {
		this("null");
	}
	
	public InterpreterPlan(String name) {
		this.name = name;
		this.conversionData = new ArrayList<ConversionData>();
	}
	
	public String getName() {
		return this.name;
	}
	
	public void addConversion(String from, String to) {
		conversionData.add(new ConversionData("replc", new String[]{from, to}));
	}
	
	public void addStatment(String at, String str) {
		conversionData.add(new ConversionData("add", new String[]{at, str}));
	}
	
	public void removeStatment(String str) {
		conversionData.add(new ConversionData("remov", new String[]{str}));
	}
	
	public String convertCode(String code) {
		int i = 0;
		for(ConversionData conversion : conversionData) {
			ConversionOperation c = conversionOperations.get(conversion.getConversionType());
			code = c.convert(code, conversion.getConversionParameters());
		}
		return new String(code);
	}
	
	public void addLintingKeys(String[] keys, Color col) {
		
	}
	
	public void addLintingBetween(String[] keys, Color col) {
		
	}
}

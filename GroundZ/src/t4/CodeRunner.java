package t4;

import java.util.ArrayList;

import javax.script.Bindings;
import javax.script.Compilable;
import javax.script.CompiledScript;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

import codeSupport.CodeTransformer;
import t5.ClientSystem;
import t5.ServerPacket;
import util.Utils;

public class CodeRunner {
	
	private boolean debug;
	private ArrayList<String> codeSupportedLangs;
	private String curLang;
	
	private ClientSystem clientSystem;
	private CodeTransformer codeTransformer;
	private Interpreter interpreter;
	
	private Compilable compiler;
	private CompiledScript compiledCode;
	private Bindings bindings;
	
	
	public CodeRunner() {
		this(true);
	}
	
	public CodeRunner(boolean debug) {
		this.debug = debug;
		this.codeSupportedLangs = new ArrayList<>();
		this.clientSystem = new ClientSystem();
		this.codeTransformer = new CodeTransformer();
		this.interpreter = new Interpreter();
		ScriptEngineManager manager = new ScriptEngineManager();
	    ScriptEngine engine = manager.getEngineByName("java");
	    compiler = (Compilable) engine;
	    bindings = engine.createBindings();
	    
	    addSupportedLang("java");
	    setSupportedLang("java");
	    
	}
	
	public void handel(ServerPacket packet) {
		Utils.debug(":: "+packet.getResourceType());
		String code = new String(packet.getBytes());
		code = interpreter.convert(code, curLang);
		compile(code);
		run(null);
		packet.sendResponse(clientSystem.stripString().getBytes());
	}
	
	public void compile(String code) {
		try {
			compiledCode = compiler.compile(code);
		} catch (ScriptException e) {
			e.printStackTrace();
		}
	}
	
	public void run(String[] args) {
		setVar("args", new String[]{"Luca", "Theo"});
		setVar("clientSystem", clientSystem);
		try {
			Object result = compiledCode.eval(bindings);
		} catch (ScriptException e) {
			e.printStackTrace();
		}
	}
	
	public void setSupportedLang(String lang) {
		if(this.codeSupportedLangs.contains(lang)) {
			curLang = lang;
			if(debug) {
				curLang += "-debug";
			}
			Utils.display(lang+" : Supported");
		}
		else {
			Utils.display(lang+" : notSupported");
		}
	}
	
	public void addSupportedLang(String lang) {
		this.codeSupportedLangs.add(lang);
	}
	
	public void setVar(String varName, Object value) {
		bindings.put(varName, value);
	}
	
	public boolean isDebug() {
		return this.debug;
	}
	
	public void setDebug(boolean debug) {
		this.debug = debug;
	}
}
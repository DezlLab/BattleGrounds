package t4;

import java.util.ArrayList;

import javax.script.Bindings;
import javax.script.Compilable;
import javax.script.CompiledScript;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

import org.json.JSONObject;

import codeSupport.CodeTransformer;
import gameLogic.GameAction;
import t1.Player;
import t1.Vector;
import t1.Vector2Di;
import t5.ClientSystem;
import t5.GUIServer;
import t5.ServerPacket;
import util.Utils;

public class CodeRunner {
	
	private boolean debug;
	private ArrayList<String> codeSupportedLangs;
	private String curLang;
	
	private ClientSystem clientSystem;
	private Player player;
	
	private Interpreter interpreter;
	
	private Compilable compiler;
	private CompiledScript compiledCode;
	public Bindings bindings;
	
	public CodeRunner(ClientSystem clientSystem, Player player, boolean debug) {
		this.player = player;
		this.debug = debug;
		this.codeSupportedLangs = new ArrayList<>();
		this.clientSystem = clientSystem;
		this.interpreter = new Interpreter();
		ScriptEngineManager manager = new ScriptEngineManager();
	    ScriptEngine engine = manager.getEngineByName("java");
	    compiler = (Compilable) engine;
	    bindings = engine.createBindings();
	    
	    addSupportedLang("java");
	    setSupportedLang("java");
	    
	    addSupportedLang("playGroundStyle");
	    setSupportedLang("playGroundStyle");
	}
	
	public void handel(ServerPacket packet) {
		Utils.debug("==> "+packet.getResourceType());
		String code = new String(packet.getBytes());
		code = interpreter.convert(code, curLang);
		if(debug) {
			Utils.debug(code);
		}
		compile(code);
		run(null);
		
		JSONObject dataToSend = new JSONObject();
		dataToSend.accumulate("endOfData", false);
		dataToSend.accumulate("textData", clientSystem.stripString());
		dataToSend.accumulate("playerMoves", player.getActions());
		packet.sendResponse(dataToSend.toString().getBytes());
	}
	
	public void compile(String code) {
		try {
			compiledCode = compiler.compile(code);
		} catch (ScriptException e) {
			e.printStackTrace();
		}
	}
	
	public void run(String[] args) {
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
		InterpreterPlan iPlan = interpreter.getInterpreterPlan(curLang);
		//Utils.debug("public static "+value.getClass().getSimpleName()+" "+varName+";");
		iPlan.addStatment("inner", "\npublic static "+value.getClass().getSimpleName()+" "+varName+";");
		if(!value.getClass().isArray()) {
			iPlan.addStatment("outer", "import "+value.getClass().getName()+";\n");
		}
		else {
			String arrayPath = value.getClass().getTypeName();
			arrayPath = arrayPath.replace("[]", "");
			iPlan.addStatment("outer", "import "+arrayPath+";\n");
		}
		bindings.put(varName, value);
	}
	
	public boolean isDebug() {
		return this.debug;
	}
	
	public void setDebug(boolean debug) {
		this.debug = debug;
	}
}
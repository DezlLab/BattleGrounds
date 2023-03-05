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
import t1.Vector;
import t1.Vector2Di;
import t5.ClientSystem;
import t5.GUIServer;
import t5.ServerPacket;
import util.Utils;
import t1.Player;
public class CodeRunner {
	
	private boolean debug;
	private ArrayList<String> codeSupportedLangs;
	private String curLang;
	
	private ClientSystem clientSystem;
	private CodeTransformer codeTransformer;
	private Interpreter interpreter;
	
	private Compilable compiler;
	private CompiledScript compiledCode;
	public Bindings bindings;
	
	private ArrayList<Vector> playerMoves ;
	private Player player;
	
	public CodeRunner(ClientSystem clientSystem, boolean debug) {
		
		this.debug = debug;
		this.codeSupportedLangs = new ArrayList<>();
		this.clientSystem = clientSystem;
		this.codeTransformer = new CodeTransformer();
		this.interpreter = new Interpreter();
		player = new Player();
		playerMoves = new ArrayList<Vector>();
		ScriptEngineManager manager = new ScriptEngineManager();
	    ScriptEngine engine = manager.getEngineByName("java");
	    compiler = (Compilable) engine;
	    bindings = engine.createBindings();
	    
	    addSupportedLang("java");
	    setSupportedLang("java");
	    
	    addSupportedLang("playGroundStyle");
	    setSupportedLang("playGroundStyle");
	    setVar("clientSystem", clientSystem);
	}
	
	public void handel(ServerPacket packet) {
		Utils.debug("==> "+packet.getResourceType());
		String code = new String(packet.getBytes());
		code = interpreter.convert(code, curLang);
		System.out.println("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaa"+code +"aaaaaaaaaaaaaaaaaa");
		Utils.debug(code);
		compile(code);
		run(null);
		JSONObject dataToSend = new JSONObject();
		dataToSend.accumulate("endOfData", false);
		dataToSend.accumulate("textData", clientSystem.stripString());
		
		
		playerMoves.add(new Vector2Di(0, 0));
//		playerMoves.add(new Vector2Di(1, 1));
//		playerMoves.add(new Vector2Di(1, 1));
//		playerMoves.add(new Vector2Di(1, 1));
//		playerMoves.add(new Vector2Di(0, -1));
		dataToSend.accumulate("playerMoves", playerMoves);//////HIER player moves hin ArrayList<Vector>
		packet.sendResponse(dataToSend.toString().getBytes());
		//packet.sendResponse(clientSystem.stripString().getBytes());
	}
	public void handel(String code, boolean t) {
		code = interpreter.convert(code, curLang);
		Utils.debug(code);
		compile(code);
		run(null);
		//packet.sendResponse(clientSystem.stripString().getBytes());
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
		try {
			Object result = compiledCode.eval(bindings);
		} catch (ScriptException e) {
			e.printStackTrace();
		}
	}
	
//	public void debugStep(String password) {
//		if (password == "SQLSequenzDiagram") {
//			Utils.debug("lol");
//			//guiServer.notifyAll();
//			//guiServer.test();
//			//packet.sendResponse(("From code").getBytes());
//		}
//		
//	}
	
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
	
	private void setVar(String varName, Object value) {
		bindings.put(varName, value);
	}
	
	public void setVar(String varName, Object value, String s) {
		InterpreterPlan iPlan = interpreter.getInterpreterPlan(curLang);
		//Utils.debug("public static "+value.getClass().getSimpleName()+" "+varName+";");
		iPlan.addStatment("inner", "\npublic static "+value.getClass().getSimpleName()+" "+varName+";");
		iPlan.addStatment("outer", "import "+value.getClass().getName()+";\n");
		setVar(varName, value);
	}
	
	public boolean isDebug() {
		return this.debug;
	}
	
	public void setDebug(boolean debug) {
		this.debug = debug;
	}

	public ArrayList<Vector> getPlayerMoves() {
		return playerMoves;
	}

	public void setPlayerMoves(ArrayList<Vector> playerMoves) {
		this.playerMoves = playerMoves;
	}
	
	
}
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
import t1.Player;
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
	
<<<<<<< HEAD
	private ArrayList<Vector> playerMoves ;
	private Player player;
	
	public CodeRunner(ClientSystem clientSystem, boolean debug) {
		
=======
	public CodeRunner(ClientSystem clientSystem, Player player, boolean debug) {
		this.player = player;
>>>>>>> 87e6fc5f3132f9db954d2dabddd263eef95c3384
		this.debug = debug;
		this.codeSupportedLangs = new ArrayList<>();
		this.clientSystem = clientSystem;
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
	}
	
	public void handel(ServerPacket packet) {
		Utils.debug("==> "+packet.getResourceType());
		String code = new String(packet.getBytes());
		code = interpreter.convert(code, curLang);
<<<<<<< HEAD
		System.out.println("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaa"+code +"aaaaaaaaaaaaaaaaaa");
		Utils.debug(code);
=======
		if(debug) {
			Utils.debug(code);
		}
>>>>>>> 87e6fc5f3132f9db954d2dabddd263eef95c3384
		compile(code);
		run(null);
		
		JSONObject dataToSend = new JSONObject();
		dataToSend.accumulate("endOfData", false);
		dataToSend.accumulate("textData", clientSystem.stripString());
<<<<<<< HEAD
		
		
		playerMoves.add(new Vector2Di(0, 0));
//		playerMoves.add(new Vector2Di(1, 1));
//		playerMoves.add(new Vector2Di(1, 1));
//		playerMoves.add(new Vector2Di(1, 1));
//		playerMoves.add(new Vector2Di(0, -1));
		dataToSend.accumulate("playerMoves", playerMoves);//////HIER player moves hin ArrayList<Vector>
=======
		dataToSend.accumulate("playerMoves", player.getActions());
>>>>>>> 87e6fc5f3132f9db954d2dabddd263eef95c3384
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

	public ArrayList<Vector> getPlayerMoves() {
		return playerMoves;
	}

	public void setPlayerMoves(ArrayList<Vector> playerMoves) {
		this.playerMoves = playerMoves;
	}
	
	
}
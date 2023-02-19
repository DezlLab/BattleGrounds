package t4;


import javax.script.Bindings;
import javax.script.Compilable;
import javax.script.CompiledScript;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

import t5.ClientSystem;

public class InterpreterMain {
	interface MainClass {
		void main(String[] args);
	}
	
	public static void main(String[] args)
	{
		try {
		    ScriptEngineManager manager = new ScriptEngineManager();
		    ScriptEngine engine = manager.getEngineByName("java");
		    Compilable compiler = (Compilable) engine;

		    CompiledScript compiledScript = compiler.compile(""
		    		+ "import t5.ClientSystem;" +
		            "public class Script {" +
		            "   public static String[] args;" +
		            "	public static ClientSystem clientSystem;" +
		            "   public int counter = 1;" +
		            "   public void getMessage() {"
		            + "	clientSystem.print(\"Wow so cool\");"
		            + "	System.out.println(\"Hi from here \"+args[0]);"
		            +
		            "   } " +
		            "}");

	        Bindings bindings = engine.createBindings();
	        bindings.put("args", new String[]{"Luca", "Theo"});
	        ClientSystem clientSystem = new ClientSystem();
	        bindings.put("clientSystem", clientSystem);

	        Object result = compiledScript.eval(bindings);
	        clientSystem.print(" : Res");
	        System.out.println(clientSystem.stripString());
	        
	        System.out.println("Variable2 counter: " + bindings.get("counter"));
		} catch (ScriptException e) {
		    e.printStackTrace();
		}
	}
}

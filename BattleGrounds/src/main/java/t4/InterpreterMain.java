package t4;

import javax.script.Bindings;
import javax.script.Compilable;
import javax.script.CompiledScript;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

import ch.obermuhlner.scriptengine.java.JavaScriptEngine;
import ch.obermuhlner.scriptengine.java.execution.MethodExecutionStrategy;

public class InterpreterMain {
	
	interface MainClass {
		void main(String[] args);
	}

	public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException {
		//test of https://github.com/eobermuhlner/java-scriptengine
		try {
		    ScriptEngineManager manager = new ScriptEngineManager();
		    ScriptEngine engine = manager.getEngineByName("java");
		    
		    //setup main
		    JavaScriptEngine jsEngine = (JavaScriptEngine) engine;
		    jsEngine.setExecutionStrategyFactory((clazz) -> {
		        return MethodExecutionStrategy.byMatchingArguments(
		                clazz,
		                "main");
		    });
		    
		    Compilable compiler = (Compilable) engine;
		    CompiledScript compiledScript = compiler.compile(""
		    		+"public class Script {"
		    		+ "public static String[] args;"
		            + "public static void main(){" //TODO https://github.com/eobermuhlner/java-scriptengine#set-executionstrategyfactory-in-javascriptengine
		    		//+ "String[] args = _args.split(\",\");"
		            + "	int a = 39;"							//for main to work by 
		            + "	int b = 3;"
		            + "	double c = (float)(a * 2)/2.0 + b;"
		            + "	System.out.println(\"test : \"+c+args[0]);"
		            + "}"
		            +"}");
		    
//		    Object result = engine.eval(""
//		    		+"public class Script {"
//		            + "public static void main(String _args){" //TODO https://github.com/eobermuhlner/java-scriptengine#set-executionstrategyfactory-in-javascriptengine
//		    		//+ "String[] args = _args.split(\",\");"
//		            + "	int a = 39;"							//for main to work by 
//		            + "	int b = 3;"
//		            + "	double c = (float)(a * 2)/2.0 + b;"
//		            + "	System.out.println(\"test : \"+c+args[1]);"
//		            + "}"
//		            +"}");
		    
		    Bindings bindings = engine.createBindings();
		    bindings.put("args", new String[] {"make"});
		    
		    Object result = compiledScript.eval(bindings);
		    
		    System.out.println("Result: " + result);
		} catch (ScriptException e) {
		    e.printStackTrace();
		}
	}
}

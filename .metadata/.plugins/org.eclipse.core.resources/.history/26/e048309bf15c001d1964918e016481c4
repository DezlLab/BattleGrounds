package t4;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

public class InterpreterMain {
	
	interface MainClass {
		void main(String[] args);
	}

	public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException {
		//test of https://github.com/eobermuhlner/java-scriptengine
		try {
		    ScriptEngineManager manager = new ScriptEngineManager();
		    ScriptEngine engine = manager.getEngineByName("java");

		    Object result = engine.eval(""
		    		+"public class Script {"
		            + "public static void main(String[] args){" //TODO https://github.com/eobermuhlner/java-scriptengine#set-executionstrategyfactory-in-javascriptengine
		            + "	int a = 39;"							//for main to work by 
		            + "	int b = 3;"
		            + "	double c = (float)(a * 2)/2.0 + b;"
		            + "	System.out.println(\"test\"+c);"
		            + "}"
		            +"}");
		    System.out.println("Result: " + result);
		} catch (ScriptException e) {
		    e.printStackTrace();
		}
	}
}

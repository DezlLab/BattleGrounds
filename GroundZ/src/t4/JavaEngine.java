package t4;

import javax.script.Bindings;
import javax.script.Compilable;
import javax.script.CompiledScript;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

import ch.obermuhlner.scriptengine.java.JavaScriptEngine;
import ch.obermuhlner.scriptengine.java.execution.MethodExecutionStrategy;
import t5.ClientSystem;


public class JavaEngine {
	private ScriptEngine jEngine;
	private Compilable compiler;
	
	private String sourceCode;
	private CompiledScript compiledScript;
	private Bindings bindings;
	
	private ClientSystem clientSystem;
	
	private boolean debug;
	
	public JavaEngine() {
		this(true);
	}
	
	public JavaEngine(boolean debug) {
		this.debug = debug;
		this.jEngine = new ScriptEngineManager().getEngineByName("java");
		this.compiler = (Compilable) jEngine;
		this.sourceCode = "public class Main{\n"
				+ "    public static void main(String[] args) {\n"
				+ "    System.out.print(\"Hello\"+\"args[0]\");\n"
				+ " }\n"
				+ "}\n";
		this.bindings = jEngine.createBindings();
		
		this.setEntryMethod("main");
	}
	
	public ClientSystem getClientSystem() {
		return this.clientSystem;
	}
	
	public void setEntryMethod(String name) {
		((JavaScriptEngine) jEngine).setExecutionStrategyFactory((clazz) -> {
	        return MethodExecutionStrategy.byMatchingArguments(
	                clazz,
	                name);
	    });
	}
	
	public void run() { run(new String[] {""});}
	
	public void run(String[] args) {
		setArgs(args);
		try {
			System.out.println(sourceCode);
			this.compiledScript = compiler.compile(sourceCode);
			
			Object result = compiledScript.eval(bindings);
			//System.out.println("Result: " + result);
		} catch (ScriptException e) {
			System.out.println("run :: ERROR");
			e.printStackTrace();
		}
	}
	
	private boolean setArgs(String[] args) {
		//To get String[] args working a line is added as global var and then set with put
		this.sourceCode.replaceAll("  ", " ");//Only allow one white space
		int index = this.sourceCode.indexOf("public static void main(String[] args)");
		if (index != -1) {
			//Also the entryMethod is not allowed to have args (they are removed)
			System.out.println("==>"+sourceCode);
			this.sourceCode = this.sourceCode.replace("public static void main(String[] args)", "public static String[] args; public static void main()");
			//this.sourceCode = this.sourceCode.substring(0, index) + "public static String[] args; " + this.sourceCode.substring(index);
			System.err.println(sourceCode);
			//bindings.put("args", args);
			return true;
		}
		return false;
	}
	
	public void addBinding(String paramName, Object param) {
		bindings.put(paramName, param);
	}
	
	public String getSourceCode() {
		return sourceCode;
	}

	public void setSourceCode(String sourceCode) {
		this.sourceCode = sourceCode;
	}

	public boolean isDebug() {
		return debug;
	}

	public void setDebug(boolean debug) {
		this.debug = debug;
	}
}
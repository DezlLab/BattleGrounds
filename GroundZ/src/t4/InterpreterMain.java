package t4;

public class InterpreterMain {
	
	public static void main(String[] args)
	{
		
		CodeRunner jEngine = new CodeRunner(true);
		
		Interpreter i = new Interpreter();
		String code = i.convert(""+
	            "public class Script {" +
	            "public static void main(String[] args) {"
	            + "  System.out.print(\"Wow so cool\");"
	            + "System.out.println(\"Wow with conversion \");"
	            +
	            "   } " +
	            "}", "java");
		System.out.println(code);
		jEngine.compile(code);
		jEngine.run(null);
	}
}

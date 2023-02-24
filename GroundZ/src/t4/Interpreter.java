package t4;

import java.util.ArrayList;

public class Interpreter {
	
	private ArrayList<InterpreterPlan> interpreterPlans;
	
	public Interpreter() {
		interpreterPlans = new ArrayList<InterpreterPlan>();
		InterpreterPlan toJava = addInterpreterPlan("java");
		toJava.addConversion("System.out.", "clientSystem.");
		toJava.addConversion("System.err", "clientSystem.");
		toJava.addConversion("public static void main(String[] args)", "public static void main()");
		toJava.addStatment("inner", "\npublic static String[] args;");
		toJava.addStatment("inner", "\npublic static ClientSystem clientSystem;");
		toJava.addStatment("outer", "import t5.ClientSystem;\n");
		
		InterpreterPlan playGroundStyle = addInterpreterPlan("playGroundStyle");
		playGroundStyle.addStatment("outer", "public class Main{\n"
				+ "    public static void main() {");
		playGroundStyle.addConversion("print", "clientSystem.println");
		playGroundStyle.addStatment("inner", "\npublic static String[] args;");
		playGroundStyle.addStatment("inner", "\npublic static ClientSystem clientSystem;");
		playGroundStyle.addStatment("outer", "import t5.ClientSystem;\n");
		playGroundStyle.addStatment("end", "}}");
		//toJava.addConversionAt("main", ";", ";\njEngine.debugStep(\"SQLSequenzDiagram\");");
	}
	
	public Interpreter(String templatePath) {
		
	}
	
	public InterpreterPlan addInterpreterPlan(String name) {
		interpreterPlans.add(new InterpreterPlan(name));
		return interpreterPlans.get(interpreterPlans.size()-1);
	}
	
	public InterpreterPlan getInterpreterPlan(String name) {
		for(InterpreterPlan p : interpreterPlans) {
			if(p.getName().equals(name)) {
				return p;
			}
		}
		return null;
	}
	
	public String convert(String code, String interpreterName) {
		for(InterpreterPlan p : interpreterPlans) {
			if(p.getName().equals(interpreterName)) {
				return p.convertCode(code);
			}
		}
		return "import t5.ClientSystem;public class Script{public static ClientSystem clientSystem;public static String[] args;public static void main() {  clientSystem.print(\"Conversion Error in Interpreter\");}}";
	}
}

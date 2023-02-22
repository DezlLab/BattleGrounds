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
		toJava.addStatment("inner", "public static String[] args;");
		toJava.addStatment("inner", "public static ClientSystem clientSystem;");
		toJava.addStatment("outer", "import t5.ClientSystem;");
	}
	
	public Interpreter(String templatePath) {
		
	}
	
	public InterpreterPlan addInterpreterPlan(String name) {
		interpreterPlans.add(new InterpreterPlan(name));
		return interpreterPlans.get(interpreterPlans.size()-1);
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

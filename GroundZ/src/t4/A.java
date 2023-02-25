package t4;

import javax.swing.JFrame;

import t5.ClientSystem;

public class A { 
	   public static void main(String[] args) {
		   JFrame j;
		   ClientSystem c;
		   //Reflections r;
		   String className = "String";
		   ClassLoader classLoader = ClassLoader.getSystemClassLoader();
		   boolean classFound = false;
		   
		   
		   for (Package p : Package.getPackages()) {
			   System.out.println(p);
//		       try {
//		           Class<?> clazz = classLoader.loadClass(p.getName() + "." + className);
//		           System.out.println("Class path: " + clazz.getName());
//		           classFound = true;
//		           break; // Stop searching after the first match
//		       } catch (ClassNotFoundException e) {
//		           // Ignore and continue searching
//		       }
		   }
		if (!classFound) {
		       System.out.println("No such class: " + className);
		   }
	   } 
}
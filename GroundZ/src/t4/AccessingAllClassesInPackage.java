package t4;


import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Set;
import java.util.function.Consumer;
import java.util.stream.Collectors;

import t5.ClientSystem;

public class AccessingAllClassesInPackage {

    public Set<Class> findAllClassesUsingClassLoader(String packageName) {
        InputStream stream = ClassLoader.getSystemClassLoader()
          .getResourceAsStream(packageName.replaceAll("[.]", "/"));
        BufferedReader reader = new BufferedReader(new InputStreamReader(stream));
        return reader.lines()
          .filter(line -> line.endsWith(".class"))
          .map(line -> getClass(line, packageName))
          .collect(Collectors.toSet());
    }
 
    private Class getClass(String className, String packageName) {
        try {
            return Class.forName(packageName + "."
              + className.substring(0, className.lastIndexOf('.')));
        } catch (ClassNotFoundException e) {
            // handle the exception
        }
        return null;
    }
    
    public static void main(String[] args) {
        AccessingAllClassesInPackage instance = new AccessingAllClassesInPackage();
        
        Set<Class> classes = instance.findAllClassesUsingClassLoader(
          "");
     
        //classes.toArray()
        System.out.println(classes.size()+"\n=="+classes.toString());
        

        System.out.println(ClassLoader.getSystemClassLoader().getResource("t5"));
	}
}
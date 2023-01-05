package t1;

import java.lang.reflect.InvocationTargetException;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class ConsoleMain {

	public static void main(String[] args) 
	{
		// TODO Auto-generated method stu
		Grid grid = new Grid(20,20);
		
		Player player = new Player(new Vector2Df(1.0f,1.0f),grid);
		Player player1 = new Player(new Vector2Df(1.0f,1.0f),grid);
		
		player.setPin(43);
		System.out.println(player1.getPin());
		
		System.out.println(player);
		Scanner eingabe = new Scanner(System.in); 
		
		while(1 == 1)
		{
			java.lang.reflect.Method method = null;
			
			System.out.print("Put in moveMethod:\n");
			String[] name = eingabe.nextLine().split(",");
//			if (name.contains("moveLeft()")) 
//			{
//				System.out.println("aaaaaaaaaaaa");
//				player.moveLeft();
//				}
			
		for( String i : name)
		{ 
			i = i.replaceAll("\\(\\)", "");
			//System.out.println(i);
			try {
				method = player.getClass().getMethod(i);
				try {
					method.invoke(player);
				} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
					
				}
			} catch (NoSuchMethodException | SecurityException e) {
			
			}
			 System.out.println(player);
			 try {
				TimeUnit.MILLISECONDS.sleep(500);;
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}	
			
			 
			
			
		}
	}

}

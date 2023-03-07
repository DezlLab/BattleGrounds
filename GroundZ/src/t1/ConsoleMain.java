package t1;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.nio.file.spi.FileSystemProvider;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class ConsoleMain {
	
	
	
	public static void main(String[] args) 
	{
		Menu menu = new Menu();
		// TODO Auto-generated method stu
		Grid grid = new Grid(5,5);
		
		Player player = new Player(new Vector2Df(1.0f,1.0f),grid);
		//Player player1 = new Player(new Vector2Df(1.0f,1.0f),grid);
		
//		player.setPin(43);
		//System.out.println(player1.getPin());
		
		
		System.out.println(menu);
		
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
			
			String[] methodParts = i.split("\\(");
			
			//methodParts[1] = i.replaceAll("\\(\\)", "");
			
//			System.out.println(i);
//			System.out.println(methodParts[0]);
//			System.out.println(methodParts[1]);
			if (methodParts.length != 2 )
			{
			try {
				try {
					player.getClass().getMethod(methodParts[0]).invoke(player);
				} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				};
				
				}
				catch (NoSuchMethodException | SecurityException e) {
				}
			}
			else 
			{
				methodParts[1] = methodParts[1].replaceAll("\\)", "");
				methodParts[1] = methodParts[1].replaceAll("\\(\\)", "");
				if (methodParts[1] == "")
				{
					try {
						try {
							player.getClass().getMethod(methodParts[0]).invoke(player);
						} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						};
						
						}
						catch (NoSuchMethodException | SecurityException e) {
						}
				}
				else
				{
					try {
						try {
							player.getClass().getMethod(methodParts[0],int.class).invoke(player,Integer.parseInt(methodParts[1]));
						} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						};
						
						}
					 catch (NoSuchMethodException | SecurityException e) {
					 
					 }
				}
			}
			if(!i.contains("help"))
			{
			clearScreen();
			System.out.println(player);
			}
			 try {
				TimeUnit.MILLISECONDS.sleep(500);;
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}	
			
			 
			
			
		}
	}
	public static void clearScreen() 
	{  	
		System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
	}

}

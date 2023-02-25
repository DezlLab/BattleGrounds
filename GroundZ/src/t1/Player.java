package t1;

import java.nio.file.spi.FileSystemProvider;
import java.util.ArrayList;

public class Player 
{
	private static int Pin = 23;
	private String rotation;
	private String look;
	private Grid grid;
	private Vector2Df position;
	private ArrayList<GameObject> map;
	private Vector2Df destination;
	private int coins;
	
	
	
	public static int getPin() {
		return Pin;
	}

	public static void setPin(int pin) {
		Pin = pin;
	}

	public Player()
	{
		this.position = new Vector2Df(0f,0f);
	}
	
	public Player(  Vector2Df position,Grid grid ) {
		
		super();
		this.grid = grid;
		this.map = grid.getObjects();
		this.position = position;
		this.destination = new Vector2Df(position.getX(),position.getY());	
		look = "▲";
		rotation = "Up";
		coins = 0;
		Pin ++;
		
	}

	//_____________________________________________________________________________________________________________________________________
	
	public void move(String direction)
	{
		switch (direction) {
		case "left":
			goveLeft();break;
		case "right":
			goveRight();break;
		case "down":
			goveDown();break;
		case "up":
			goveUp();break;

		default:break;
		}
	}
	
	public void down() {this.position = this.position.add(Vector.down());System.out.println(this.position);}
	public void up() {this.position = this.position.add(Vector.up());}
	public void left() {this.position = this.position.add(Vector.left());}
	public void right() {this.position = this.position.add(Vector.right());}
	
	public void move()
	{
		switch (rotation) {
		case "Left":
			goveLeft();break;
		case "Right":
			goveRight();break;
		case "Down":
			goveDown();break;
		case "Up":goveUp();break;
		default:break;
		}
	}
	public void moveBack()
	{
		switch (rotation) {
		case "Left":
			goveRight();break;			
		case "Right":
			goveLeft();break;
		case "Down":
			goveUp();break;
		case "Up":
			goveDown();break;
		default:break;
		}
	}
	public void moveLeft()
	{
		switch (rotation) {
		case "Left":
			goveDown();break;
		case "Right":
			goveUp();break;
		case "Down":
			goveRight();break;
		case "Up":
			goveLeft();break;
		default:break;
		}
	}
	public void moveRight()
	{
		switch (rotation) {
		case "Left":
			goveUp();break;
		case "Right":
			goveDown();break;
		case "Down":
			goveLeft();break;
		case "Up":
			goveRight();break;
		default:break;
		}
	}
	
	public void goveUp()
	{
		if (map.get(destinationToList()).canLeave("South"))
		{
		destination.setY(position.getY()-1);
		if (map.get(destinationToList()).canEnter("South"))
			position.setY(destination.getY());
		else
			destination.setY(position.getY());
		collectCoin();
		}
	}
	
	public void coin()
	{
		coins += 1000;
	}
	public void goveDown()
	{
		if (map.get(destinationToList()).canLeave("North"))
		{
		destination.setY(position.getY()+1);
		if (map.get(destinationToList()).canEnter("North"))
			position.setY(destination.getY());
		else
			destination.setY(position.getY());
		collectCoin();
		}
	}
	public void goveLeft()
	{
		if (map.get(destinationToList()).canLeave("East"))
		{
		destination.setX(position.getX()-1);
		if (map.get(destinationToList()).canEnter("East"))
			position.setX(destination.getX());	
		else
			destination.setX(position.getX());
		collectCoin();
		}
	}
	public void goveRight()
	{
		if (map.get(destinationToList()).canLeave("West"))
		{
		destination.setX(position.getX()+1);
		if (map.get(destinationToList()).canEnter("West"))
			position.setX(destination.getX());	
		else
			destination.setX(position.getX());
		collectCoin();
		}
		
			
	}
	public void collectCoin()
	{
		if (map.get(positionToList()).canCollect()  && map.get(positionToList()).toString() == "₿")
		{
			map.set(positionToList(), new NormalStone());
			coins ++;
		}
	}
	//▲►▼◄
	public void rotateRight()
	{
		
		switch (rotation) {
		case "Up":rotation="Right";
		look = "►";
		break;
		case "Right": rotation="Down";
		look = "▼";break;
		case "Down": rotation="Left";
		look = "◄";break;
		case "Left": rotation="Up";
		look = "▲";break;
		default:
			break;
		}
	}
	public void rotateLeft()
	{
		switch (rotation) {
		case "Down":rotation="Right";
		look = "►";
		break;
		case "Left": rotation="Down";
		look = "▼";break;
		case "Up": 
			rotation="Left";
		look = "◄";break;
		case "Right": rotation="Up";
		look = "▲";break;
		default:
			break;
		}
	}
	
	//____________________________________________________________________________________________________________________________
	
	public int destinationToList()
	{
		int index = 0;
		index = ((int) destination.getY() ) *  grid.getWidth()  + (int)destination.getX()  ;
		
		return index;
	}
	
	public int positionToList()
	{
		int index = 0;
		index = ((int) position.getY() ) *  grid.getWidth()  + (int)position.getX()  ;
		
		return index;
	}
	
	public String toString() {
		String gridTranslation = "\n\n\n    ";
		int slpitCount = 0;
		int rowCount = 0;
		for (GameObject i : map) 
		{
			if (slpitCount == position.getX() &&  rowCount == position.getY())
			{
				gridTranslation += look +" "; //▲►▼◄
			}
			else
			{
				if (slpitCount < grid.getWidth())
					gridTranslation += i +" ";
				else
				{
					rowCount ++;
					slpitCount = 0;
					if (slpitCount == position.getX() &&  rowCount == position.getY())
					{
						gridTranslation += "\n    O ";
					}
					else
						gridTranslation +="\n    "+ i +" ";
				}
			}
			slpitCount++;
		}
		gridTranslation += "\n     Coins:" + coins + "        OverObject:  " + map.get(positionToList()) ;
		return gridTranslation;
	}
}



package t1;

import java.util.ArrayList;


import gameLogic.GameAction;


public class Player 
{
	
	private String rotation;
	private String look;
	private Grid grid;
	private Vector2Df position;
	private ArrayList<GameObject> map;
	private Vector2Df destination;
	private int coins;
	private Vector2Df lastDestination;

	
	 

	private ArrayList<GameAction> actions;
	
	


	public Player()
	{
		this.position = new Vector2Df(1f,1f);
	}
	
	public Player(  Vector2Df position,Grid grid ) {
	
		this.actions = new ArrayList<GameAction>();
		this.grid = grid;
		this.map = grid.getObjects();
		this.position = position;
		this.destination = new Vector2Df(position.getX(),position.getY());	
		this.lastDestination = new Vector2Df(position.getX(),position.getY());	
		look = "▲";
		rotation = "Up";
		coins = 0;
		actions.add(GameAction.move(Vector.zero()));
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
	

	
	public ArrayList<GameAction> getActions() {
		ArrayList<GameAction> res = actions;
		actions = new ArrayList<GameAction>();
		return res;
	}
	public String help()
	{
		System.out.println("Mögliche Commands: \n\nmoveForward()\nmoveBack()\nmoveRight()\nmoveLeft()\nrotateRight()\nrotateLeft()\ncollectCoin()\nAlle move Commands können mit einer Zahl benutzt werden,\n um sie mehrmals auszuführen. Bsp. moveForward(3);\n");
		return "Mögliche Commands: \n\nplayer.moveForward()\nplayer.moveBack()\nplayer.moveRight()\nplayer.moveLeft()\nplayer.rotateRight()\nplayer.rotateLeft()\nplayer.collectCoin()\nAlle move Commands können mit einer Zahl benutzt werden,\n um sie mehrmals auszuführen. Bsp. player.moveForward(3);\n";
	}
	//Console 
	public void moveForward()
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
	public void moveForward(int times)
	{
		for(int i = 0; i < times ; i++)
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
	}
	public void moveBack(int times)
	{
		for(int i = 0; i < times ; i++)
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
	}
	public void moveLeft(int times)
	{
		for(int i = 0; i < times ; i++)
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
	}
	
	public void moveRight(int times)
	{
		for(int i = 0; i < times ; i++)
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
	}
	private void goveUp()
	{
		boolean addZero = true;
		if (map.get(destinationToList()).canLeave("South")) {
			destination.setY(position.getY()-1);
			if (map.get(destinationToList()).canEnter("South")) {
				position.setY(destination.getY());
				actions.add(GameAction.move(Vector.up()));
				addZero = false;
			}
			destination.setY(position.getY());
		}
		
		if(addZero){
			actions.add(GameAction.move(Vector.zero()));
		}		
	}
	

	private void goveDown()
	{
		boolean addZero = true;
		if (map.get(destinationToList()).canLeave("North")) {
			destination.setY(position.getY()+1);
			if (map.get(destinationToList()).canEnter("North")) {
				position.setY(destination.getY());
				actions.add(GameAction.move(Vector.down()));
				addZero = false;
			}
			destination.setY(position.getY());
		}
		
		if(addZero){
			actions.add(GameAction.move(Vector.zero()));
		}
	}
	
	private void goveLeft()
	{
		boolean addZero = true;
		if (map.get(destinationToList()).canLeave("East")) {
			destination.setX(position.getX()-1);
			if (map.get(destinationToList()).canEnter("East")) {
				position.setX(destination.getX());
				actions.add(GameAction.move(Vector.left()));
				addZero = false;
			}
			destination.setX(position.getX());
		}
		
		if(addZero){
			actions.add(GameAction.move(Vector.zero()));
		}
	}
	
	private void goveRight()
	{
		boolean addZero = true;
		if (map.get(destinationToList()).canLeave("West")) {
			destination.setX(position.getX()+1);
			if (map.get(destinationToList()).canEnter("West")) {
				position.setX(destination.getX());
				actions.add(GameAction.move(Vector.right()));
				addZero = false;
			}
			destination.setX(position.getX());
		}
		
		if(addZero){
			actions.add(GameAction.move(Vector.zero()));
		}
//		if (map.get(destinationToList()).canLeave("West"))
//		{
//		destination.setX(position.getX()+1);
//		lastDestination.setX(destination.getX());
//		lastDestination.setY(destination.getY());
//		if (map.get(destinationToList()).canEnter("West")) {
//			position.setX(destination.getX());
//			System.out.println("==move right==");
//		}
//		else {
//			destination.setX(position.getX());
//			System.out.println("==cant move right==");
//		}
//		}	
//		if( lastDestination.compare(position) ) {
//			System.out.println("=addRight=");
//			actions.add(GameAction.move(Vector.right()));
//		}
//		else {
//			actions.add(GameAction.move(Vector.zero()));
//			System.out.println("=addZero=");
//		}
	}
	public void collectCoin()
	{
		if (map.get(positionToList()).canCollect()  && map.get(positionToList()).toString() == "₿")
		{
			map.set(positionToList(), new NormalStone());
			coins ++;
		}
		actions.add(GameAction.collectCoin());
	}
	
//	private void delay()
//	{
//		 try {
//				TimeUnit.MILLISECONDS.sleep(500);;
//			} catch (InterruptedException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//	}
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
		actions.add(GameAction.rotate(90));
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
		actions.add(GameAction.rotate(-90));
	}
	public void rotateRight(int times)
	{
		for(int i = 0; i < times ; i++)
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
		actions.add(GameAction.rotate(90));
		}
	}
	public void rotateLeft(int times)
	{
		for(int i = 0; i < times ; i++)
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
		actions.add(GameAction.rotate(-90));
		}
	}
	
	//____________________________________________________________________________________________________________________________
	
	private int destinationToList()
	{
		int index = 0;
		index = ((int) destination.getY() ) *  grid.getWidth()  + (int)destination.getX()  ;
		return index;
	}
	
	private int positionToList()
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

//	public Vector2Df getPosition() {
//		return position;
//	}
//
//	public void setPosition(Vector2Df position) {
//		this.position = position;
//	}
	public void resetPlayer()
	{
		position = new Vector2Df(1f,1f);
		destination.setX(position.getX());
		destination.setY(position.getY());
		map = grid.getObjects();
		rotation = "Up";
		look = "▲";
	}
}

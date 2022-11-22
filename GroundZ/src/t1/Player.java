package t1;

import java.util.ArrayList;

public class Player 
{
	
	private Grid grid;
	private Vector2Df position;
	private ArrayList<String> map;
	private Vector2Df destination;
	
	
	public Player()
	{
		this.position = new Vector2Df(0f,0f);
	}
	
	public Player(  Vector2Df position,Grid grid) {
		super();
		this.grid = grid;
		this.map = grid.getObjects();
		this.position = position;
		this.destination = new Vector2Df(position.getX(),position.getY());	
	}

	
	
	public void move(String direction)
	{
		switch (direction) {
		case "left":
			position.setX(position.getX()-1);
			
		break;
		case "right":
			position.setX(position.getX()+1);
			
		break;
		case "down":
			position.setY(position.getY()+1);
			
		break;
		case "up":
			position.setY(position.getY()-1);
			
		break;

		default:
			break;
		}
		
		System.out.println(positionToList());
		
		System.out.println(position);

		
	}
	
	public void left() { position.add(Vector.left());}
	public void right(){ position.add(Vector.right());}
	public void up()   { position.add(Vector.up());}
	public void down() { position.add(Vector.down());}
	
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
		for (String i : map) 
		{
			if (slpitCount == position.getX() &&  rowCount == position.getY())
			{
				gridTranslation += "O ";
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
		return gridTranslation;
	}
}

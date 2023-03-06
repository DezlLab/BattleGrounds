package t1;

import java.util.ArrayList;
import java.util.Iterator;

public class Grid 
{
	private int height;
	private int width;
	private ArrayList<GameObject> objects;
	private ArrayList<GameObject> objectsNoBarriers;
	
	
	
	//						width  height  
	private int[] Level1 = {1     ,1     ,};
	
	



	public Grid() 
	{
		height = 0;
		width = 0;
		
	}



	public int getHeight() {
		return height;
	}



	public void setHeight(int height) {
		this.height = height;
	}



	public int getWidth() {
		return width;
	}



	public void setWidth(int width) {
		this.width = width;
	}



	public Grid(int width, int height) 
	{
		
		this.height = height;
		this.width = width;
		objects = new ArrayList<GameObject>();
		objectsNoBarriers = new ArrayList<GameObject>();
		newRandomGrid();
	}



	public ArrayList<GameObject> getObjects() 
	{
		
		return (ArrayList<GameObject>) objects.clone();
	}



	public void setObjects(ArrayList<GameObject> objects) 
	{
		this.objects = objects;
	}
	
	
	public void newRandomGrid()
	{
		//int random = 3;//(int) (Math.random()*height*width);
		for (int i= 0; i<height*width;i++) 
		{
			if(i!=0)
			{
				if ((int) (Math.random()*100 )== 3)
					objects.add( new Tunel(true) );
				else if ((int) (Math.random()*100 )== 3)
					objects.add( new Tunel(false) );
				else if ((int) (Math.random()*5 )== 3)
					objects.add( new Coin() );
				 
				else
					objects.add( new NormalStone() );
			}
			else
				objects.add( new NormalStone() );
		}
		objectsNoBarriers = (ArrayList<GameObject>) objects.clone();
		addBarrier();
	}
	
//	public ArrayList<Vector2Di> coinPosition()
//	{
//		ArrayList<Vector2Di> coinPos = new ArrayList<Vector2Di>();;
//		int index = 0;
//		for (GameObject i :objects)
//		{
//			index ++;
//			if(i.getClass().getName()== "Coin" )
//			{
//				coinPos.add(positionToList(index));
//			}
//			
//		}
//		return coinPos;
//		
//	}
//	
//	public ArrayList<Vector2Di> tunelSouthPosition()
//	{
//		ArrayList<Vector2Di> coinPos = new ArrayList<Vector2Di>();;
//		int index = 0;
//		for (GameObject i :objects)
//		{
//			index ++;
//			if(i.getClass().getName()== "Coin" )
//			{
//				coinPos.add(positionToList(index));
//			}
//			
//		}
//		return coinPos;
//		
//	}
//	public ArrayList<Vector2Di> tunelWestPosition()
//	{
//		ArrayList<Vector2Di> coinPos = new ArrayList<Vector2Di>();;
//		int index = 0;
//		for (GameObject i :objects)
//		{
//			index ++;
//			if(i.getClass().getName()== "Coin" )
//			{
//				coinPos.add(positionToList(index));
//			}
//			
//		}
//		return coinPos;
//		
//	}
//	
//	
//	public Vector2Di positionToList(int index)
//	{
//		Vector2Di position = new  Vector2Di() ;
//		
//		if (index / width % 1 == 0)
//			position.setY( (int)(index / width )  ); // pos = ((int) position.getY() ) *  grid.getWidth()  + (int)position.getX()  ;
//		else
//			position.setY( (int)(index / width +1)  );
//		position.setX( index - (position.getY()-1 )* width );
//		return position;
//	}
	
	public void addBarrier()
	{
		height +=2;
		width += 2;
		for (int i= 0; i<width;i++) 
		{
			
			objects.add(i, new BarrierBlock() );
		}
		
		for (int i= width; i<height*width -width;i++) 
		{
			
			objects.add(i, new BarrierBlock() );
			i+= width-1;
			objects.add(i, new BarrierBlock() );
		}
		
		for (int i= height*width -width; i<height*width;i++) 
		{
			
			objects.add(i, new BarrierBlock() );
		}
	}
	
	public String randomObject()
	{
		
		return ".";
	}

	
	public ArrayList<String> objectsToPng()
	{
		ArrayList<String> pngPath = new ArrayList<String>();
		
		for (GameObject i :objectsNoBarriers)
			{
				System.out.println(i.getClass().getName());
				if(i.getClass().getName().contains("Coin") )
					pngPath.add("Coin.png");
				else if (i.getClass().getName().contains("Tunel")  &&  ((Tunel) i).isFacingSouth() == true)
					pngPath.add("TunelSouth.png");
				else if (i.getClass().getName().contains("Tunel")  &&  ((Tunel) i).isFacingSouth() == false)
					pngPath.add("TunelWest.png");
				else
					pngPath.add(null);
			}
		
		return pngPath;
	}


	@Override
	public String toString() {
		String gridTranslation = "\n\n\n    ";
		int rowCount = 0;
		for (GameObject i : objects) 
		{
			
			if (rowCount < width)
				gridTranslation += i +" ";
			else
			{
				rowCount = 0;
				gridTranslation +="\n    "+ i +" ";
			}
			rowCount++;
		}
		return gridTranslation;
	}
	
	
}

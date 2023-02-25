package t1;

import java.util.ArrayList;
import java.util.Iterator;

public class Grid 
{
	private int height;
	private int width;
	private ArrayList<GameObject> objects;
	
	
	
	
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
		newRandomGrid();
	}



	public ArrayList<GameObject> getObjects() 
	{
		return objects;
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
			if ((int) (Math.random()*100 )== 3)
				objects.add( new Tunel(true) );
			else if ((int) (Math.random()*100 )== 3)
				objects.add( new Tunel(false) );
			else if ((int) (Math.random()*5 )== 3)
				objects.add( new Coin() );
			 
			else
				objects.add( new NormalStone() );
		}
		addBarrier();
	}
	
	
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

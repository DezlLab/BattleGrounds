package t1;

import java.util.ArrayList;
import java.util.Iterator;

public class Grid 
{
	private int height;
	private int width;
	private ArrayList<String> objects;
	
	
	
	



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
		objects = new ArrayList<String>();
		newRandomGrid();
	}



	public ArrayList<String> getObjects() 
	{
		return objects;
	}



	public void setObjects(ArrayList<String> objects) 
	{
		this.objects = objects;
	}
	
	
	public void newRandomGrid()
	{
		for (int i= 0; i<height*width;i++) 
		{
			objects.add( ".");
			
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
		for (String i : objects) 
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

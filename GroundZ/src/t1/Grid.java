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
		objects = new ArrayList<GameObject>();;
		for (int i= 0; i<height*width;i++) 
		{
			if(i!=0)
			{
				if ((int) (Math.random()*10 )== 3)
					objects.add( new Tunnel(true) );
				else if ((int) (Math.random()*10 )== 3)
					objects.add( new Tunnel(false) );
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
	
	public void addBarrier()
	{
		int heightWithBarrier = height +2;
		int widthWithBarrier = height +2;

		for (int i= 0; i<width;i++) 
		{
			
			objects.add(i, new BarrierBlock() );
		}
		
		for (int i= widthWithBarrier; i<heightWithBarrier*widthWithBarrier -widthWithBarrier;i++) 
		{
			
			objects.add(i, new BarrierBlock() );
			i+= widthWithBarrier-1;
			objects.add(i, new BarrierBlock() );
		}
		
		for (int i= heightWithBarrier*widthWithBarrier -widthWithBarrier; i<heightWithBarrier*widthWithBarrier;i++) 
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
				
				if(i.getClass().getName().contains("Coin") )
					pngPath.add("Coin.png");
				else if (i.getClass().getName().contains("Tunnel")  &&  ((Tunnel) i).isFacingSouth() == true)
					pngPath.add("TunnelSouth.png");
				else if (i.getClass().getName().contains("Tunnel")  &&  ((Tunnel) i).isFacingSouth() == false)
					pngPath.add("TunnelWest.png");
				else
					pngPath.add(null);
			}
		
		return pngPath;
	}
	
	public ArrayList<Integer> size()
	{
		 ArrayList<Integer> size = new ArrayList<Integer>();
		 size.add(width);
		 size.add(height);
		 return size;
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

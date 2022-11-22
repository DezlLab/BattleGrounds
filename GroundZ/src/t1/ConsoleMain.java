package t1;

public class ConsoleMain {

	public static void main(String[] args) 
	{
		// TODO Auto-generated method stu
		Grid grid = new Grid(20,20);
		System.out.println(grid);
		Player player = new Player(new Vector2Df(1.0f,1.0f),grid);
		System.out.println(player);
		player.move("left");
		player.down();
		System.out.println(player);

		
	}

}

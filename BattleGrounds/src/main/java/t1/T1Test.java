package t1;

public class T1Test {
	
	public static void main(String[] args) {
		Vector2D v = new Vector2D();
		Vector2D a = new Vector2D(0f, 0f);
		System.out.println("== : "+v+a);
		
		a.setXLimit((f) -> {return (f >= 1) ? f : 1;});
		v.setX(1);
		System.out.println("== : "+v+a);
	}
}

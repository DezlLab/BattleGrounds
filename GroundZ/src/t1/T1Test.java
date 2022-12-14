package t1;

public class T1Test {
	
	public static void main(String[] args) {
		Vector2Df v = new Vector2Df();
		Vector2Df a = new Vector2Df(0f, 0f);
		System.out.println("v+a == : "+v+a);
		
		a.setXLimit((f) -> {return (f >= 1) ? f : 1;});
		v.setX(1);
		System.out.println("v+a == : "+v+a);
		
		Vector b = v.add(v);
		
		System.out.println("b == : "+b);
		
		Vector2Di i = new Vector2Di(Vector.up());
		//VectorConst cant be edited
		Vector constVec = Vector.up();
		//Vector int float conversion
		i = i.add(b);
		i = i.mult(b);
		
		System.out.println("i == : "+i);
		
		i.setX(0);
		//Can be added and all things but there is no access to the const
		//this is IMPORTANT because it allows all to access the contents but no option to change
		constVec.abs();
		System.out.println(constVec);
	}
}

package t1;

public abstract class Vector {
	
	private static VectorConst zero	= new VectorConst(new Vector2Di(0, 0));
	private static VectorConst up	= new VectorConst(new Vector2Di(0, 1));
	private static VectorConst down	= new VectorConst(new Vector2Di(0, -1));
	private static VectorConst left	= new VectorConst(new Vector2Di(-1, 0));
	private static VectorConst right= new VectorConst(new Vector2Di(1, 0));
	
	public static Vector zero() {
		return zero;
	}
	
	public static Vector up() {
		return up;
	}
	
	public static Vector down() {
		return down;
	}
	
	public static Vector left() {
		return left;
	}
	
	public static Vector right () {
		return right;
	}
	
	abstract Vector2Df toFloat();
	abstract Vector2Di toInt();
	
	abstract Vector add(Vector other);
	abstract Vector sub(Vector other);
	abstract Vector mult(Vector other);
	abstract Vector divd(Vector other);
	
	abstract void scal(float s);
	abstract float abs();
	
	public abstract String toString();
}

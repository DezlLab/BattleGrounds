package t1;

public class Vector2D{
	
	private float x;
	private float y;
	
	interface Limiter {
		float set (float f);
	}
	
	Limiter xLimit = (f) -> {return f;};
	Limiter yLimit = (f) -> {return f;};
	
	public Vector2D() {
		this.setX(0.0f);
		this.setY(0.0f);
	}
	
	public Vector2D(float x, float y) {
		this.setX(x);
		this.setY(y);
	}
	
	Vector2D add(Vector2D other) { return new Vector2D(this.x + other.x, this.y + other.y);}
	
	Vector2D sub(Vector2D other) { return new Vector2D(this.x - other.x, this.y - other.y);}
	
	Vector2D mult(Vector2D other) {return new Vector2D(this.x * other.x, this.y * other.y);}
	
	Vector2D divd(Vector2D other) {return new Vector2D(this.x / other.x, this.y / other.y);}
	
	Vector2D scal(float s) { return new Vector2D(this.x * s, this.y * s);}
	
	float abs() { return (float) Math.sqrt(Math.pow(x, 2f) + Math.pow(y, 2f));}
	
	public static Vector2D zero()	{ return new Vector2D(0f, 0f);}
	public static Vector2D up()		{ return new Vector2D(0f, 1f);}
	public static Vector2D down()	{ return new Vector2D(0f,-1f);}
	public static Vector2D right()	{ return new Vector2D(1f, 0f);}
	public static Vector2D left()	{ return new Vector2D(-1f,0f);}
	
	public Vector2D(Vector2D vec) {
		this(vec.x, vec.y);
	}
	
	public void setX(float x) {
		this.x = xLimit.set(x);
	}

	public void setY(float y) {
		this.y = xLimit.set(y);
	}
	
	public float getX() { return x;}

	public float getY() { return y;}

	public Limiter getxLimit() { return xLimit;}
	
	public Limiter getyLimit() { return yLimit;}

	public void setXLimit(Limiter xLimit) {
		this.xLimit = xLimit;
		this.setX(x);
	}

	public void setYLimit(Limiter yLimit) {
		this.yLimit = yLimit;
		this.setY(y);
	}
	
	public String toString() {
		return "("+this.x+", "+this.y+")";
	}
}
package t1;

public class Vector2Di extends Vector{
	private int x;
	private int y;
	
	public interface Limiter {
		int set (int f);
	}
	
	private Limiter xLimit = (f) -> {return f;};
	private Limiter yLimit = (f) -> {return f;};
	
	public Vector2Di() {
		this.setX(0);
		this.setY(0);
	}
	
	public Vector2Di(int x, int y) {
		this.setX(x);
		this.setY(y);
	}
	
	public Vector2Di(Vector newVec) {
		Vector2Di vec = newVec.toInt();
		this.setX(vec.x);
		this.setX(vec.y);
	}

	public Vector2Df toFloat() {
		return new Vector2Df((float)(this.x), (float)(this.y));
	}
	
	public Vector2Di toInt() {
		return this;
	}
	
	public Vector2Di add(Vector otherVec) { 
		Vector2Di other = otherVec.toInt();
		return new Vector2Di(this.x + other.x, this.y + other.y);
	}
	
	public Vector2Di sub(Vector otherVec) { 
		Vector2Di other = otherVec.toInt();
		return new Vector2Di(this.x - other.x, this.y - other.y);
	}
	
	public Vector2Di mult(Vector otherVec) {
		Vector2Di other = otherVec.toInt();
		return new Vector2Di(this.x * other.x, this.y * other.y);
	}
	
	public Vector2Di divd(Vector otherVec) {
		Vector2Di other = otherVec.toInt();
		return new Vector2Di(this.x / other.x, this.y / other.y);
	}
	
	public void scal(float s) { this.x *= s; this.y *= s;}
	
	public float abs() { return (float) Math.sqrt(Math.pow(x, 2f) + Math.pow(y, 2f));}
	
	public void setX(int x) {
		this.x = xLimit.set(x);
	}

	public void setY(int y) {
		this.y = xLimit.set(y);
	}
	
	public int getX() { return x;}

	public int getY() { return y;}

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
		return "("+this.x+"i, "+this.y+"i)";
	}
}
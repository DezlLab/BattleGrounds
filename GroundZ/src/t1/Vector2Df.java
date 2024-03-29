package t1;

public class Vector2Df extends Vector{
	
	private float x;
	private float y;
	
	interface Limiter {
		float set (float f);
	}
	
	private Limiter xLimit = (f) -> {return f;};
	private Limiter yLimit = (f) -> {return f;};
	
	public Vector2Df() {
		this.setX(0.0f);
		this.setY(0.0f);
	}
	
	public Vector2Df(float x, float y) {
		this.setX(x);
		this.setY(y);
	}
	
	public Vector2Df(Vector newVec) {
		Vector2Df vec = newVec.toFloat();
		this.setX(vec.getX());
		this.setX(vec.getY());
	}
	
	public Vector2Df toFloat() {
		return this;
	}
	
	public Vector2Di toInt() {
		return new Vector2Di((int)(this.x), (int)(this.y));
	}
	
	public Vector2Df add(Vector otherVec) { 
		Vector2Df other = otherVec.toFloat();
		return new Vector2Df(this.x + other.x, this.y + other.y);
	}
	
	public Vector2Df sub(Vector otherVec) { 
		Vector2Df other = otherVec.toFloat();
		return new Vector2Df(this.x - other.x, this.y - other.y);
	}
	
	public Vector2Df mult(Vector otherVec) {
		Vector2Df other = otherVec.toFloat();
		return new Vector2Df(this.x * other.x, this.y * other.y);
	}
	
	public Vector2Df divd(Vector otherVec) {
		Vector2Df other = otherVec.toFloat();
		return new Vector2Df(this.x / other.x, this.y / other.y);
	}
	
	public void scal(float s) { this.x *= s; this.y *= s;}
	
	public float abs() { return (float) Math.sqrt(Math.pow(x, 2f) + Math.pow(y, 2f));}
	
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
		return "("+this.x+"f, "+this.y+"f)";
	}
}
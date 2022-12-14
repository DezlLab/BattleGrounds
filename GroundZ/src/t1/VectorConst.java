package t1;

public class VectorConst extends Vector{
	private Vector vec;
	
	public VectorConst(Vector vec) {
		this.vec = vec;
	}

	@Override
	public Vector2Df toFloat() {
		return vec.toFloat();
	}

	@Override
	public Vector2Di toInt() {
		return vec.toInt();
	}

	@Override
	public Vector add(Vector other) {
		return vec.add(other);
	}

	@Override
	public Vector sub(Vector other) {
		return vec.sub(other);
	}

	@Override
	public Vector mult(Vector other) {
		return vec.mult(other);
	}

	@Override
	public Vector divd(Vector other) {
		return vec.divd(other);
	}

	@Override
	public void scal(float s) {
		System.err.println("You cant scale a const vec");
	}

	@Override
	public float abs() {
		return vec.abs();
	}

	@Override
	public String toString() {
		return vec.toString();
	}
}

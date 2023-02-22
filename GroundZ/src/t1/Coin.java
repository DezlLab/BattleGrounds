package t1;

public class Coin extends GameObject{

	public Coin() {
		setLook();
	}

	void setLook() {
		look = "₿";
	}

	boolean canEnter(String direction) {
		return true;
	}

	boolean canIneract(String direction) {
		return false;
	}

	@Override
	boolean canCollect() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	boolean canLeave(String direction) {
		// TODO Auto-generated method stub
		return false;
	}

}

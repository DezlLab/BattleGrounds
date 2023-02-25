package t1;

public class NormalStone extends GameObject {


	public NormalStone() {
		setLook();
	}


	boolean canEnter(String direction) {
		return true;
	}


	boolean canIneract(String direction) {
		return false;
	}

	void setLook() {
		look = ".";
	}

	
	public String toString() {
		return  look ;
	}



	@Override
	boolean canCollect() {
		// TODO Auto-generated method stub
		return false;
	}


	@Override
	boolean canLeave(String direction) {
		// TODO Auto-generated method stub
		return true;
	}

}

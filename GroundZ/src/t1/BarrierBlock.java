package t1;

public class BarrierBlock extends GameObject
{

	public BarrierBlock() {
		setLook();
	}

	void setLook() 
	{
		look = "X";
	}

	boolean canEnter(String direction) {
		return false;
	}

	boolean canIneract(String direction) {
		return false;
	}

	@Override
	boolean canCollect() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	boolean canLeave(String direction) {
		// TODO Auto-generated method stub
		return false;
	}

}

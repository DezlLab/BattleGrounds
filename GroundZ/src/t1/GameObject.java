package t1;

public abstract class GameObject 
{
	protected String look;
	abstract void setLook();
	abstract boolean canEnter(String direction);
	abstract boolean canLeave(String direction);
	abstract boolean canIneract(String direction);
	abstract boolean canCollect();
	
	public String toString() {
		return  look ;
	}

}

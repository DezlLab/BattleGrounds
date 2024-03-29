package t1;

public class Tunnel extends GameObject{
	
	private boolean facingSouth;
		
	public Tunnel(boolean facingSouth) {
		this.facingSouth = facingSouth;
		setLook();
	}

	@Override
	void setLook() {
		if (facingSouth)
			look = "‖";
		else
			look = "=";
		
	}

	@Override
	boolean canEnter(String direction) {
		if (facingSouth)
		
		switch (direction) {
		case "South":
			return true;
		case "North":
			return true;
		case "East":
			return false;
		case "West":
			return false;
		default: return false;
		}
	else
		switch (direction) {
		case "South":
			return false;
		case "North":
			return false;
		case "East":
			return true;
		case "West":
			return true;
		default: return false;
		}
	}

	@Override
	boolean canLeave(String direction) {
		if (facingSouth)
			
			switch (direction) {
			case "South":
				return true;
			case "North":
				return true;
			case "East":
				return false;
			case "West":
				return false;
			default: return false;
			}
		else
			switch (direction) {
			case "South":
				return false;
			case "North":
				return false;
			case "East":
				return true;
			case "West":
				return true;
			default: return false;
			}
	}

	@Override
	boolean canIneract(String direction) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	boolean canCollect() {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean isFacingSouth() {
		return facingSouth;
	}

	public void setFacingSouth(boolean facingSouth) {
		this.facingSouth = facingSouth;
	}

}

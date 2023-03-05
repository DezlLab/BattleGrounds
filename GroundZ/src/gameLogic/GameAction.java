package gameLogic;

import t1.Vector;
import t1.Vector2Di;
import t1.VectorConst;

public class GameAction {
	
	private String actionName;
	private Vector2Di direction;
	
	public GameAction(String actionName, Vector2Di direction) {
		this.actionName = actionName;
		this.direction = direction;
	}

	public static GameAction collectCoin() {
		return new GameAction("collectCoin", Vector.zero().toInt());
	}
	
	public static GameAction move(VectorConst v) {
		return new GameAction("move", v.toInt());
	}
	
	public static GameAction rotate(int deg) {
		return new GameAction("rotate", (new Vector2Di((int)(Math.cos(deg)), (int)(Math.cos(deg)))));
	}
	
	public String getActionName() {
		return actionName;
	}

	public Vector2Di getDirection() {
		return direction;
	}

	@Override
	public String toString() {
		return "GameAction [actionName=" + actionName + ", direction=" + direction + "]";
	}

}

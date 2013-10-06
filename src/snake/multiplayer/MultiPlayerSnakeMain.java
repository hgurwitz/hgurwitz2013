package snake.multiplayer;

import snake.GameType;
import snake.SnakeMain;

public class MultiPlayerSnakeMain extends SnakeMain {

	public MultiPlayerSnakeMain() {
		super(GameType.MULTI_PLAYER);
	}

	public static void main(String[] args) {
		new MultiPlayerSnakeMain();
	}

}

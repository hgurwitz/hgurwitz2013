package snake.multiplayer;

import snake.SnakeMain;
import snake.enums.GameType;

public class MultiPlayerSnakeMain extends SnakeMain {

	public MultiPlayerSnakeMain() {
		super(GameType.MULTI_PLAYER);
	}

	public static void main(String[] args) {
		new MultiPlayerSnakeMain();
	}

}

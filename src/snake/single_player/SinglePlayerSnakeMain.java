package snake.single_player;

import snake.SnakeMain;
import snake.enums.GameType;

public class SinglePlayerSnakeMain extends SnakeMain {

	public SinglePlayerSnakeMain() {
		super(GameType.SINGLE_PLAYER);
	}

	public static void main(String[] args) {
		new SinglePlayerSnakeMain();
	}

}

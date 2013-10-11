package snake.single_player;

import snake.SnakeMain;

public class SinglePlayerSnakeMain extends SnakeMain {

	public SinglePlayerSnakeMain() {
		super(new SinglePlayerGameController());
	}

	public static void main(String[] args) {
		new SinglePlayerSnakeMain();
	}

}

package snake.multiplayer;

import snake.SnakeMain;

public class MultiPlayerSnakeMain extends SnakeMain {

	public MultiPlayerSnakeMain() {
		super(new MultiPlayerGameController());
	}

	public static void main(String[] args) {
		new MultiPlayerSnakeMain();
	}

}

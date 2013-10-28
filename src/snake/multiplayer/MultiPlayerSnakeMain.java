package snake.multiplayer;

import snake.SnakeMain;

public class MultiPlayerSnakeMain extends SnakeMain {

	private static final long serialVersionUID = 1L;

	public MultiPlayerSnakeMain() {
		super(new MultiPlayerGameController());
	}

	public static void main(String[] args) {
		new MultiPlayerSnakeMain();
	}

}

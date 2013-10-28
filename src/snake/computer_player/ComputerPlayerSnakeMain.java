package snake.computer_player;

import snake.SnakeMain;

public class ComputerPlayerSnakeMain extends SnakeMain {

	private static final long serialVersionUID = 1L;

	public ComputerPlayerSnakeMain() {
		super(new ComputerPlayerGameController());
	}

	public static void main(String[] args) {
		new ComputerPlayerSnakeMain();
	}

}

package snake.computer_player;

import snake.SnakeMain;
import snake.enums.GameType;

public class ComputerPlayerSnakeMain extends SnakeMain {

	public ComputerPlayerSnakeMain() {
		super(new ComputerPlayerGameController());
	}

	public static void main(String[] args) {
		new ComputerPlayerSnakeMain();
	}

}

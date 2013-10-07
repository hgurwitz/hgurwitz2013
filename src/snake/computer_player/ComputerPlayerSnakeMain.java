package snake.computer_player;

import snake.SnakeMain;
import snake.enums.GameType;

public class ComputerPlayerSnakeMain extends SnakeMain {

	public ComputerPlayerSnakeMain() {
		super(GameType.COMPUTER_PLAYER);
	}

	public static void main(String[] args) {
		new ComputerPlayerSnakeMain();
	}

}

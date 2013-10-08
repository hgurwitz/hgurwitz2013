package snake.computer_player;

import snake.ComputerSnake;
import snake.GameController;

public class ComputerPlayerGameController extends GameController {

	public ComputerPlayerGameController() {
		super();
		initializeComputerSnake();
	}

	public void checkGameStuff() {
		if (!gameOver) {
			if (timer.isTimeToMove()) {
				snakeMove(computerSnake);
			}
		}
	}

	public ComputerSnake getComputerSnake() {
		return computerSnake;
	}

}

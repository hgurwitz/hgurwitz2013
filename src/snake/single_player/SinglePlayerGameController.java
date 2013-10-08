package snake.single_player;

import snake.GameController;

public class SinglePlayerGameController extends GameController {

	public SinglePlayerGameController() {
		super();
		initializePlayerSnake();
	}

	public void checkGameStuff() {
		if (!gameOver) {
			if (timer.isTimeToMove()) {
				snakeMove(playerSnake);
			}
		}
	}

}

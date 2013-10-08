package snake.multiplayer;

import snake.GameController;
import snake.Piece;
import snake.SnakeView;

public class MultiPlayerGameController extends GameController {

	public MultiPlayerGameController() {
		super();
		initializePlayerSnake();
		initialY = (SnakeView.SIDELENGTH / 3);
		initialY -= (initialY % Piece.SIZE);
		initialY = SnakeView.SIDELENGTH - initialY;
		initializeComputerSnake();
	}

	public void checkGameStuff() {
		if (!gameOver) {
			if (timer.isTimeToMove()) {
				snakeMove(computerSnake);
				snakeMove(playerSnake);
			}
		}
	}

}

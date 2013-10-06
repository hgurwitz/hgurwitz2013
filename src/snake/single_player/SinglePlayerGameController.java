package snake.single_player;

import snake.BodyPiece;
import snake.GameController;
import snake.KeyboardListener;
import snake.SnakeBody;

public class SinglePlayerGameController extends GameController {

	private SnakeBody playerSnake;

	public SinglePlayerGameController() {
		super();
		System.out.println("initialLoc" + initialLoc);
		playerSnake = new SnakeBody(new BodyPiece(initialLoc, initialLoc),
				initialSnakeLength);
		listener = new KeyboardListener(playerSnake, this);
		board.setPlayerSnake(playerSnake);
	}

	public SnakeBody getSnake() {
		return playerSnake;
	}

	public void checkGameStuff() {
		super.checkGameStuff();
		if (!gameOver) {
			if (timer.isTimeToMove()) {
				playerSnake.move();
				if (playerSnake.detectCollisionsWithFood(food.getXY())) {
					foundFood();
				}
				if (playerSnake.detectCollision(board)) {
					detectedCollision();
				}
				board.setPlayerSnake(playerSnake.getHead());
				board.setEmpty(playerSnake.getOldTail());
			}
		}
	}

	protected void foundFood() {
		super.foundFood();
		board.setEmpty(food);
		food = generator.getNewPieceOfFood(board);
		board.setFood(food);
		playerSnake.addPiece();
		board.setPlayerSnake(playerSnake.getTail());
	}

}

package snake.multiplayer;

import snake.BodyPiece;
import snake.ComputerSnake;
import snake.Direction;
import snake.GameController;
import snake.KeyboardListener;
import snake.Piece;
import snake.SnakeBody;
import snake.SnakeView;

public class MultiPlayerGameController extends GameController {

	private SnakeBody playerSnake;
	private ComputerSnake computerSnake;
	private KeyboardListener listener;

	public MultiPlayerGameController() {
		super();
		playerSnake = new SnakeBody(new BodyPiece(initialLoc, initialLoc),
				initialSnakeLength);
		listener = new KeyboardListener(playerSnake, this);
		initialLoc = (SnakeView.SIDELENGTH / 4) % Piece.SIZE;
		computerSnake = new ComputerSnake(new BodyPiece(initialLoc, initialLoc,
				Direction.LEFT), initialSnakeLength, board);
		computerSnake.setFood(food);
		board.setComputerSnake(computerSnake);
		board.setPlayerSnake(playerSnake);
	}

	public KeyboardListener getListener() {
		return listener;
	}

	public SnakeBody getSnake() {
		return playerSnake;
	}

	public void checkGameStuff() {
		super.checkGameStuff();
		if (!gameOver) {
			if (timer.isTimeToMove()) {
				computerSnake.move();
				playerSnake.move();
				if (playerSnake.detectCollisionsWithFood(food.getXY())) {
					foundFood(playerSnake);
				}
				if (computerSnake.detectCollisionsWithFood(food.getXY())) {
					foundFood(computerSnake);
				}
				if (playerSnake.detectCollision(board)
						|| computerSnake.detectCollision(board)) {
					System.out.println("Detected snake collision");
					detectedCollision();
				}
				board.setComputerSnake(computerSnake.getHead());
				board.setEmpty(computerSnake.getOldTail());
				board.setPlayerSnake(playerSnake.getHead());
				board.setEmpty(playerSnake.getOldTail());
			}
		}
	}

	protected void foundFood(SnakeBody snake) {
		super.foundFood();
		board.setEmpty(food);
		food = generator.getNewPieceOfFood(board);
		board.setFood(food);
		computerSnake.setFood(food);
		if (snake instanceof ComputerSnake) {
			computerSnake.addPiece();
			board.setComputerSnake(computerSnake.getTail());
		} else {
			snake.addPiece();
			board.setPlayerSnake(snake.getTail());
		}
	}

	public ComputerSnake getComputerSnake() {
		return computerSnake;
	}
}

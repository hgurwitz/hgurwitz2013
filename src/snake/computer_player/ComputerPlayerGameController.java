package snake.computer_player;

import snake.BodyPiece;
import snake.ComputerSnake;
import snake.GameController;
import snake.enums.Direction;

public class ComputerPlayerGameController extends GameController {

	private ComputerSnake computerSnake;

	public ComputerPlayerGameController() {
		super();
		computerSnake = new ComputerSnake(new BodyPiece(initialLoc, initialLoc,
				Direction.LEFT), initialSnakeLength, board);
		computerSnake.setFood(food);
		board.setComputerSnake(computerSnake);
	}

	public void checkGameStuff() {
		super.checkGameStuff();
		if (!gameOver) {
			if (timer.isTimeToMove()) {
				computerSnake.move();
				if (computerSnake.detectCollisionsWithFood(food.getXY())) {
					foundFood();
				}
				if (computerSnake.detectCollision(board)) {
					System.out.println("Detected snake collision");
					detectedCollision();
				}
				board.setComputerSnake(computerSnake.getHead());
				board.setEmpty(computerSnake.getOldTail());
			}
		}
	}

	protected void foundFood() {
		super.foundFood();
		board.setEmpty(food);
		food = generator.getNewPieceOfFood(board);
		board.setFood(food);
		computerSnake.setFood(food);
		computerSnake.addPiece();
		board.setComputerSnake(computerSnake.getTail());
	}

	public ComputerSnake getComputerSnake() {
		return computerSnake;
	}

}

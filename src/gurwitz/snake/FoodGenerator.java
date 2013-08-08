package gurwitz.snake;

import java.awt.Color;
import java.util.Random;

public class FoodGenerator {

	private SnakeBody snake;
	private SnakeBody computerSnake;
	private Random random;

	public FoodGenerator(SnakeBody snake, SnakeBody computerSnake) {
		this.snake = snake;
		this.computerSnake = computerSnake;
		this.random = new Random();
	}

	public BodyPiece getNewPieceOfFood() {
		int x, y;
		do {
			x = (random.nextInt(SnakeView.SIDELENGTH));
			x -= (x % BodyPiece.SIZE); // -truncates so it's aligned with the
										// snake
			y = (random.nextInt(SnakeView.SIDELENGTH));
			y -= (y % BodyPiece.SIZE);
		} while (snake.detectCollisionsWithAPiece(x, y)
				|| computerSnake.detectCollisionsWithAPiece(x, y));
		BodyPiece p = new BodyPiece(Color.MAGENTA, x, y);
		return p;
	}

	/*
	 * public BodyPiece getNewPieceOfFoodAtEdgeOfBoard() {
	 * int x, y;
	 * x = SnakeView.SIDELENGTH;
	 * x -= (x % BodyPiece.SIZE);
	 * y = SnakeView.SIDELENGTH;
	 * y -= (x % BodyPiece.SIZE);
	 * BodyPiece p = new BodyPiece(Color.MAGENTA, x, y);
	 * return p;
	 * }
	 */
}

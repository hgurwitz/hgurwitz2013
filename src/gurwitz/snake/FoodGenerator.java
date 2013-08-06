package gurwitz.snake;

import java.awt.Color;
import java.util.Random;

public class FoodGenerator {

	private SnakeBody snake;
	private Random random;

	public FoodGenerator(SnakeBody snake) {
		this.snake = snake;
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
		} while (snake.detectCollisionsWithAPiece(x, y));
		BodyPiece p = new BodyPiece(Color.MAGENTA, x, y);
		return p;
	}

	public BodyPiece getNewPieceOfFoodAtEdgeOfBoard() {
		int x, y;
		x = SnakeView.SIDELENGTH;
		x -= (x % BodyPiece.SIZE);
		y = SnakeView.SIDELENGTH;
		y -= (x % BodyPiece.SIZE);
		BodyPiece p = new BodyPiece(Color.MAGENTA, x, y);
		return p;
	}
}

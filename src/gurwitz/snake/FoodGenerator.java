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
			x = (random.nextInt(SnakeMain.SIDELENGTH));
			x -= (x % BodyPiece.SIZE); // -truncates so it's aligned with the
										// snake
			y = (random.nextInt(SnakeMain.SIDELENGTH));
			y -= (y % BodyPiece.SIZE);
		} while (snake.detectCollisionsWithAPiece(x, y));
		BodyPiece p = new BodyPiece(Color.RED, x, y);
		return p;
	}

}

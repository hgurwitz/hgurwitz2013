package snake;

import java.util.Random;

import snake.enums.SquareContents;


public class FoodGenerator {
	private Random random;

	public FoodGenerator() {
		this.random = new Random();
	}

	public BodyPiece getNewPieceOfFood(Board board) {
		int x, y;
		do {
			x = (random.nextInt(SnakeView.SIDELENGTH));
			x -= (x % BodyPiece.SIZE); // -truncates so it's aligned with the
										// snake
			y = (random.nextInt(SnakeView.SIDELENGTH));
			y -= (y % BodyPiece.SIZE);
		} while (board.getContentsOfASquare(x, y) != SquareContents.EMPTY);
		BodyPiece p = new BodyPiece(x, y);
		return p;
	}

}

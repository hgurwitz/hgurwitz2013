package snake.computer_player;

import java.awt.Color;
import java.util.Random;

public class FoodGenerator {

	// /private ArrayList<Piece> obstacles;
	// private ComputerSnake computerSnake;
	private Random random;
	private Board board;

	public FoodGenerator(Board board) {
		// this.computerSnake = computerSnake;
		// this.obstacles = obstacles;
		this.random = new Random();
		this.board = board;
	}

	public BodyPiece getNewPieceOfFood() {
		int x, y;
		do {
			x = (random.nextInt(SnakeView.SIDELENGTH));
			x -= (x % BodyPiece.SIZE); // -truncates so it's aligned with the
										// snake
			y = (random.nextInt(SnakeView.SIDELENGTH));
			y -= (y % BodyPiece.SIZE);
		} // while (computerSnake.detectCollisionsWithAPiece(x, y)
			// || checkForCollisionWithObstacles(x, y));
		while (board.getContentsOfASquare(x, y) != SquareContents.EMPTY);
		BodyPiece p = new BodyPiece(Color.MAGENTA, x, y);
		return p;
	}

	/*
	 * private boolean checkForCollisionWithObstacles(int x, int y) {
	 * for (Piece p : obstacles) {
	 * if (p.detectCollisionWithAnotherPiece(x, y)) {
	 * return true;
	 * }
	 * }
	 * return false;
	 * }
	 */
}

package snake.computer_player;

import java.util.Random;

import snake.Board;
import snake.SquareContents;

public class FoodGenerator {

	// /private ArrayList<Piece> obstacles;
	// private ComputerSnake computerSnake;
	private Random random;

	public FoodGenerator() {
		// this.computerSnake = computerSnake;
		// this.obstacles = obstacles;
		this.random = new Random();
	}

	public BodyPiece getNewPieceOfFood(Board board) {
		int x, y;
		do {
			x = (random.nextInt(ComputerPlayerSnakeView.SIDELENGTH));
			x -= (x % BodyPiece.SIZE); // -truncates so it's aligned with the
										// snake
			y = (random.nextInt(ComputerPlayerSnakeView.SIDELENGTH));
			y -= (y % BodyPiece.SIZE);
		} // while (computerSnake.detectCollisionsWithAPiece(x, y)
			// || checkForCollisionWithObstacles(x, y));
		while (board.getContentsOfASquare(x, y) != SquareContents.EMPTY);
		BodyPiece p = new BodyPiece(x, y);
		return p;
	}

	/*
	 * private boolean checkForCollisionWithObstacles(int x, int y) { for (Piece
	 * p : obstacles) { if (p.detectCollisionWithAnotherPiece(x, y)) { return
	 * true; } } return false; }
	 */
}

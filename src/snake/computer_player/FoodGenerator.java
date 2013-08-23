package snake.computer_player;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Random;

public class FoodGenerator {

	private ArrayList<Piece> obstacles;
	private ComputerSnake computerSnake;
	private Random random;

	public FoodGenerator(ComputerSnake computerSnake, ArrayList<Piece> obstacles) {
		this.computerSnake = computerSnake;
		this.obstacles = obstacles;
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
		} while (computerSnake.detectCollisionsWithAPiece(x, y)
				|| checkForCollisionWithObstacles(x, y));
		BodyPiece p = new BodyPiece(Color.MAGENTA, x, y);
		return p;
	}

	private boolean checkForCollisionWithObstacles(int x, int y) {
		for (Piece p : obstacles) {
			if (p.detectCollisionWithAnotherPiece(x, y)) {
				return true;
			}
		}
		return false;
	}
}

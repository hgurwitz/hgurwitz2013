package gurwitz.snake;

import java.awt.Color;
import java.util.HashMap;

public class ComputerSnake extends SnakeBody {

	private SnakeBody snake;
	private BodyPiece food;
	private HashMap<Direction, Direction> dirsOpps;

	public ComputerSnake(BodyPiece head, int initialLength,
			SnakeBody otherSnake, BodyPiece food) {
		super(head, initialLength, otherSnake);
		head.setColor(Color.RED);
		this.food = food;
		dirsOpps = new HashMap<Direction, Direction>();
		dirsOpps.put(Direction.UP, Direction.DOWN);
		dirsOpps.put(Direction.DOWN, Direction.UP);
		dirsOpps.put(Direction.LEFT, Direction.RIGHT);
		dirsOpps.put(Direction.RIGHT, Direction.LEFT);

	}

	public void move() {
		if (food == null) {
			System.out.println("food is null");
		}
		if (head == null) {
			System.out.println("head is null");
		}
		Direction currdir = head.getDir();
		if (food.getX() > head.getX() && currdir != Direction.LEFT) {
			head.setDir(Direction.RIGHT);
		} else if (food.getX() < head.getX()
				&& head.getDir() != Direction.RIGHT) {
			head.setDir(Direction.LEFT);
		} else if (food.getY() < head.getY() && currdir != Direction.DOWN) {
			head.setDir(Direction.UP);
		} else if (food.getY() > head.getY() && currdir != Direction.UP) {
			head.setDir(Direction.DOWN);
		}

		head.move(0);
		// if collision then undo

		// while (super.detectCollision()) {
		// if (super.detectCollision()) {
		// head.undoMove();
		// then move different direction
		/*
		 * Direction[] dirArray = Direction.values();
		 * for (Direction d : dirArray) {
		 * // if d is not new dir (that resulted in collision) or opp of
		 * // current dir
		 * if (!d.equals(head.getDir())
		 * && !d.equals(dirsOpps.get(currdir))) {
		 * head.setDir(d);
		 * System.out.println("changing dir to " + d.toString());
		 * }
		 * }
		 */
		// head.move();
		// System.out.println("Undid move then moved");
		// }

	}

	public boolean isThisDirectionGood() {
		Direction currdir = head.getDir();
		switch (currdir) {
		case UP:
			break;
		case DOWN:
			break;
		case LEFT:
			break;
		case RIGHT:
			break;
		}
		return (Boolean) null;
	}

	public void setFood(BodyPiece food) {
		this.food = food;

	}

	public void undoMove() {
		head.moveBackward(0);

	}

}

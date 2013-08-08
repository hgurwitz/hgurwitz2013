package gurwitz.snake;

import java.awt.Color;
import java.util.HashMap;
import java.util.Random;

public class ComputerSnake extends SnakeBody {

	private SnakeBody snake;
	private BodyPiece food;
	private HashMap<Direction, Direction> dirsOpps;
	private Random r;

	public ComputerSnake(BodyPiece head, int initialLength,
			SnakeBody otherSnake, BodyPiece food) {
		super(head, initialLength, otherSnake);
		head.setColor(Color.RED);
		this.food = food;
		r = new Random();
		dirsOpps = new HashMap<Direction, Direction>();
		dirsOpps.put(Direction.UP, Direction.DOWN);
		dirsOpps.put(Direction.DOWN, Direction.UP);
		dirsOpps.put(Direction.LEFT, Direction.RIGHT);
		dirsOpps.put(Direction.RIGHT, Direction.LEFT);

	}

	public ComputerSnake(BodyPiece head, int initialLength, SnakeBody otherSnake) {
		this(head, initialLength, otherSnake, null);

	}

	public void move() {
		if (food == null) {
			System.out.println("food is null");
		}
		if (head == null) {
			System.out.println("head is null");
		}

		Direction currdir = head.getDir();
		if (!isThisDirectionGood()) {

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
		}

		head.move(0);
		Direction newdir = head.getDir();
		// if collision then undo

		while (super.detectCollision()) {
			head.moveBackward();
			// then move different direction

			Direction[] dirArray = Direction.values();
			int index = r.nextInt(4);
			Direction d = dirArray[index];
			// if d is not new dir (that resulted in collision) or opp of
			// current dir
			if (!d.equals(newdir) && !d.equals(dirsOpps.get(currdir))) {
				System.out.println("newdir " + newdir + " currDir: " + currdir);
				head.setDir(d);
				System.out.println("changing dir to " + d.toString());
			}

			head.move();
		}

	}

	public boolean isThisDirectionGood() {
		Direction currdir = head.getDir();
		switch (currdir) {
		case UP:
			return (food.getY() < head.getY());
		case DOWN:
			return (food.getY() > head.getY());
		case LEFT:
			return (food.getX() < head.getX());
		case RIGHT:
			return (food.getX() > head.getX());
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

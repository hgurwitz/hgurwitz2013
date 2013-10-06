package snake.computer_player;

import java.util.EmptyStackException;
import java.util.Stack;

import snake.Board;
import snake.Direction;
import snake.Piece;
import snake.SquareContents;

public class BodyPiece extends Piece {

	private Direction dir;
	private Stack<Direction> prevDirs;
	private BodyPiece nextNode, prevNode;

	public BodyPiece(int x, int y) {
		this(new Piece(x, y));
	}

	public BodyPiece(Piece p) {
		this(p, Direction.LEFT);
	}

	public BodyPiece(Piece p, Direction dir) {
		super(p);
		this.dir = dir;
		prevDirs = new Stack<Direction>();
		prevDirs.push(dir);
	}

	public BodyPiece(int x, int y, Direction dir) {
		super(x, y);
		this.dir = dir;
		prevDirs = new Stack<Direction>();
		prevDirs.push(dir);
	}

	public BodyPiece(BodyPiece p) {
		this(new Piece(p.getXY()), p.getDir());
	}

	public Direction getPrevDir() {
		return prevDirs.peek();
	}

	public void setNext(BodyPiece nextNode) {
		this.nextNode = nextNode;
	}

	public void setPrev(BodyPiece prevNode) {
		this.prevNode = prevNode;
	}

	public BodyPiece getNextNode() {
		return nextNode;
	}

	public void setNextNode(BodyPiece nextNode) {
		this.nextNode = nextNode;
	}

	public BodyPiece getPrevNode() {
		return prevNode;
	}

	public void setPrevNode(BodyPiece prevNode) {
		this.prevNode = prevNode;
	}

	public void setDir(Direction newDir) {
		prevDirs.push(this.dir);
		this.dir = newDir;
	}

	/*
	 * public void paint(Graphics g) { g2 = (Graphics2D) g;
	 * g2.setComposite(ALPHA); g.fillRoundRect(xy.getX(), xy.getY(), SIZE, SIZE,
	 * 10, 10); if (nextNode != null) { nextNode.paint(g); }
	 * 
	 * }
	 */

	public void move() {
		move(0);
	}

	public void move(int numPiece) {
		switch (dir) {
		case UP:
			setY(xy.getY() - SIZE);
			break;
		case DOWN:
			setY(xy.getY() + SIZE);
			break;
		case LEFT:
			setX(xy.getX() - SIZE);
			break;
		case RIGHT:
			setX(xy.getX() + SIZE);
			break;
		}
		if (nextNode != null) {
			nextNode.move(++numPiece);
			nextNode.setDir(dir);
		}

	}

	public void moveBackward() {
		moveBackward(0);
	}

	public void moveBackward(int numPiece) {
		if (prevNode == null) {
			undoMove();
			revertToPrevDir();
		} else {
			try {
				revertToPrevDir();
			} catch (EmptyStackException e) {
				throw e;
			}
			undoMove();
		}

		if (nextNode != null) {
			try {
				nextNode.moveBackward(++numPiece);
			} catch (EmptyStackException e) {
				// the next piece didn't exist then...remove it
				nextNode = null;
				System.out.println("Removed last piece");
			}
		}
	}

	private void undoMove() {
		switch (dir) {
		case UP:
			setY(xy.getY() + SIZE);
			break;
		case DOWN:
			setY(xy.getY() - SIZE);
			break;
		case LEFT:
			setX(xy.getX() + SIZE);
			break;
		case RIGHT:
			setX(xy.getX() - SIZE);
			break;
		}
	}

	private void revertToPrevDir() throws EmptyStackException {
		try {
			dir = prevDirs.pop();
		} catch (EmptyStackException e) {
			throw e;
		}
	}

	public Direction getDir() {
		return dir;
	}

	public boolean detectCollisionWithBody(Board board) {

		SquareContents content = board.getContentsOfASquare(xy);
		if (content.equals(SquareContents.COMP_SNAKEPIECE)
				|| content.equals(SquareContents.PLAYER_SNAKEPIECE)) {
			System.out.println("Detected collision with body");
		}
		return (content.equals(SquareContents.COMP_SNAKEPIECE) || content
				.equals(SquareContents.PLAYER_SNAKEPIECE));
	}

	public boolean detectCollisionWithWalls() {
		int x = xy.getX(), y = xy.getY();
		if (x < 0 || ((x + SIZE) > (ComputerPlayerSnakeView.SIDELENGTH)) || y < 0
				|| ((y + SIZE) > (ComputerPlayerSnakeView.SIDELENGTH))) {
			return true;
		}
		return false;
	}

	public boolean detectCollisionWithObstacles(Board board) {
		return (board.getContentsOfASquare(xy).equals(SquareContents.OBSTACLE));
	}

}

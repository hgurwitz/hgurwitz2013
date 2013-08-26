package snake.computer_player;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.EmptyStackException;
import java.util.Stack;

import snake.Direction;

public class BodyPiece extends Piece {

	private Direction dir;
	private Stack<Direction> prevDirs;
	private BodyPiece nextNode, prevNode;

	public BodyPiece(Color color, int x, int y) {
		this(new Piece(color, x, y));
	}

	public BodyPiece(Color color, int x, int y, Direction dir) {
		this(new Piece(color, x, y), dir);
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
		super(Color.CYAN, x, y);
		this.dir = dir;
		prevDirs = new Stack<Direction>();
		prevDirs.push(dir);
	}

	public BodyPiece(BodyPiece p) {
		this(new Piece(p.getColor(), p.getXY()), p.getDir());
	}

	public BodyPiece getNext() {
		return nextNode;
	}

	public Direction getPrevDir() {
		return prevDirs.peek();
	}

	public void setNext(BodyPiece nextNode) {
		this.nextNode = nextNode;
	}

	public BodyPiece getPrev() {
		return prevNode;
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

	public void paint(Graphics g) {
		g2 = (Graphics2D) g;
		g2.setComposite(ALPHA);
		g.setColor(color);
		g.fillRoundRect(xy.getX(), xy.getY(), SIZE, SIZE, 10, 10);
		if (nextNode != null) {
			nextNode.paint(g);
		}

	}

	public void move() {
		move(0);
	}

	public void move(int numPiece) {
		// System.out.println("MOVE called" + numPiece);
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

	// public boolean detectCollision() {

	// return detectCollisionWithWalls() || detectCollisionWithBody(xy);
	// }

	// public boolean detectCollision(Board board) {

	// return detectCollisionWithWalls() || detectCollisionWithBody(board);
	// }

	// public boolean detectCollisionWithBody(XYCoordinate xyTest) {
	// return detectCollisionWithBody(xyTest.getX(), xyTest.getY());
	// }

	public boolean detectCollisionWithBody(Board board) {
		// (int pieceX, int pieceY) {
		// O(1)
		// only called when this piece is the head
		/*
		 * BodyPiece next = nextNode; // don't check head
		 * while (next != null) {
		 * if (detectCollisionWithAnotherPiece(next, pieceX, pieceY)) {
		 * return true;
		 * }
		 * next = next.getNext();
		 * }
		 * return false;
		 */

		if (board.getContentsOfASquare(xy).equals(SquareContents.SNAKEPIECE)) {
			System.out.println("Detected collision with my body");
		}
		return (board.getContentsOfASquare(xy)
				.equals(SquareContents.SNAKEPIECE));
	}

	public boolean detectCollisionWithWalls() {
		// O(1)
		// only called when this piece is the head
		int x = xy.getX(), y = xy.getY();
		if (x < 0 || ((x + SIZE) > (SnakeView.SIDELENGTH)) || y < 0
				|| ((y + SIZE) > (SnakeView.SIDELENGTH))) {
			return true;
		}
		return false;
	}

	public boolean detectCollisionWithObstacles(Board board) { // O(1)
		// only called when this piece is the head
		// (ArrayList<Piece> obstacles) {
		// for (Piece p : obstacles) {
		// if (detectCollisionWithAnotherPiece(this, p.getXY())) {
		// return true;
		// }
		// }
		// return false;
		return (board.getContentsOfASquare(xy).equals(SquareContents.OBSTACLE));
	}

}

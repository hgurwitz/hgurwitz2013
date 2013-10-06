package snake.old;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.EmptyStackException;
import java.util.Stack;

import snake.Direction;

public class BodyPiece {

	private static final AlphaComposite ALPHA = AlphaComposite.getInstance(
			AlphaComposite.SRC_OVER, .6f);
	private Color color;
	public static final int SIZE = 14;
	private int x, y;
	private Direction dir;
	private Stack<Direction> prevDirs;

	private BodyPiece nextNode, prevNode;
	private Graphics2D g2;

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

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	public BodyPiece(Color color, int x, int y) {
		this.color = color;
		this.x = x;
		this.y = y;
		dir = Direction.LEFT;
		prevDirs = new Stack<Direction>();
		prevDirs.push(dir);
	}

	public BodyPiece(int x, int y, Direction dir) {
		this(x, y);
		prevDirs.push(dir);
	}

	public BodyPiece(int x, int y) {
		this(Color.CYAN, x, y);
	}

	public void paint(Graphics g) {
		g2 = (Graphics2D) g;
		g2.setComposite(ALPHA);
		g.setColor(color);
		g.fillRoundRect(x, y, SIZE, SIZE, 10, 10);
		if (nextNode != null) {
			nextNode.paint(g);
		}

	}

	public void move() {
		move(0);
	}

	public void move(int numPiece) {
		switch (dir) {
		case UP:
			y -= SIZE;
			break;
		case DOWN:
			y += SIZE;
			break;
		case LEFT:
			x -= SIZE;
			break;
		case RIGHT:
			x += SIZE;
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
		if (prevNode == null) { // head

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
			y += SIZE;
			break;
		case DOWN:
			y -= SIZE;
			break;
		case LEFT:
			x += SIZE;
			break;
		case RIGHT:
			x -= SIZE;
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

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public boolean detectCollision() {

		return detectCollisionWithWalls()
				|| detectCollisionWithBody(this.x, this.y);
	}

	public boolean detectCollisionWithBody(int xTest, int yTest) {
		BodyPiece next = nextNode;
		while (next != null) {
			if (detectCollisionWithAnotherPiece(next, xTest, yTest)) {
				return true;
			}
			next = next.getNext();
		}
		return false;
	}

	public boolean detectCollisionWithOtherSnake(SnakeBody snake) {
		BodyPiece next = snake.getHead();
		while (next != null) {
			if (detectCollisionWithAnotherPiece(this, next.getX(), next.getY())) {
				return true;
			}
			next = next.getNext();
		}
		return false;
	}

	public void setX(int x) {
		this.x = x;
	}

	public void setY(int y) {
		this.y = y;
	}

	public boolean detectCollisionWithWalls() {
		if (x < 0 || ((x + SIZE) > (ComputerPlayerSnakeView.SIDELENGTH)) || y < 0
				|| ((y + SIZE) > (ComputerPlayerSnakeView.SIDELENGTH))) {
			return true;
		}
		return false;
	}

	public boolean detectCollisionWithAnotherPiece(BodyPiece piece, int xTest,
			int yTest) {
		if (xTest == piece.getX() && yTest == piece.getY()) {
			return true;
		}
		return false;
	}

}

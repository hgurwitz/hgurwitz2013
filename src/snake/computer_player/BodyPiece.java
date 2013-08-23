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

	public BodyPiece(Color color, int x, int y) {
		super(color, x, y);
		dir = Direction.LEFT;
		prevDirs = new Stack<Direction>();
		prevDirs.push(dir);
	}

	public BodyPiece(int x, int y, Direction dir) {
		super(Color.CYAN, x, y);
		prevDirs = new Stack<Direction>();
		prevDirs.push(dir);
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
		// System.out.println("MOVE called" + numPiece);
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

	public void setX(int x) {
		this.x = x;
	}

	public void setY(int y) {
		this.y = y;
	}

	public boolean detectCollisionWithWalls() {
		if (x < 0 || ((x + SIZE) > (SnakeView.SIDELENGTH)) || y < 0
				|| ((y + SIZE) > (SnakeView.SIDELENGTH))) {
			return true;
		}
		return false;
	}

	public boolean detectCollisionWithObstacles(ArrayList<Piece> obstacles) {
		for (Piece p : obstacles) {
			if (detectCollisionWithAnotherPiece(this, p.getX(), p.getY())) {
				return true;
			}
		}
		return false;
	}

}

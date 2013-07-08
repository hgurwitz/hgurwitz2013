package gurwitz.snake;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

public class BodyPiece {

	private static final AlphaComposite ALPHA = AlphaComposite.getInstance(
			AlphaComposite.SRC_OVER, .6f);
	private Color color;
	public static final int SIZE = 14;
	private int x, y;
	private Direction dir;
	private BodyPiece nextNode, prevNode;
	private Graphics2D g2;

	public BodyPiece getNext() {
		return nextNode;
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

	public void setDir(Direction dir) {
		this.dir = dir;
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
	}

	public BodyPiece(int x, int y) {
		this(Color.CYAN, x, y);
	}

	public void paint(Graphics g) {
		g2 = (Graphics2D) g;
		g2.setComposite(ALPHA);
		g.setColor(color);
		g.fillRoundRect(x, y, SIZE, SIZE, 10, 10);
	}

	public void move() {
		switch (dir) {
		case UP:
			moveUp();
			break;
		case DOWN:
			moveDown();
			break;
		case LEFT:
			moveLeft();
			break;
		case RIGHT:
			moveRight();
			break;
		}
		if (nextNode != null) {
			nextNode.move();
			nextNode.setDir(dir);
		}

	}

	public Direction getDir() {
		return dir;
	}

	public void moveUp() {
		y -= SIZE;
	}

	public void moveDown() {
		y += SIZE;
	}

	public void moveRight() {
		x += SIZE;
	}

	public void moveLeft() {
		x -= SIZE;
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

	public boolean detectCollisionWithAnotherPiece(BodyPiece piece, int xTest,
			int yTest) {
		if (xTest == piece.getX() && yTest == piece.getY()) {
			return true;
		}
		return false;
	}

}

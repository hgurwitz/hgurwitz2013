package snake.computer_player;

import snake.Board;
import snake.Direction;
import snake.XYCoordinate;

public class SnakeBody {

	protected BodyPiece head;
	private BodyPiece tail;
	private int initialLength;
	private XYCoordinate oldTail;

	public SnakeBody(BodyPiece head, int initialLength) {
		this.head = head;
		this.tail = head;
		this.initialLength = initialLength;
		oldTail = new XYCoordinate(tail.getXY());
		for (int i = 0; i < (initialLength - 1); i++) {
			addPiece();
		}
	}

	public void addPiece() {
		BodyPiece newPiece = new BodyPiece(0, 0, tail.getPrevDir());
		newPiece.setDir(tail.getDir());

		int tailX = tail.getXY().getX();
		int tailY = tail.getXY().getY();
		int x = tailX, y = tailY;

		switch (tail.getDir()) {
		case UP:
			y = tailY + BodyPiece.SIZE;
			break;
		case DOWN:
			y = tailY - BodyPiece.SIZE;
			break;
		case LEFT:
			x = tailX + BodyPiece.SIZE;
			break;
		case RIGHT:
			x = tailX - BodyPiece.SIZE;
			break;
		}
		newPiece.setX(x);
		newPiece.setY(y);

		newPiece.setPrev(tail);
		tail.setNext(newPiece);
		this.tail = newPiece;
	}

	public int getInitialLength() {
		return initialLength;
	}

	public void setInitialLength(int initialLength) {
		this.initialLength = initialLength;
	}

	/*
	 * public void paint(Graphics g) { head.paint(g); }
	 */

	public void move() {
		oldTail = new XYCoordinate(tail.getXY());
		head.move(0);
	}

	public XYCoordinate getOldTail() {
		return oldTail;
	}

	public void changeDirection(Direction newDir) {
		head.setDir(newDir);
	}

	public boolean detectCollision(Board board) {
		return head.detectCollisionWithWalls()
				|| head.detectCollisionWithBody(board)
				|| head.detectCollisionWithObstacles(board);
	}

	public boolean detectCollisionsWithFood(XYCoordinate xy) {
		return head.getXY().equals(xy);
	}

	public BodyPiece getHead() {
		return head;
	}

	public void setHead(BodyPiece head) {
		this.head = head;
	}

	public BodyPiece getTail() {
		return tail;
	}

	public void setTail(BodyPiece tail) {
		this.tail = tail;
	}

}

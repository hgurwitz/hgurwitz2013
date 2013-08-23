package snake.computer_player;

import java.awt.Graphics;
import java.util.ArrayList;

import snake.Direction;

public class SnakeBody {

	protected BodyPiece head;
	private BodyPiece tail;
	private int initialLength;
	private ArrayList<Piece> obstacles;

	public SnakeBody(BodyPiece head, int initialLength,
			ArrayList<Piece> obstacles) {
		this.obstacles = obstacles;
		this.head = head;
		this.tail = head;
		this.initialLength = initialLength;
		for (int i = 0; i < (initialLength - 1); i++) {
			addPiece();
		}

	}

	public void addPiece() {
		BodyPiece newPiece = new BodyPiece(0, 0, tail.getPrevDir());
		newPiece.setColor(tail.getColor());
		newPiece.setDir(tail.getDir());

		int tailX = tail.getX();
		int tailY = tail.getY();
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

	public void paint(Graphics g) {
		head.paint(g);
	}

	public void move() {
		head.move(0);
	}

	public void changeDirection(Direction newDir) {
		head.setDir(newDir);
	}

	public boolean detectCollision() {
		return head.detectCollision()
				|| head.detectCollisionWithObstacles(obstacles);
	}

	public boolean detectCollisionsWithAPiece(int pieceX, int pieceY) {
		return head.detectCollisionWithBody(pieceX, pieceY);
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

	public void setObstacles(ArrayList<Piece> obstacles) {
		this.obstacles = obstacles;
	}
}

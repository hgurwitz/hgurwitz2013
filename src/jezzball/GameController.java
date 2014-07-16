package jezzball;

import gurwitz.physics2.Projectile;

import java.awt.Cursor;
import java.awt.Graphics;

import javax.swing.JComponent;

public class GameController extends JComponent {

	protected boolean gameOver = false;
	protected Board board;
	protected JezzView view; // needed to change cursor
	protected int initialX, initialY;
	protected MouseClickListener listener;
	private Direction direction;
	protected Projectile projectile;

	public GameController() {
		// initialX = (SnakeView.SIDELENGTH - (initialSnakeLength *
		// Piece.SIZE));
		// initialY = (SnakeView.SIDELENGTH / 3);
		// initialY -= (initialY % Piece.SIZE);
		board = new Board();
		listener = new MouseClickListener(this);
		direction = Direction.HORIZONTAL;
	//	view.setCursor(Cursor.getPredefinedCursor(Cursor.N_RESIZE_CURSOR));
	}

	public Board getBoard() {
		return board;
	}

	public MouseClickListener getListener() {
		return listener;
	}

	protected void detectedCollision() {
		gameOver = true;
		System.out.println("Detected collision");

	}

	public void changeDirection() {
		if (direction == Direction.HORIZONTAL) {
			direction = Direction.VERTICAL;
			view.setCursor(Cursor.getPredefinedCursor(Cursor.N_RESIZE_CURSOR));
		} else {
			direction = Direction.HORIZONTAL;
			view.setCursor(Cursor.getPredefinedCursor(Cursor.W_RESIZE_CURSOR));
		}
	}

	public void paint(Graphics g) {
		board.paint(g);
	}

	public void mouseClicked(int x, int y) {
		x -= x % Square.SIZE;
		y -= y % Square.SIZE;
		System.out.println("XY: " + x + " " + y);
		XYCoordinate xy = new XYCoordinate(x, y);
		board.mouseClicked(xy);

		int max = board.sidelength;
		System.out.println(max);
		if (direction == Direction.HORIZONTAL) {
			int x1 = x, x2 = x;
			x1 -= Square.SIZE;
			x2 += Square.SIZE;
			while (x1 > 0) {
				// System.out.println("XY1: " + x1 + " " + y);
				XYCoordinate xy1 = new XYCoordinate(x1, y);
				SquareContents oldContents = board.mouseClicked(xy1);
				if (oldContents == SquareContents.WALL
						|| oldContents == SquareContents.MOVING_WALL) {
					break;
				}
				x1 -= Square.SIZE;
			}
			while (x2 < max) {
				// System.out.println("XY2: " + x2 + " " + y);
				XYCoordinate xy2 = new XYCoordinate(x2, y);
				SquareContents oldContents = board.mouseClicked(xy2);
				if (oldContents == SquareContents.WALL
						|| oldContents == SquareContents.MOVING_WALL) {
					break;
				}
				x2 += Square.SIZE;
			}
		}
		if (direction == Direction.VERTICAL) {
			int y1 = y, y2 = y;
			y1 -= Square.SIZE;
			y2 += Square.SIZE;
			while (y1 > 0) {
				// System.out.println("XY1: " + x + " " + y1);
				XYCoordinate xy1 = new XYCoordinate(x, y1);
				SquareContents oldContents = board.mouseClicked(xy1);
				if (oldContents == SquareContents.WALL
						|| oldContents == SquareContents.MOVING_WALL) {
					break;
				}
				y1 -= Square.SIZE;
			}
			while (y2 < max) {

				// System.out.println("XY2: " + x + " " + y2);
				XYCoordinate xy2 = new XYCoordinate(x, y2);
				SquareContents oldContents = board.mouseClicked(xy2);
				if (oldContents == SquareContents.WALL
						|| oldContents == SquareContents.MOVING_WALL) {
					break;
				}
				y2 += Square.SIZE;

			}
		}
	//	System.out.println(board.checkForSquare(xy));

		// if the line formed a complete square with no ball in it
		// then fill in the square

	}

	public void setView(JezzView view) {
		this.view = view;
	}

}

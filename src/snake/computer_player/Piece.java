package snake.computer_player;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

public class Piece {

	protected static final AlphaComposite ALPHA = AlphaComposite.getInstance(
			AlphaComposite.SRC_OVER, .6f);
	protected Color color;
	public static final int SIZE = 14;
	protected int x, y;
	protected Graphics2D g2;

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	public Piece(Color color, int x, int y) {
		this.color = color;
		this.x = x;
		this.y = y;
	}

	public Piece(int x, int y) {
		this(Color.BLUE, x, y);
	}

	public void paint(Graphics g) {
		g2 = (Graphics2D) g;
		g2.setComposite(ALPHA);
		g.setColor(color);
		g.fillRect(x, y, SIZE, SIZE);

	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	protected boolean detectCollisionWithAnotherPiece(Piece piece, int xTest,
			int yTest) {
		if (xTest == piece.getX() && yTest == piece.getY()) {
			return true;
		}
		return false;
	}

	protected boolean detectCollisionWithAnotherPiece(int xTest, int yTest) {
		if (x == xTest && y == yTest) {
			return true;
		}
		return false;
	}

}

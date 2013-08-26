package snake.computer_player;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

public class Piece {

	protected static final AlphaComposite ALPHA = AlphaComposite.getInstance(
			AlphaComposite.SRC_OVER, .6f);
	protected Color color;
	public static final int SIZE = 10;
	protected XYCoordinate xy;
	protected Graphics2D g2;

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	public Piece(Color color, int x, int y) {
		this.color = color;
		this.xy = new XYCoordinate(x, y);
	}

	public Piece(Color color, XYCoordinate xy) {
		this.color = color;
		this.xy = xy;
	}

	public Piece(Piece p) {
		this(p.getColor(), p.getXY());
	}

	public Piece(int x, int y) {
		this(Color.BLUE, x, y);
	}

	public void paint(Graphics g) {
		g2 = (Graphics2D) g;
		g2.setComposite(ALPHA);
		g.setColor(color);
		g.fillRect(xy.getX(), xy.getY(), SIZE, SIZE);

	}

	public XYCoordinate getXY() {
		return xy;
	}

	protected boolean detectCollisionWithAnotherPiece(Piece piece,
			XYCoordinate test) {
		return (piece.getXY().equals(test));
	}

	public void setXY(XYCoordinate xy) {
		this.xy = xy;
	}

	public void setX(int x) {
		xy.setX(x);
	}

	public void setY(int y) {
		xy.setY(y);
	}

	protected boolean detectCollisionWithAnotherPiece(Piece piece, int xTest,
			int yTest) {
		return (piece.getXY().equals(xTest, yTest));
	}

	protected boolean detectCollisionWithAnotherPiece(int xTest, int yTest) {
		return (xy.equals(xTest, yTest));
	}

}

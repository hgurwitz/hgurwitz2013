package jezzball;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

public class Square {

	private XYCoordinate xy;
	private SquareContents content;
	private Square parent;
	private int f, g, h;
	protected static final AlphaComposite ALPHA = AlphaComposite.getInstance(
			AlphaComposite.SRC_OVER, .6f);
	protected Graphics2D g2;
	protected Color color;
	public final static int SIZE = 10;

	public Square(XYCoordinate xy, SquareContents content) {
		this.xy = xy;
		this.content = content;
	}

	public XYCoordinate getXy() {
		return xy;
	}

	public SquareContents getContent() {
		return content;
	}

	public Square getParent() {
		return parent;
	}

	public void setParent(Square square) {
		parent = square;
	}

	public int getG() {
		return g;
	}

	public void setG(int i) {
		g = i;
	}

	public void setContent(SquareContents content) {
		this.content = content;
	}

	public int getH() {
		return h;
	}

	public void setH(int i) {
		h = i;
	}

	public int getF() {
		return f;
	}

	public void setF(int i) {
		f = i;
	}

	@Override
	public String toString() {
		return "Square [xy=" + xy + ", content=" + content + ", f=" + f
				+ ", g=" + g + ", h=" + h + "]";
	}

	protected void paint(Graphics g) {
		if (color == null) {
			switch (content) {

			case WALL:
				color = Color.BLUE;
				break;
			case MOVING_WALL:
				color = Color.BLUE;
				break;
			case BALL:
				color = Color.GREEN;
				break;
			case EMPTY:
				color = Color.PINK;
				break;
			}
		}

		if (!content.equals(SquareContents.EMPTY)) {
			g2 = (Graphics2D) g;
			g2.setComposite(ALPHA);
			g.setColor(color);
			g.fillRoundRect(xy.getX(), xy.getY(), SIZE, SIZE, 10, 10);
		}

	}

	public Square getMyTrailParent() {
		return parent;
	}

	public void setMyTrailParent(Square myTrailParent) {
		this.parent = myTrailParent;
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}

}

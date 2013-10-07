package snake;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

import snake.enums.SquareContents;

public class Square {

	private XYCoordinate xy;
	private SquareContents content;
	private Square parent;
	private int f, g, h;
	protected static final AlphaComposite ALPHA = AlphaComposite.getInstance(
			AlphaComposite.SRC_OVER, .6f);
	protected Graphics2D g2;
	protected Color color;

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
		switch (content) {
		case EMPTY:
			color = Color.WHITE;
			break;
		case FOOD:
			color = Color.RED;
			break;
		case COMP_SNAKEPIECE:
			color = Color.GREEN;
			break;
		case PLAYER_SNAKEPIECE:
			color = Color.BLUE;
			break;
		case OBSTACLE:
			color = Color.MAGENTA;
			break;
		}
		g2 = (Graphics2D) g;
		g2.setComposite(ALPHA);
		g2.setColor(color);
		if (!content.equals(SquareContents.EMPTY)) {
			g2.fillRoundRect(xy.getX(), xy.getY(), Piece.SIZE, Piece.SIZE, 10,
					10);
		}

	}
}

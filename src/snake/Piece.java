package snake;


public class Piece {

	public static final int SIZE = 10;
	protected XYCoordinate xy;

	public Piece(int x, int y) {
		this.xy = new XYCoordinate(x, y);
	}

	public Piece(XYCoordinate xy) {
		this.xy = xy;
	}

	public Piece(Piece p) {
		this(p.getXY());
	}

	/*
	 * public void paint(Graphics g) { g2 = (Graphics2D) g;
	 * g2.setComposite(ALPHA); g.fillRect(xy.getX(), xy.getY(), SIZE, SIZE);
	 * 
	 * }
	 */

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

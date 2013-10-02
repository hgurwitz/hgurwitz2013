package snake.computer_player;

public class Square {

	private XYCoordinate xy;
	private SquareContents content;
	private Square parent;
	private int f, g, h;

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

}

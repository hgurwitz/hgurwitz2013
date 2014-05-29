package jezzball;

public class XYCoordinate {

	private int x;
	private int y;

	public XYCoordinate(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public XYCoordinate(XYCoordinate xy) {
		this(xy.getX(), xy.getY());
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + x;
		result = prime * result + y;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		XYCoordinate other = (XYCoordinate) obj;
		if (x != other.x) {
			return false;
		}
		if (y != other.y) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "XYCoordinate [x=" + x + ", y=" + y + "]";
	}

	public boolean equals(int xTest, int yTest) {
		return (x == xTest && y == yTest);
	}

}

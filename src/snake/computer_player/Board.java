package snake.computer_player;

import java.util.ArrayList;
import java.util.HashMap;

public class Board {

	private HashMap<XYCoordinate, Square> squaresMap;

	public Board() {
		squaresMap = new HashMap<XYCoordinate, Square>();
		// add EMPTY squares
		int x = 0, y = 0;

		while (x < SnakeView.SIDELENGTH) {
			while (y < SnakeView.SIDELENGTH) {
				XYCoordinate xy = new XYCoordinate(x, y);
				System.out.print(xy);
				Square s = new Square(xy, SquareContents.EMPTY);
				squaresMap.put(xy, s);
				y += Piece.SIZE;
			}
			System.out.println();
			y = 0;
			x += Piece.SIZE;
		}

	}

	public HashMap<XYCoordinate, Square> getSquaresMap() {
		return squaresMap;
	}

	public void setFood(BodyPiece food) {
		squaresMap.get(food.getXY()).setContent(SquareContents.FOOD);
	}

	public void setSnake(ComputerSnake snake) {
		BodyPiece p = snake.getHead();
		while (p != null) {
			squaresMap.get(p.getXY()).setContent(SquareContents.SNAKEPIECE);
			p = p.getNextNode();
		}
	}

	public void setSnake(BodyPiece piece) {
		XYCoordinate xy = piece.getXY();
		Square s = squaresMap.get(xy);
		s.setContent(SquareContents.SNAKEPIECE);
	}

	public void setObstacles(ArrayList<Piece> obstacles) {
		for (Piece p : obstacles) {
			squaresMap.get(p.getXY()).setContent(SquareContents.OBSTACLE);
		}
	}

	public void setEmpty(XYCoordinate oldTail) {
		squaresMap.get(oldTail).setContent(SquareContents.EMPTY);
	}

	public void setEmpty(BodyPiece food) {
		setEmpty(food.getXY());
	}

	public SquareContents getContentsOfASquare(int x, int y) {
		return getContentsOfASquare(new XYCoordinate(x, y));
	}

	public SquareContents getContentsOfASquare(XYCoordinate xy) {
		return squaresMap.get(xy).getContent();
	}

}

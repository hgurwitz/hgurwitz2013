package snake;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

import snake.enums.SquareContents;

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

	public void setComputerSnake(ComputerSnake snake) {
		BodyPiece p = snake.getHead();
		while (p != null) {
			XYCoordinate xy = p.getXY();
			System.out.println("in setComputerSnake(snake) " + xy);
			Square s = squaresMap.get(xy);
			if (s == null) {
				System.out.println("NULL in setComputerSnake(snake). Xy " + xy);
			}
			s.setContent(SquareContents.COMP_SNAKEPIECE);
			p = p.getNextNode();
		}
	}

	public void setPlayerSnake(SnakeBody snake) {
		BodyPiece p = snake.getHead();
		while (p != null) {
			squaresMap.get(p.getXY()).setContent(
					SquareContents.PLAYER_SNAKEPIECE);
			p = p.getNextNode();
		}
	}

	public void setComputerSnake(BodyPiece piece) {
		XYCoordinate xy = piece.getXY();
		Square s = squaresMap.get(xy);
		if (s == null) {
			System.out.println("NULL in setComputerSnake(). Xy " + xy);
		}
		s.setContent(SquareContents.COMP_SNAKEPIECE);
	}

	public void setPlayerSnake(BodyPiece piece) {
		XYCoordinate xy = piece.getXY();
		Square s = squaresMap.get(xy);
		if (s == null) {
			System.out.println("NULL in setPlayerSnake(). Xy " + xy);
		}
		s.setContent(SquareContents.PLAYER_SNAKEPIECE);
	}

	public void setObstacles(ArrayList<Piece> obstacles) {
		for (Piece p : obstacles) {
			Square s = squaresMap.get(p.getXY());
			if (s == null) {
				System.out.println("NULL in setObstacles(). Xy " + p.getXY());
			}
			s.setContent(SquareContents.OBSTACLE);
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

	public void paint(Graphics g) {
		Set<XYCoordinate> keyset = squaresMap.keySet();
		for (XYCoordinate keyXY : keyset) {
			Square s = squaresMap.get(keyXY);
			s.paint(g);
		}
	}
}

package snake.computer_player;

import java.awt.Color;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Stack;

import snake.Direction;

public class ComputerSnake extends SnakeBody {
	/*
	 * Used this tutorial to implement A-star pathfinding:
	 * http://www.policyalmanac.org/games/aStarTutorial.htm
	 */
	private BodyPiece food;
	private Stack<Square> path;
	private boolean finishedPath = false;
	private HashMap<XYCoordinate, Square> squaresMap;
	private HashMap<XYCoordinate, Square> openList;
	private HashMap<XYCoordinate, Square> closedList;
	private Square start;
	private Square target;

	public ComputerSnake(BodyPiece head, int initialLength,
			ArrayList<Piece> obstacles, BodyPiece food, Board board) {
		super(head, initialLength, board);
		head.setColor(Color.RED);
		this.food = food;
		path = new Stack<Square>();
		finishedPath = false;
		squaresMap = board.getSquaresMap();
		openList = new HashMap<XYCoordinate, Square>();
		closedList = new HashMap<XYCoordinate, Square>();
	}

	public ComputerSnake(BodyPiece head, int initialLength,
			ArrayList<Piece> obstacles, Board board) {
		this(head, initialLength, obstacles, null, board);

	}

	public void move() {
		if (path.isEmpty()) {
			findPath();
		}
		Square s = path.pop();
		clearSquareSettings(s);
		moveToGetToXY(s.getXy());

	}

	public void setFood(BodyPiece food) {
		this.food = food;

	}

	public void undoMove() {
		head.moveBackward(0);

	}

	public void findPath() {

		finishedPath = false;
		openList.clear();
		closedList.clear();
		start = squaresMap.get(head.getXY()); // head square
		target = squaresMap.get(food.getXY()); // food square
		openList.put(start.getXy(), start);
		Square currentSquare = start;
		System.out.println("START: " + start);
		System.out.println("TARGET: " + target);
		while (!openList.isEmpty() && !finishedPath) {
			// look for lowest F cost on open list
			currentSquare = findLowestFOnOpenList(openList);
			if (currentSquare.equals(target)) {
				System.out
						.println("DONE-----------------------------------------");
				// DONE
				finishedPath = true;
			} else {
				// switch it to closed list
				XYCoordinate currentSquareXY = currentSquare.getXy();
				openList.remove(currentSquareXY);
				closedList.put(currentSquareXY, currentSquare);
				// for each of the 4 adjacent squares
				ArrayList<Square> adjSquares = getAdjSquares(currentSquare,
						squaresMap);
				for (Square adj : adjSquares) {
					// if it's walkable
					SquareContents content = adj.getContent();
					if (!content.equals(SquareContents.SNAKEPIECE)
							&& !content.equals(SquareContents.OBSTACLE)) {
						// If it isn’t on the open list, add it to the open
						// list.
						// Make the current square the parent of this square.
						// Record
						// the F, G, and H costs of the square.
						if (!openList.containsKey(adj.getXy())
								&& !closedList.containsKey(adj.getXy())) {
							adj.setParent(currentSquare);
							calculateFGH(adj, target, currentSquare);
							openList.put(adj.getXy(), adj);
						} else {
							// If it is on the open list already, check to see
							// if
							// this path to that square is better, using G cost
							// as
							// the measure. A lower G cost means that this is a
							// better path. If so, change the parent of the
							// square
							// to the current square, and recalculate the G and
							// F
							// scores of the square.
							if (adj.getG() > (currentSquare.getG() + 1)) {
								adj.setParent(currentSquare);
								adj.setG(currentSquare.getG() + 1);
								adj.setF(adj.getG() + adj.getH());
							}

						}
					}
				}
			}
		}
		// either found path, or no walkable path exists
		currentSquare = target;

		// int counter = 0;
		try {
			while (!currentSquare.equals(start) && !(currentSquare == null)) {
				// currentSquare.setMyPathColor(Color.GREEN);
				path.push(currentSquare);
				// currentSquare.setOnPath(true);
				// counter++;
				currentSquare = currentSquare.getParent();

			}
		} catch (Exception e) {
			System.out.println("CAUGHT EXCEPTION");
			System.out.println("currentSquare " + currentSquare);
		}
		// System.out.println(counter);

	}

	private void moveToGetToXY(XYCoordinate targetXY) {
		// choose a direction
		// (targetXY is adjacent to head)
		XYCoordinate headXY = head.getXY();
		if (headXY.getX() < targetXY.getX()) {
			head.setDir(Direction.RIGHT);
		} else if (headXY.getX() > targetXY.getX()) {
			head.setDir(Direction.LEFT);
		} else if (headXY.getY() < targetXY.getY()) {
			head.setDir(Direction.DOWN);
		} else if (headXY.getY() > targetXY.getY()) {
			head.setDir(Direction.UP);
		}
		super.move();
		if (!head.getXY().equals(targetXY)) {
			System.out.println("ERROR -- why didn't I reach target XY?");
		}
	}

	private void calculateFGH(Square square, Square target, Square currentSquare) {
		// G= number of squares already traveled to get to this square, plus 1
		// H = the number of squares that have to be traveled to get to target
		// from this square
		// F=G+H
		square.setG(currentSquare.getG() + 1);
		XYCoordinate squareXY = square.getXy();
		XYCoordinate targetXY = target.getXy();
		int numHorizontal = ((squareXY.getX()) - (targetXY.getX()) / Piece.SIZE);
		if (numHorizontal < 0) {
			numHorizontal = -numHorizontal;
		}
		int numVertical = ((squareXY.getY()) - (targetXY.getY()) / Piece.SIZE);
		if (numVertical < 0) {
			numVertical = -numVertical;
		}
		square.setH(numHorizontal + numVertical);
		square.setF(square.getG() + square.getH());

	}

	private Square findLowestFOnOpenList(HashMap<XYCoordinate, Square> openList) {
		int lowestF = 0;
		Square lowestFSquare = null;
		int counter = 0;
		for (XYCoordinate key : openList.keySet()) {
			if (counter == 0) {
				lowestFSquare = openList.get(key);
				lowestF = lowestFSquare.getF();
				counter++;
			} else {
				Square s = openList.get(key);
				int f = s.getF();
				if (f < lowestF) {
					lowestF = f;
					lowestFSquare = s;
				}
			}

		}
		return lowestFSquare;
	}

	private ArrayList<Square> getAdjSquares(Square currentSquare,
			HashMap<XYCoordinate, Square> squaresMap) {
		ArrayList<Square> adjSquares = new ArrayList<Square>();
		XYCoordinate xy = currentSquare.getXy();
		int x = xy.getX(), y = xy.getY();
		int size = Piece.SIZE;
		XYCoordinate[] lookFors = new XYCoordinate[4];
		lookFors[0] = new XYCoordinate(x + size, y);
		lookFors[1] = new XYCoordinate(x - size, y);
		lookFors[2] = new XYCoordinate(x, y + size);
		lookFors[3] = new XYCoordinate(x, y - size);
		for (XYCoordinate lookFor : lookFors) {
			Square get = squaresMap.get(lookFor);
			if (get != null) {
				SquareContents content = get.getContent();
				if (content.equals(SquareContents.EMPTY)
						|| content.equals(SquareContents.FOOD)) {
					adjSquares.add(get);
				}
			}
		}
		return adjSquares;
	}

	private void clearSquareSettings(Square s) {
		s.setParent(null);
		s.setF(0);
		s.setG(0);
		s.setH(0);
		// s.setMyPathColor(Color.BLACK);
	}
}

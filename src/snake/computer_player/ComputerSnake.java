package snake.computer_player;

import java.awt.Color;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;
import java.util.Set;
import java.util.Stack;

import snake.Direction;

public class ComputerSnake extends SnakeBody {

	private ArrayList<Piece> obstacles;
	private BodyPiece food;
	private HashMap<Direction, Direction> dirsOpps;
	private Random r;
	private Stack<Square> path;

	public ComputerSnake(BodyPiece head, int initialLength,
			ArrayList<Piece> obstacles, BodyPiece food, Board board) {
		super(head, initialLength, obstacles, board);
		head.setColor(Color.RED);
		this.food = food;
		path = new Stack<Square>();
		// r = new Random();
		// dirsOpps = new HashMap<Direction, Direction>();
		// dirsOpps.put(Direction.UP, Direction.DOWN);
		// dirsOpps.put(Direction.DOWN, Direction.UP);
		// dirsOpps.put(Direction.LEFT, Direction.RIGHT);
		// dirsOpps.put(Direction.RIGHT, Direction.LEFT);

	}

	public ComputerSnake(BodyPiece head, int initialLength,
			ArrayList<Piece> obstacles, Board board) {
		this(head, initialLength, obstacles, null, board);

	}

	public void move() {
		if (path.isEmpty()) {
			System.out.println("CALLING find path");
			findPath();
		}
		moveToGetToXY(path.pop().getXy());

	}

	/*
	 * public void move() {
	 * if (food == null) {
	 * System.out.println("food is null");
	 * }
	 * if (head == null) {
	 * System.out.println("head is null");
	 * }
	 * 
	 * Direction currdir = head.getDir();
	 * XYCoordinate foodXY = food.getXY(), headXY = head.getXY();
	 * int foodX = foodXY.getX(), foodY = foodXY.getY(), headX = headXY.getX(),
	 * headY = headXY
	 * .getY();
	 * if (!isThisDirectionGood(foodX, foodY, headX, headY)) {
	 * 
	 * if (foodX > headX && currdir != Direction.LEFT) {
	 * head.setDir(Direction.RIGHT);
	 * } else if (foodX < headX && head.getDir() != Direction.RIGHT) {
	 * head.setDir(Direction.LEFT);
	 * } else if (foodY < headY && currdir != Direction.DOWN) {
	 * head.setDir(Direction.UP);
	 * } else if (foodY > headY && currdir != Direction.UP) {
	 * head.setDir(Direction.DOWN);
	 * }
	 * }
	 * 
	 * head.move(0);
	 * Direction newdir = head.getDir();
	 * // if collision then undo
	 * 
	 * while (super.detectCollision()) {
	 * head.moveBackward();
	 * // then move different direction
	 * 
	 * Direction[] dirArray = Direction.values();
	 * int index = r.nextInt(4);
	 * Direction d = dirArray[index];
	 * // if d is not new dir (that resulted in collision) or opp of
	 * // current dir
	 * if (!d.equals(newdir) && !d.equals(dirsOpps.get(currdir))) {
	 * System.out.println("newdir " + newdir + " currDir: " + currdir);
	 * head.setDir(d);
	 * System.out.println("changing dir to " + d.toString());
	 * }
	 * 
	 * head.move();
	 * }
	 * 
	 * }
	 */

	/*
	 * public boolean isThisDirectionGood(int foodX, int foodY, int headX,
	 * int headY) {
	 * Direction currdir = head.getDir();
	 * switch (currdir) {
	 * case UP:
	 * return (foodY < headY);
	 * case DOWN:
	 * return (foodY > headY);
	 * case LEFT:
	 * return (foodX < headX);
	 * case RIGHT:
	 * return (foodX > headX);
	 * }
	 * return (Boolean) null;
	 * }
	 */

	public void setFood(BodyPiece food) {
		this.food = food;

	}

	public void undoMove() {
		head.moveBackward(0);

	}

	public void findPath() {
		System.out.println("FINDING PATH");
		boolean finishedPath = false;
		HashMap<XYCoordinate, Square> squaresMap = board.getSquaresMap();
		HashMap<XYCoordinate, Square> openList = new HashMap<XYCoordinate, Square>();
		HashMap<XYCoordinate, Square> closedList = new HashMap<XYCoordinate, Square>();
		Square start = squaresMap.get(head.getXY()); // head square
		Square target = squaresMap.get(food.getXY()); // food square
		openList.put(start.getXy(), start);
		Square currentSquare = start;
		while (!openList.isEmpty() && !finishedPath) {
			// look for lowest F cost on open list
			currentSquare = findLowestFOnOpenList(openList);
			System.out.println("current square: "
					+ currentSquare.getXy().toString());
			if (currentSquare.equals(target)) {
				System.out.println("DONE");
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
					if (adj.getContent().equals(SquareContents.EMPTY)
							|| adj.getContent().equals(SquareContents.FOOD)) {
						// If it isn’t on the open list, add it to the open
						// list.
						// Make the current square the parent of this square.
						// Record
						// the F, G, and H costs of the square.
						if (!openList.containsKey(adj.getXy())
								&& !closedList.containsKey(adj.getXy())) {
							System.out
									.println("key not in Open List, adding it");
							openList.put(adj.getXy(), adj);
							adj.setParent(currentSquare);
							calculateFGH(adj, target, currentSquare);
						} else {
							System.out.println("key is already in Open List");
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
				System.out.println("Finished for ");
				System.out.println(openList.size());
			}
		}
		System.out.println("Finished while ");
		// either found path, or there is no good path
		currentSquare = target;
		if (currentSquare == null) {
			System.out.println("ERROR: current square is null");
		}
		if (start == null) {
			System.out.println("ERROR: start is null");
		}
		while (!currentSquare.equals(start)) {
			path.push(currentSquare);
			currentSquare = currentSquare.getParent();
		}

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
		System.out.println("Head XY is  " + headXY + " and target XY is "
				+ targetXY + "  Chose direction: " + head.getDir());
		head.move();
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
		System.out.println("choosing lowest F in Open List");
		int lowestF = 0;
		Square lowestFSquare = null;
		int counter = 0;
		for (XYCoordinate key : openList.keySet()) {
			System.out.println(key.toString() + "   ");
			if (counter == 0) {
				lowestFSquare = openList.get(key);
				lowestF = lowestFSquare.getF();

			} else {
				counter++;
				Square s = openList.get(key);
				int f = s.getF();
				if (f < lowestF) {
					lowestF = f;
					lowestFSquare = s;
				}
			}

		}
		System.out.println("end choose   " + lowestFSquare.getXy().toString());
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
				adjSquares.add(get);
			}
		}
		// lookForXY.setX(x + size);
		// adjSquares.add(squaresMap.get(lookForXY));
		// lookForXY.setX(x - size);
		// adjSquares.add(squaresMap.get(lookForXY));
		// lookForXY.setX(x);
		// lookForXY.setY(y + size);
		// adjSquares.add(squaresMap.get(lookForXY));
		// lookForXY.setY(y - size);
		// adjSquares.add(squaresMap.get(lookForXY));
		return adjSquares;
	}
}

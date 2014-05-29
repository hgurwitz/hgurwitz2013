package jezzball;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;
import java.util.Stack;

import javax.swing.JComponent;

//TESTING GIT
//AND AGAIN
public class Board extends JComponent {

	private HashMap<XYCoordinate, Square> squaresMap;
	protected int sidelength = JezzView.SIDELENGTH;

	public Board() {
		squaresMap = new HashMap<XYCoordinate, Square>();
		int x = 0, y = 0;
		while (x < sidelength) {
			while (y < sidelength) {
				XYCoordinate xy = new XYCoordinate(x, y);
				System.out.print(xy);
				SquareContents contents = SquareContents.EMPTY;
				int max = sidelength - Square.SIZE;
				if (x == 0 || y == 0) {// || x == max || y == max) {
					contents = SquareContents.WALL;
				}
				Square s = new Square(xy, contents);
				System.out.print(contents);
				squaresMap.put(xy, s);
				y += Square.SIZE;
			}
			System.out.println();
			y = 0;
			x += Square.SIZE;
		}
	}

	public HashMap<XYCoordinate, Square> getSquaresMap() {
		return squaresMap;
	}

	public void setEmpty(XYCoordinate oldTail) {
		squaresMap.get(oldTail).setContent(SquareContents.EMPTY);
	}

	public SquareContents getContentsOfASquare(int x, int y) {
		return getContentsOfASquare(new XYCoordinate(x, y));
	}

	public SquareContents getContentsOfASquare(XYCoordinate xy) {
		return squaresMap.get(xy).getContent();
	}

	public SquareContents mouseClicked(XYCoordinate clickedSquare) {
		Set<XYCoordinate> keyset = squaresMap.keySet();
		for (XYCoordinate keyXY : keyset) {
			Square s = squaresMap.get(keyXY);
			s.setMyTrailParent(null);
			s.setColor(null);
		}

		Square s = squaresMap.get(clickedSquare);
		if (s != null) {
			SquareContents oldContents = s.getContent();
			s.setContent(SquareContents.MOVING_WALL);
			// findPath(clickedSquare);
			return oldContents;
		}
		return null;
	}

	public void paint(Graphics g) {
		Set<XYCoordinate> keyset = squaresMap.keySet();

		for (XYCoordinate keyXY : keyset) {
			Square s = squaresMap.get(keyXY);
			s.paint(g);
		}

	}

	public boolean checkForSquare(XYCoordinate origXY) {
		findPath1(origXY);
		return false;
	}

	public boolean checkForSquare1(XYCoordinate origXY) {
		// if xy does not have 2 filled in on either side return false
		// recursion - check that this square has 2 filled in on either side.
		// add both of those to stack. keep going until find the original one
		int currX, currY, numTimesFoundOriginal = 0;
		XYCoordinate[] currAdjacents = new XYCoordinate[4];
		ArrayList<XYCoordinate> currAdjFilledXYs = new ArrayList<XYCoordinate>();
		Stack<Square> adjSquares = new Stack<Square>();
		HashMap<XYCoordinate, Square> checkedSquares = new HashMap<XYCoordinate, Square>();
		Square origSquare = squaresMap.get(origXY);
		origSquare.setMyTrailParent(origSquare); // temporary
		adjSquares.push(origSquare);
		checkedSquares.put(origXY, origSquare);
		XYCoordinate currXY;

		while (!adjSquares.isEmpty() && numTimesFoundOriginal < 3) {
			// pop the next one
			Square currSquare = adjSquares.pop();
			System.out.println("currSquare: " + currSquare);
			currXY = currSquare.getXy();
			currX = currXY.getX();
			currY = currXY.getY();
			currAdjFilledXYs.clear();
			currAdjacents[0] = new XYCoordinate(currX - Square.SIZE, currY);
			currAdjacents[1] = new XYCoordinate(currX, currY - Square.SIZE);
			currAdjacents[2] = new XYCoordinate(currX + Square.SIZE, currY);
			currAdjacents[3] = new XYCoordinate(currX, currY + Square.SIZE);
			for (XYCoordinate currAdjXY : currAdjacents) {
				Square currAdjSquare = squaresMap.get(currAdjXY);
				if (currAdjSquare != null) {
					SquareContents contents = currAdjSquare.getContent();
					if (contents.equals(SquareContents.WALL)
							|| contents.equals(SquareContents.MOVING_WALL)) {

						currAdjFilledXYs.add(currAdjXY);
					}
				}
			}
			if (currAdjFilledXYs.size() >= 2) {
				for (XYCoordinate currAdjFilledXY : currAdjFilledXYs) {
					Square currAdjSquare = squaresMap.get(currAdjFilledXY);
					if (currAdjSquare != null
							&& currAdjSquare.getMyTrailParent() == null) {
						System.out.println("setting this as trail parent: "
								+ currSquare + " to square " + currAdjSquare);
						currAdjSquare.setMyTrailParent(currSquare);
						if (!checkedSquares.containsKey(currAdjFilledXY)) {
							currAdjSquare.setColor(Color.MAGENTA);
							checkedSquares.put(currAdjFilledXY, currAdjSquare);
							adjSquares.push(currAdjSquare);

						}
					}

					if (currAdjFilledXY.equals(origXY.getX(), origXY.getY())) {
						numTimesFoundOriginal++;
						if (numTimesFoundOriginal == 3) {
							if (currSquare.getMyTrailParent() == null) {
								System.out.println("STOPPING HERE------");
								return false;
							} else {
								origSquare.setMyTrailParent(currSquare);
							}
						}

						System.out.println("numTimesFoundOriginal "
								+ numTimesFoundOriginal);
					}
				}
			}

		}
		if (numTimesFoundOriginal >= 3) {
			System.out.println("FOUND A SQUARE------");
			int mySquareX = origXY.getX(), mySquareY = origXY.getY();
			Square myTrailParent = origSquare.getMyTrailParent();
			System.out.println("mySquare " + origSquare);
			System.out.println("mytrailparent " + myTrailParent);
			int counter = 0;
			while (myTrailParent != null && !(myTrailParent.equals(origSquare))) {
				// && !myTrailParent.getXy().equals(mySquareX, mySquareY)) {
				// && counter++ < 50) {
				myTrailParent.setColor(Color.GREEN);
				myTrailParent = myTrailParent.getMyTrailParent();
				System.out.println("mytrailparent " + myTrailParent);
			}
			return true;
		}

		return false;
	}

	private ArrayList<Square> getAdjSquares(Square currentSquare) {
		ArrayList<Square> adjSquares = new ArrayList<Square>();
		XYCoordinate xy = currentSquare.getXy();
		int x = xy.getX(), y = xy.getY();
		int size = Square.SIZE;
		XYCoordinate[] lookFors = new XYCoordinate[4];
		lookFors[0] = new XYCoordinate(x + size, y);
		lookFors[1] = new XYCoordinate(x - size, y);
		lookFors[2] = new XYCoordinate(x, y + size);
		lookFors[3] = new XYCoordinate(x, y - size);
		for (XYCoordinate lookFor : lookFors) {
			Square get = squaresMap.get(lookFor);
			if (get != null) {
				SquareContents content = get.getContent();
				if (content.equals(SquareContents.WALL)
						|| content.equals(SquareContents.MOVING_WALL)) {
					adjSquares.add(get);
				}
			}
		}
		return adjSquares;
	}

	private Square getFirstFromKeySet(Set<XYCoordinate> keyset) {
		for (XYCoordinate xy : keyset) {
			Square s = squaresMap.get(xy);
			return s;
		}
		return null;
	}

	public void findPath1(XYCoordinate origXY) {

		Stack<Square> path = new Stack<Square>();
		boolean finishedPath = false;
		HashMap<XYCoordinate, Square> openList = new HashMap<XYCoordinate, Square>();
		HashMap<XYCoordinate, Square> closedList = new HashMap<XYCoordinate, Square>();
		Square origSquare = squaresMap.get(origXY); // my square
		openList.put(origXY, origSquare);
		Square currentSquare = origSquare;
		System.out.println("START: " + origSquare);
		int counter = 0;
		int numTimesFound = 0;
		while (!openList.isEmpty() && !finishedPath) {
			currentSquare = getFirstFromKeySet(openList.keySet());
			if (currentSquare.equals(origSquare)) {
				numTimesFound++;
				if (numTimesFound >= 3 && counter > 0) {
					System.out.println("DONE-----------------------------"
							+ counter);
					finishedPath = true;
				}

			} else {
				counter++;
				// switch it to closed list
				XYCoordinate currentSquareXY = currentSquare.getXy();
				openList.remove(currentSquareXY);
				closedList.put(currentSquareXY, currentSquare);
				// for each of the 4 adjacent squares
				ArrayList<Square> adjSquares = getAdjSquares(currentSquare);
				for (Square adj : adjSquares) {
					/*
					 * If it isn’t on the open list, add it to the open list.
					 * Make the current square the parent of this square.
					 */
					XYCoordinate adjXY = adj.getXy();
					if ((!openList.containsKey(adjXY) && !closedList
							.containsKey(adjXY)) || adjXY.equals(origXY)) {
						System.out.println("adj = " + adj + " adj's parent = "
								+ currentSquare);
						if (!currentSquare.equals(origSquare)) {
							adj.setMyTrailParent(currentSquare);
						}
						openList.put(adjXY, adj);
					}

				}
			}
		}
		// either found path, or no walkable path exists
		currentSquare = origSquare;

		try {
			counter = 0;
			System.out.println("------------ " + counter);
			while (counter < 50 && !(currentSquare == null)) {

				System.out.println("currentSquare " + currentSquare);
				currentSquare.setColor(Color.GREEN);
				path.push(currentSquare);
				// currentSquare.setOnPath(true);
				// counter++;

				currentSquare = currentSquare.getMyTrailParent();
				System.out.println("nextSquare " + currentSquare);
				counter++;

			}
			System.out.println("------------ " + counter);
		} catch (Exception e) {
			System.out.println("CAUGHT EXCEPTION");
			System.out.println("currentSquare " + currentSquare);
		}

	}

	public boolean findPath(XYCoordinate origXY) {

		Stack<Square> path = new Stack<Square>();
		boolean finishedPath = false;
		HashMap<XYCoordinate, Square> openList = new HashMap<XYCoordinate, Square>();
		HashMap<XYCoordinate, Square> closedList = new HashMap<XYCoordinate, Square>();
		Square start;
		Square target;
		int numTimesFoundTarget = 0;

		finishedPath = false;
		openList.clear();
		closedList.clear();
		start = squaresMap.get(origXY); // head square
		target = squaresMap.get(origXY); // food square
		openList.put(start.getXy(), start);
		Square currentSquare = start;
		System.out.println("START: " + start);
		System.out.println("TARGET: " + target);
		while (!openList.isEmpty() && !finishedPath) {
			// look for lowest F cost on open list
			currentSquare = findLowestFOnOpenList(openList);
			if (currentSquare.equals(target)) {
				if (numTimesFoundTarget++ >= 3) {
					System.out
							.println("DONE-----------------------------------------");
					// DONE
					finishedPath = true;
				}

			} else {
				// switch it to closed list
				XYCoordinate currentSquareXY = currentSquare.getXy();
				openList.remove(currentSquareXY);
				closedList.put(currentSquareXY, currentSquare);
				// for each of the 4 adjacent squares
				ArrayList<Square> adjSquares = getAdjSquares(currentSquare);
				for (Square adj : adjSquares) {
					// if it's walkable
					SquareContents content = adj.getContent();
					if (content.equals(SquareContents.WALL)
							|| content.equals(SquareContents.MOVING_WALL)) {
						/*
						 * If it isn’t on the open list, add it to the open
						 * list. Make the current square the parent of this
						 * square. Record the F, G, and H costs of the square.
						 */
						if (!openList.containsKey(adj.getXy())
								&& !closedList.containsKey(adj.getXy())) {
							adj.setMyTrailParent(currentSquare);
							calculateFGH(adj, target, currentSquare);
							openList.put(adj.getXy(), adj);
						} else {
							/*
							 * If it is on the open list already, check to see
							 * if this path to that square is better, using G
							 * cost as the measure. A lower G cost means that
							 * this is a better path. If so, change the parent
							 * of the square to the current square, and
							 * recalculate the G and F scores of the square.
							 */
							if (adj.getG() > (currentSquare.getG() + 1)) {
								adj.setMyTrailParent(currentSquare);
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
		return finishedPath;

	}

	private void calculateFGH(Square square, Square target, Square currentSquare) {
		/*
		 * G= number of squares already traveled to get to this square, plus 1 H
		 * = the number of squares that have to be traveled to get to target
		 * from this square F = G + H
		 */
		square.setG(currentSquare.getG() + 1);
		XYCoordinate squareXY = square.getXy();
		XYCoordinate targetXY = target.getXy();
		int numHorizontal = ((squareXY.getX()) - (targetXY.getX())
				/ Square.SIZE);
		if (numHorizontal < 0) {
			numHorizontal = -numHorizontal;
		}
		int numVertical = ((squareXY.getY()) - (targetXY.getY()) / Square.SIZE);
		if (numVertical < 0) {
			numVertical = -numVertical;
		}
		square.setH(numHorizontal + numVertical);
		square.setF(square.getG() + square.getH());

	}

	private Square findLowestFOnOpenList(HashMap<XYCoordinate, Square> openList) {
		/*
		 * It is possible for 2 Squares to have equal Fs. The method should take
		 * this into account and decide which one is better
		 */

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

}

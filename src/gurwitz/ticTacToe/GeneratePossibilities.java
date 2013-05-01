package gurwitz.ticTacToe;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;

//how many unique boards
//how many ending boards
//given a board state, figure out best move (based on figuring out next possible moves
//and assigning it a 0/-1/+1 score
//given a board, will x eventually win? or o? or is it a tie?

public class GeneratePossibilities {

	private int numCompletedSquares;
	private char currTurn;
	private char xTurn;
	private char oTurn;
	private LinkedList<Board> boardQueue;
	private HashMap<ArrayList<Character>, Board> uniqueEndings;
	private HashMap<ArrayList<Character>, Board> uniqueBoards;
	int x = 0;
	int numGames;
	private int numUnique;
	private int numUniqueBoards;
	private int numNotUnique;

	public GeneratePossibilities() {
		xTurn = 'X';
		oTurn = 'O';
		boardQueue = new LinkedList<Board>();
		numCompletedSquares = 0;
		uniqueEndings = new HashMap<ArrayList<Character>, Board>();
		uniqueBoards = new HashMap<ArrayList<Character>, Board>();
		numUnique = 0;
		numUniqueBoards = 0;
		numNotUnique = 0;
		generate();

	}

	public static void main(String[] args) {
		new GeneratePossibilities();
	}

	public void generate() {
		currTurn = xTurn;
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				Board board = new Board();
				// System.out.println(i + " " + j);
				board.fillASquare(i, j);
				boardQueue.add(board);

			}
		}
		nextTurn();
		makeMoves();

	}

	public void nextTurn() {
		if (currTurn == xTurn) {
			currTurn = oTurn;
		} else {
			currTurn = xTurn;
		}
	}

	public void makeMoves() {

		Iterator<Board> queueIter = boardQueue.iterator();
		while (queueIter.hasNext()) {
			System.out.println("size " + boardQueue.size());
			Board nextBoard = boardQueue.pop();
			makeAllPossibleMoves(nextBoard);

			// System.out.println("size " + boardQueue.size());
		}
		System.out.println("Total completed boards: " + numCompletedSquares);
		System.out.println("Total unique boards: " + numUniqueBoards);
		System.out.println("Total unique completed boards: " + numUnique);
		System.out
				.println("Total non-unique completed boards: " + numNotUnique);

	}

	public void makeAllPossibleMoves(Board b) {
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				Board copy = b.getCopy();
				ArrayList<Character> copyBoardList = copy.getArrayList();
				if (!uniqueBoards.containsKey(copyBoardList)) {
					uniqueBoards.put(copyBoardList, copy);
					numUniqueBoards++;
				} else {
					// numNotUnique++;
				}
				if (copy.fillASquare(i, j)) {
					if (copy.checkForWin() || copy.isFull()) {
						ArrayList<Character> boardList = copy.getArrayList();
						if (!uniqueEndings.containsKey(boardList)) {
							uniqueEndings.put(boardList, copy);
							numUnique++;
						} else {
							numNotUnique++;
						}

						System.out.println(copy);// + "ended");
						numCompletedSquares++;
					} else {
						boardQueue.add(copy);
						nextTurn();
					}

					// Total unique boards: 4519
					// Total unique completed boards: 958

				}

			}
		}
	}

}
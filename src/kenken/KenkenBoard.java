package kenken;

import java.util.Random;

public class KenkenBoard {

	private int[][] board;

	private final int size;
	private Random rand;

	public KenkenBoard(int size) {
		this.size = size;
		board = new int[size][size];
		rand = new Random();
		boolean exThrown = false;
		do {
			try {
				createBoard();
			} catch (Exception e) {
				exThrown = true;
			}
		} while (exThrown);

	}

	public KenkenBoard(int[][] boardNums) {
		this.board = boardNums;
		size = boardNums[0].length;
		rand = new Random();
	}

	public static void main(String[] args) {
		System.out.println(new KenkenBoard(4).toString());
	}

	public int[][] getBoardNums() {
		return board;
	}

	public void setBoardNums(int[][] board) {
		this.board = board;
	}

	public void createBoard() throws Exception {
		// initializeArrays();
		// System.out.println("creating again");
		for (int col = 0; col < size; col++) {
			for (int row = 0; row < size; row++) {
				// System.out.println("row=" + row + " col=" + col);
				int random = getRandomNum(row, col);
				if (random == 0) {
					// System.out.println("Cannot find valid number "
					// + "rowIndex is " + row + " colindex is " + col);
					// System.out.println(toString());
					// if (col > 1) {
					// System.out.println("Going back one col");
					// col -= 2;
					// } else if (row > 1) {
					// System.out.println("Going back one row");
					// row -= 2;
					// }

					// start again
					throw new Exception("Unable to create valid board");
				}
				board[row][col] = random;
				System.out.println("rowIndex is " + row + " colindex is " + col
						+ " number is " + board[row][col]);
			}
		}
	}

	public int getSize() {
		return size;
	}

	public int getRandomNum(int rowIndex, int colIndex) {
		int returnVal = 0;
		int random = 0;
		for (int i = 0; i < size; i++) {
			if (random == 0) {
				random = rand.nextInt(size) + 1;
			}
			if (!containsNumberRow(rowIndex, random)
					&& !containsNumberCol(colIndex, random)) {
				return random;
			} else {
				random = (random + 1) % 5;
			}
		}
		return returnVal;
	}

	public boolean containsNumberRow(int rowIndex, int numToCheck) {
		int[] array = board[rowIndex];
		for (int i = 0; i < size; i++) {
			if (array[i] == numToCheck) {
				return true;
			}
		}
		return false;
	}

	public boolean containsNumberCol(int colIndex, int numToCheck) {
		for (int i = 0; i < size; i++) {
			if (board[i][colIndex] == numToCheck) {
				return true;
			}
		}
		return false;
	}

	public String toString() {
		StringBuilder s = new StringBuilder("");
		for (int i = 0; i < size; i++) {

			for (int k = 0; k < (size * 4); k++) {
				s.append("-");
			}
			s.append("\n");
			s.append("| ");
			for (int j = 0; j < size; j++) {
				s.append(String.valueOf(board[i][j]) + " | ");
			}
			s.append("\n");

		}
		for (int k = 0; k < (size * 4); k++) {
			s.append("-");
		}
		return s.toString();

	}

}

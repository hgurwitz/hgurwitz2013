package gurwitz.ticTacToe;

import java.util.ArrayList;

public class Board {

	private char[][] board;
	private char xTurn;
	private char oTurn;
	private char currTurn;
	private char winner;
	protected Move move;

	private int[][] possibleMoves;

	public Board() {
		board = new char[3][3];
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				board[i][j] = ' ';
			}
		}
		xTurn = 'X';
		oTurn = 'O';
		currTurn = xTurn;
		winner = ' ';

	}

	public Board(ArrayList<Character> list) throws Exception {
		board = new char[3][3];
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				board[i][j] = ' ';
			}
		}
		xTurn = 'X';
		oTurn = 'O';
		currTurn = xTurn;
		winner = ' ';
		if (list.size() != 9) {
			throw new Exception("Invalid number of characters");
		} else {
			for (int i = 0; i < 9; i++) {
				// System.out.println(i + " " + i / 3 + " " + i % 3);
				if (!(fillASquare(i / 3, i % 3, list.get(i)))) {
					throw new Exception("Invalid move");
				}

			}
		}
		checkForWin();

	}

	public Board(ArrayList<Character> list, Move m) throws Exception {
		this(list);
		this.move = m;
	}

	public ArrayList<Character> getArrayList() {
		ArrayList<Character> boardList = new ArrayList<Character>();
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				boardList.add(board[i][j]);
			}
		}
		return boardList;
	}

	public void nextTurn() {
		if (currTurn == xTurn) {
			currTurn = oTurn;
		} else {
			currTurn = xTurn;
		}
	}

	public int[][] getPossibleMoves() {
		return possibleMoves;
	}

	public char[][] getArray() {
		return board;
	}

	public boolean fillASquare(int row, int column) {
		if (row < 0 || row > 2 || column < 0 || column > 2
				|| (board[row][column] == 'X') || (board[row][column] == 'O')) {
			return false;
		}

		board[row][column] = currTurn;
		nextTurn();
		return true;

	}

	public boolean fillASquare(int row, int column, char player) {
		if (row < 0 || row > 2 || column < 0 || column > 2
				|| (board[row][column] == 'X') || (board[row][column] == 'O')) {
			return false;
		}

		board[row][column] = player;
		return true;

	}

	public char getCurrTurn() {
		return currTurn;
	}

	public void setCurrTurn(char c) {
		this.currTurn = c;
	}

	public boolean checkForWin() {
		// check every line to see if it's a win
		// first, the rows
		for (char[] row : board) {
			if (checkForLine(row[0], row[1], row[2]))
				return true;
		}
		// now the columns
		for (int i = 0; i < 3; i++) {
			char[] column = new char[3];
			for (int j = 0; j < 3; j++) {
				column[j] = board[j][i];
			}
			if (checkForLine(column[0], column[1], column[2]))
				return true;
		}
		// now the diagonals
		if (checkForLine(board[0][0], board[1][1], board[2][2]))
			return true;
		if (checkForLine(board[0][2], board[1][1], board[2][0]))
			return true;

		return false;

	}

	private boolean checkForLine(char a, char b, char c) {
		if (!(a == ' ' || b == ' ' || c == ' ' || a != b || a != c)) {
			winner = a;
			return true;
		} else
			return false;
	}

	public boolean isFull() {
		for (char[] row : board) {
			for (char c : row) {
				if (c == ' ') {
					return false;
				}
			}
		}
		return true;

	}

	public String toString() {
		String str = "";
		for (char[] row : board) {
			for (char c : row) {
				str += c + "|";
			}
			str += "\n";
		}
		return str;
	}

	public char getCharAt(int row, int column) {
		return board[row][column];
	}

	public Board getCopy() {
		Board b = new Board();

		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				b.fillASquare(i, j, board[i][j]);
			}
		}
		b.setCurrTurn(currTurn);
		b.checkForWin();
		return b;

	}

	public Board getCopyWithMove() {
		Board b = getCopy();
		b.setMove(move);
		return b;
	}

	public char getWinner() {
		return winner;
	}

	public Move getMove() {
		return move;
	}

	public void setMove(Move move) {
		this.move = move;
	}

}

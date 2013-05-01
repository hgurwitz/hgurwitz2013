package gurwitz.ticTacToe;

public class Move implements Comparable<Move> {
	private Board b;
	private int row;
	private int col;
	private char player;
	private int numWinsWithThisMove;
	private int levelPastInitialMove;

	public int getLevelPastInitialMove() {
		return levelPastInitialMove;
	}

	public void setLevelPastInitialMove(int levelPastInitialMove) {
		this.levelPastInitialMove = levelPastInitialMove;
	}

	public void incrementLevelPastInitialMove() {
		this.levelPastInitialMove++;
	}

	public Board getB() {
		return b;
	}

	public int getRow() {
		return row;
	}

	public int getCol() {
		return col;
	}

	public char getPlayer() {
		return player;
	}

	public int getNumWins() {
		return numWinsWithThisMove;
	}

	public void setNumWins(int x) {
		numWinsWithThisMove = x;
	}

	public void incrementNumMoves() {
		numWinsWithThisMove++;
	}

	public Move(Board b, int row, int col, char player) {
		super();
		this.b = b;
		this.row = row;
		this.col = col;
		this.player = player;
		numWinsWithThisMove = 0;
	}

	public boolean equals(Move o) {
		return (player == o.getPlayer() && col == o.getCol() && row == o
				.getRow());
	}

	@Override
	public int compareTo(Move o) {
		if (player == o.getPlayer() && col == o.getCol() && row == o.getRow()) {
			return 0;
		} else {
			return -1;
		}

	}

	@Override
	public String toString() {
		return "Move [row=" + row + ", col=" + col + ", player=" + player + "]";
	}
}
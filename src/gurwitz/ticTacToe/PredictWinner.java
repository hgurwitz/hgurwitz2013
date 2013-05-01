package gurwitz.ticTacToe;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;
import java.util.Stack;

public class PredictWinner {

	private Board board;

	private Stack<Board> stack;
	private HashMap<ArrayList<Character>, BoardState> map;
	private ArrayList<Board> boardsToAdd;
	private int[] winners;
	private ArrayList<Move> moves;

	public PredictWinner(Board b) {
		this.board = b.getCopyWithMove();
		stack = new Stack<Board>();
		map = new HashMap<ArrayList<Character>, BoardState>();
		boardsToAdd = new ArrayList<Board>();
		winners = new int[3];
		moves = new ArrayList<Move>();

	}

	private void moveSetup(char player) {
		// all possible moves that can be made now
		int levelOfMove = 0;
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				Board boardCopy = board.getCopyWithMove();
				if (boardCopy.fillASquare(i, j, player)) {
					Move m = new Move(boardCopy, i, j, player);
					m.setLevelPastInitialMove(levelOfMove);
					moves.add(m);
					boardCopy.setMove(m);
					stack.push(boardCopy);
				}
			}
		}
	}

	private void findProbableWinnerAndBestMove() {
		moveSetup('X');

		// stack.push(board);
		while (stack.size() > 0) {
			winner();
		}

		Set<ArrayList<Character>> arraylists = map.keySet();
		Iterator<ArrayList<Character>> iter = arraylists.iterator();
		while (iter.hasNext()) {
			BoardState state = map.get(iter.next());
			winners[state.value.ordinal()]++;
			if (state.value == WinningPlayer.X) {
				state.m.incrementNumMoves();
			}

		}
		int maxWins = 0;
		WinningPlayer maxWinner = WinningPlayer.X;
		for (int i = 0; i < 3; i++) {
			int wins = winners[i];
			System.out.println("winners[" + i + "] is " + wins);
			if (wins > maxWins) {
				maxWins = wins;
				maxWinner = WinningPlayer.values()[i];
			}
		}
		System.out.println("WINNER IS " + maxWinner);
		for (Move m : moves) {
			System.out.println("move is " + m.toString()
					+ " number wins for that move is " + m.getNumWins());
		}

	}

	public void allMovesForBoard(Board b) {
		boardsToAdd.clear();
		if (!(b.checkForWin() || b.isFull())) {
			for (int i = 0; i < 3; i++) {
				for (int j = 0; j < 3; j++) {
					Board bCopy = b.getCopyWithMove();
					if (bCopy.fillASquare(i, j)) {
						// push onto stack
						// System.out.println("adding to boardsToAdd");
						bCopy.move.incrementLevelPastInitialMove();
						boardsToAdd.add(bCopy);
					}
				}
			}

		} else {
			ArrayList<Character> list = b.getArrayList();
			if (!map.containsKey(list)) {
				// System.out.println("adding to hashmap" + map.size());
				map.put(b.getArrayList(), new BoardState(b, b.getMove()));
			}
		}
		for (Board each : boardsToAdd) {
			stack.push(each);

		}

	}

	public void winner() {
		boardsToAdd.clear();
		Iterator<Board> iterator = stack.iterator();
		// while (iterator.hasNext()) {
		Board b = iterator.next();
		// System.out.println(b);
		iterator.remove();
		allMovesForBoard(b);
		// }
	}

	public static void main(String[] args) {
		ArrayList<Character> list = new ArrayList<Character>();

		list.add(' ');
		list.add('X');
		list.add('O');
		list.add('X');
		list.add(' ');
		list.add('O');
		list.add(' ');
		list.add('X');
		list.add(' ');
		// _ X O
		// X _ O
		// _ X _

		/*
		 * list.add('O'); list.add('O'); list.add('X'); list.add('X');
		 * list.add('O'); list.add(' '); list.add('X'); list.add(' ');
		 * list.add(' '); // O O X // X O // X
		 */

		/*
		 * list.add('X');
		 * list.add('O');
		 * list.add('X');
		 * list.add('X');
		 * list.add('O');
		 * list.add('O');
		 * list.add('O');
		 * list.add('X');
		 * list.add(' '); // X O X // X O O // O X
		 */

		// ALL BLANK DOESN'T WORK
		/*
		 * list.add(' '); list.add(' '); list.add(' '); list.add(' ');
		 * list.add(' '); list.add(' '); list.add(' '); list.add(' ');
		 * list.add(' ');
		 */

		Board b;
		try {
			b = new Board(list);
			PredictWinner p = new PredictWinner(b);
			p.findProbableWinnerAndBestMove();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private class BoardState {
		public Board b;
		public WinningPlayer value;
		public Move m;

		public BoardState(Board b, Move m) {
			this.b = b;
			this.m = m;
			if (b.getWinner() == 'X') {
				value = WinningPlayer.X;
			} else if (b.getWinner() == 'O') {
				// value = -1;
				value = WinningPlayer.O;
			} else if (b.isFull()) {
				value = WinningPlayer._;
			} else {

			}

		}
	}

	private enum WinningPlayer {
		X, O, _
	}

}

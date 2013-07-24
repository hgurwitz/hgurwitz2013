package kenken;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class GeneratePuzzle {

	private KenkenBoard board;
	private int[][] boardNums;
	// private int[][] toWhichArrayDoIBelong;
	private CageDetails[][] cages;
	private ArrayList<Integer> cageIds;
	private ArrayList<ArrayList<Integer>> arrays;
	private Random random;
	private int size;
	private int nextCageID;

	public GeneratePuzzle(int size) {
		this.size = size;
		nextCageID = 1;
		System.out.println("1");
		board = new KenkenBoard(size);
		System.out.println("2");
		boardNums = board.getBoardNums();
		System.out.println("3");
		random = new Random();
		System.out.println("4");
		arrays = new ArrayList<ArrayList<Integer>>();
		cageIds = new ArrayList<Integer>();
		System.out.println("5");
		System.out.println("6");
		cages = new CageDetails[size][size];
		initializeCages();
		System.out.println("finished constructing about to call createboard");
		createBoard();

	}

	public GeneratePuzzle(KenkenBoard ken) {
		this.board = ken;
		boardNums = board.getBoardNums();
		this.size = ken.getSize();
		nextCageID = 1;
		random = new Random();
		arrays = new ArrayList<ArrayList<Integer>>();
		cageIds = new ArrayList<Integer>();
		cages = new CageDetails[size][size];
		initializeCages();
		System.out.println("finished constructing about to call createboard");
		createBoard();

	}

	private void initializeCages() {
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				cages[i][j] = new CageDetails(0, ' ', 0);
			}
		}
	}

	public void createBoard() {
		splitUpBoard();
		System.out.println("in createBoard after splitupboard");
		operations();
		System.out.println("in createBoard after operations");
	}

	public KenkenBoard getBoard() {
		return board;
	}

	public static void main(String[] args) {
		int[][] board = new int[3][3];
		board[0] = new int[] { 1, 2, 3 };
		board[1] = new int[] { 2, 3, 1 };
		board[2] = new int[] { 3, 1, 2 };
		KenkenBoard ken = new KenkenBoard(board);
		GeneratePuzzle g = new GeneratePuzzle(ken);

		// GeneratePuzzle g = new GeneratePuzzle(5);
		System.out.println(g.getBoard().toString());
		System.out.println(g.toString());
	}

	public String toString() {
		StringBuilder s = new StringBuilder("");

		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				CageDetails detail = cages[i][j];
				if (detail == null) {
					s.append(" 0");
					s.append(" |");
				} else {
					s.append(detail.getTotal());
					s.append(detail.getOperation());
					s.append("|");
				}

			}
			s.append("\n");

		}
		s.append("\n\n\n");
		s.append("TO WHICH ARRAY\n");
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				s.append(cages[i][j].getCageID() + " ");
			}
			s.append("\n");
		}
		// s.append("\n\n");
		// s.append("OPERATIONS\n");
		// for (int i = 0; i < size; i++) {
		// for (int j = 0; j < size; j++) {
		// s.append(operations[i][j] + " ");
		// }
		// s.append("\n");
		// }

		return s.toString();

	}

	private void splitUpBoard() {

		int randomSize = random.nextInt(4) + 1;
		int randomDirection = random.nextInt(2);
		// 0=horizontal
		// 1=vertical
		int id = nextCageID++;
		if (randomDirection == 0) {
			System.out.println("direction is horizontal, randomsize is "
					+ randomSize);
			for (int i = 0; i < (randomSize - 1); i++) {
				cages[0][i] = new CageDetails(0, ' ', id);
			}

		} else if (randomDirection == 1) {
			System.out.println("direction is vertical randomsize is "
					+ randomSize);
			for (int i = 0; i < (randomSize); i++) {
				cages[i][0] = new CageDetails(0, ' ', id);
			}
		}

	}

	private void operations() {
		int operation = random.nextInt(4);
		// 0=addition, 1=subtraction, 2=multiplication, 3=division
		for (Integer cageId : cageIds) {
			// get list of cage's numbers
			ArrayList<Integer> array = new ArrayList<Integer>();
			for (int i = 0; i < size; i++) {
				for (int j = 0; j < size; j++) {
					if (cages[i][j].getCageID() == cageId) {
						array.add(boardNums[i][j]);
					}
				}
			}

			if (array.size() != 2) {
				while (operation == 1 || operation == 3) {
					operation = random.nextInt(4);
				}
				if (operation == 3) {
					double totalDbl = array.get(0) / array.get(1);
					if (totalDbl < 0 || (totalDbl % 1) != 0) {
						totalDbl = array.get(1) / array.get(0);
						if (totalDbl < 0 || (totalDbl % 1) != 0) {
							operation = 1; // arbitrary choice
						}
					}
				}
			}
			int total = 0;
			switch (operation) {
			case 0:
				for (Integer i : array) {
					total += i;
				}
				break;
			case 1:
				total = Math.abs(array.get(0) - array.get(1));
				break;
			case 2:
				total = array.get(0);
				for (int i = 1; i < array.size(); i++) {
					total *= array.get(i);
				}
				break;
			case 3:
				double totalDbl = array.get(0) / array.get(1);
				if (totalDbl < 0 || (totalDbl % 1) != 0) {
					totalDbl = array.get(1) / array.get(0);
					if (totalDbl < 0 || (totalDbl % 1) != 0) {
						operation = 1; // arbitrary choice
						total = Math.abs(array.get(0) - array.get(1));
					} else {
						total = array.get(1) / array.get(0);
					}
				} else {
					total = array.get(0) / array.get(1);
				}
				break;
			} // end switch
			fillOperationTotals(cageId, array, total, operation);
		}
	}

	private void fillOperationTotals(int cageID, ArrayList<Integer> cageNums,
			int total, int operationCode) {
		char operation = ' ';
		switch (operationCode) {
		case 0:
			operation = '+';
			break;
		case 1:
			operation = '-';
			break;
		case 2:
			operation = '*';
			break;
		case 3:
			operation = '/';
			break;
		}
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				CageDetails cd = cages[i][j];
				if (cd.getCageID() == cageID) {
					cd.setTotal(total);
					cd.setOperation(operation);
				}
			}
		}
	}

	private class CageDetails {
		private int total;
		private char operation;
		private int cageID;

		public CageDetails(int total, char operation, int cageID) {
			this.total = total;
			this.operation = operation;
			this.cageID = cageID;
		}

		public int getCageID() {
			return cageID;
		}

		public void setCageID(int cageID) {
			this.cageID = cageID;
		}

		public int getTotal() {
			return total;
		}

		public void setTotal(int total) {
			this.total = total;
		}

		public void setOperation(char operation) {
			this.operation = operation;
		}

		public char getOperation() {
			return operation;
		}
	}

}

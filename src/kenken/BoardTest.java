package kenken;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class BoardTest {

	KenkenBoard k;
	int[][] board;
	int size;

	@Before
	public void setup() {
		size = 5;
		k = new KenkenBoard(size);
	}

	@Test
	public void testContainsNumber() {
		assertFalse(k.containsNumberRow(2, 2));
		assertFalse(k.containsNumberCol(2, 2));
		int[][] testBoard = new int[size][size];
		testBoard[2][2] = 2;
		k.setBoardNums(testBoard);
		assertTrue(k.containsNumberRow(2, 2));
		assertTrue(k.containsNumberCol(2, 2));
	}

	public void testGetRandomNum() {
		int[][] testBoard = new int[size][size];

		testBoard[2][0] = 4;
		testBoard[2][1] = 1;
		testBoard[2][3] = 2;
		testBoard[2][4] = 3;

		testBoard[0][2] = 1;
		testBoard[1][2] = 2;
		testBoard[3][2] = 3;
		testBoard[4][2] = 4;

		k.setBoardNums(testBoard);
		int rand = k.getRandomNum(2, 2);
		assertEquals(rand, 5);

	}

	public void testCreateBoard() {
		try {
			k.createBoard();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		int[][] board = k.getBoardNums();
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				assertFalse(board[i][j] == 0);
			}
		}
	}

}

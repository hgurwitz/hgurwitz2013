package gurwitz.ticTacToe;

import static org.junit.Assert.*;
import gurwitz.ticTacToe.Board;

import org.junit.Before;
import org.junit.Test;

public class BoardTest {

	Board aBoard;

	@Before
	public void setup() {
		aBoard = new Board();
	}

	@Test
	public void testFillASquare() {
		assertTrue(aBoard.fillASquare(1, 2, 'X'));
		char[][] array = aBoard.getArray();
		assertEquals('X', array[1][2]);
		assertFalse(aBoard.fillASquare(1, 2, 'O'));
		assertTrue(aBoard.fillASquare(2, 1, 'X'));
		array = aBoard.getArray();
		assertEquals('X', array[1][2]);
		assertFalse(aBoard.fillASquare(3, 2, 'X'));
	}

	@Test
	public void testCheckForWin() {
		assertFalse(aBoard.checkForWin());
		for (int i = 0; i <= 2; i++) {
			for (int j = 0; j <= 2; j++) {
				aBoard.fillASquare(i, j, 'X');
			}
		}
		assertTrue(aBoard.checkForWin());
	}

}

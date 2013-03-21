

import static org.junit.Assert.*;

import org.junit.Test;

import acm.MaxSum;

public class MaxSumTest {

	@Test
	public void test() {
		int[][] testArray = new int[4][4];
		int[] row1 = { 0, -2, -7, 0 };
		int[] row2 = { 9, 2, -6, 2 };
		int[] row3 = { -4, 1, -4, 1 };
		int[] row4 = { -1, 8, 0, -2 };
		testArray[0] = row1;
		testArray[1] = row2;
		testArray[2] = row3;
		testArray[3] = row4;

		MaxSum m = new MaxSum(testArray);
		m.findMaximalSubRectangle();
		int max = m.getMaxSum();
		assertEquals(15, max);
	}

}

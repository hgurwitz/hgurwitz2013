package acm;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class MaxSum {

	private int[][] array;
	private Scanner readFile;
	private int size;
	private int maxSum;
	private int[][] maxSumArray;

	public MaxSum(File file) throws Exception {
		new ArrayList<ArrayList<Integer>>();
		readFile = new Scanner(file);
		size = readFile.nextInt();
		array = new int[size][size];
		readFile.nextLine();
		for (int row = 0; row < size; row++) {
			for (int col = 0; col < size; col++) {
				if (readFile.hasNext()) {
					array[row][col] = readFile.nextInt();
				} else {
					throw new Exception("Too few numbers in file");
				}
			}
		}
		initializeMaxSums();

	}

	public MaxSum(int[][] array) {

		this.array = array;
		size = array.length;
		initializeMaxSums();

	}

	private void initializeMaxSums() {
		maxSum = array[0][0];
		maxSumArray = new int[1][1];
		maxSumArray[0][0] = maxSum;
	}

	public void findMaximalSubRectangle() {

		for (int row = 0; row < size; row++) {
			for (int col = 0; col < size; col++) {
				evaluateAllSubArrays(row, col);
			}

		}
	}

	public void evaluateAllSubArrays(int row, int col) {
		for (int width = row; width < size; width++) {
			for (int height = col; height < size; height++) {
				if (row == 0 && col == 0 && width == (size - 1)
						&& height == (size - 1)) {
					// this is the whole array, not a subarray, so do nothing
				} else {

					int[][] subArray = get2DSubarray(array, row, col, width,
							height);
					int arraySum = sumOfArray(subArray);
					if (arraySum > maxSum) {
						maxSum = arraySum;
						maxSumArray = subArray;
					}

					else if (arraySum == maxSum) {
						maxSumArray = subArray;
					}
				}

			}
		}
	}

	public int[][] get2DSubarray(int[][] origArray, int rowstart, int colstart,
			int rowend, int colend) {

		int[][] subArray = new int[(rowend - rowstart) + 1][(colend - colstart) + 1];
		int newArrayRow = 0;
		for (int origArrayRow = rowstart; origArrayRow < subArray.length
				+ rowstart; origArrayRow++) {
			subArray[newArrayRow++] = Arrays.copyOfRange(
					origArray[origArrayRow], colstart, (colend + 1));
		}
		return subArray;
	}

	public int getMaxSum() {
		return maxSum;
	}

	public int[][] getMaxSumArray() {
		return maxSumArray;
	}

	public int sumOfArray(int[][] arrayToSum) {
		int sum = 0;
		for (int i = 0; i < arrayToSum.length; i++) {
			for (int j = 0; j < arrayToSum[i].length; j++) {
				sum += arrayToSum[i][j];
			}
		}
		return sum;
	}

	public static void main(String[] args) {
		/*
		 * MaxSum m = null; try { m = new MaxSum(new File("./array.txt")); }
		 * catch (Exception e) { e.printStackTrace(); }
		 */

		Random r = new Random();
		int n = r.nextInt(10);
		System.out.println(n);
		int[][] array = new int[n][n];
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				array[i][j] = r.nextInt(254) - 127;
				System.out.printf("%6d", array[i][j]);
			}
			System.out.println();
		}

		System.out.println("--------------------------------------");
		MaxSum m = new MaxSum(array);

		m.findMaximalSubRectangle();

		System.out.println(m.getMaxSum());
		int[][] maxArray = m.getMaxSumArray();
		for (int i = 0; i < maxArray.length; i++) {
			for (int j = 0; j < maxArray[i].length; j++) {
				System.out.printf("%6d", maxArray[i][j]);
			}
			System.out.println();

		}

	}
}

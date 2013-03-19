package gurwitz.interviewQs;

import java.util.Random;

public class ArraySort {

	private int[] numbers;
	private int[] sortingArray;
	private Random r;

	public ArraySort() {
		numbers = new int[100];
		r = new Random();
		for (int i = 0; i < numbers.length; i++) {
			numbers[i] = r.nextInt(10000);
		}
	}

	public void sort() {
		sortingArray = new int[10000];
		for (int i : numbers) {
			sortingArray[i]++;
		}
		int numbersPlaceHolder = 0;
		for (int i = 0; i < sortingArray.length; i++) {
			if (sortingArray[i] != 0) {
				numbers[numbersPlaceHolder++] = i;
			}
		}

		for (int i : numbers) {
			System.out.print(i + " ");
		}

	}

	public static void main(String[] args) {
		ArraySort a = new ArraySort();
		a.sort();
	}

}

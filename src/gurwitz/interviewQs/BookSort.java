package gurwitz.interviewQs;

public class BookSort {

	private int[] sortingArray;
	private String s;
	private char[] charArray;

	public BookSort(String s) {
		sortingArray = new int[256];
		this.s = s;
		charArray = s.toCharArray();
	}

	public void sort() {
		for (char c : charArray) {
			sortingArray[c]++;
		}
		System.out.println();
		for (int i = 0; i < sortingArray.length; i++) {
			int x = sortingArray[i];
			if (x != 0)
				System.out.println((i) + " " + (char) i + " " + x);
		}
		// Character.valueOf((char)
	}

	public static void main(String[] args) {
		String s = "Hi, hello! How Are You?";
		BookSort b = new BookSort(s);
		System.out.println(s.length());
		b.sort();
	}

}

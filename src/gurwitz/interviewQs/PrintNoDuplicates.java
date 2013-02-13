package gurwitz.interviewQs;
import java.util.ArrayList;
import java.util.HashMap;

public class PrintNoDuplicates {

	private String[] strings;

	public PrintNoDuplicates(String[] strings) {

		this.strings = strings;
		this.print();
	}

	public void print() {

		HashMap<String, String> printedStrings = new HashMap<String, String>();
		for (String s : strings) {
			if (!printedStrings.containsKey(s)) {
				System.out.println(s);
				printedStrings.put(s, s);
			}
		}
	}

	public static void main(String[] args) {
		String[] strings = { "A", "B", "C", "A", "B" };
		new PrintNoDuplicates(strings);
	}
}

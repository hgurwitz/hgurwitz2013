package scrabble;

import java.io.FileNotFoundException;
import java.util.HashMap;

public class FindAnagrams {

	// find largest set of anagrams in the list

	public FindAnagrams() throws FileNotFoundException {
		Words w = new Words();
		HashMap<String, String> set = w.getWords();
		String string = set.toString();
		System.out.println(string);
		HashMap<String, String> aSet = new HashMap<String, String>();
	}

	public static void main(String[] args) {
		try {
			FindAnagrams f = new FindAnagrams();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void findAnagrams() {

	}

}

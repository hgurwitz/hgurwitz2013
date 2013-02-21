package scrabble;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;

public class Words {

	private HashMap<String, String> words;

	public Words() throws FileNotFoundException {

		words = new HashMap<String, String>();
		readFile();

	}

	private void readFile() throws FileNotFoundException {
		File f = new File("./wordlist.txt");
		Scanner readFile = new Scanner(f);
		while (readFile.hasNext()) {
			String word = readFile.next();
			words.put(word, word);
		}
	}

	public boolean contains(String s) {
		return words.containsKey(s);
	}

	public boolean isThisAnAnagram(String s, String anagram) {

		HashMap<Character, Integer> letters = new HashMap<Character, Integer>();
		for (int i = 0; i < s.length(); i++) {
			char letter = s.charAt(i);
			if (letters.containsKey(letter)) {
				int numTimes = letters.get(letter);
				letters.remove(letter);
				letters.put(letter, ++numTimes);
			} else {
				letters.put(anagram.charAt(i), 1);
			}
		}

		for (int i = 0; i < anagram.length(); i++) {
			char letter = anagram.charAt(i);
			if (!letters.containsKey(letter)) {
				return false;
			} else {
				int numTimes = letters.get(letter);
				if (numTimes == 1) {
					letters.remove(letter);
				} else {
					letters.put(letter, --numTimes);
				}
			}
		}

		return letters.isEmpty();
	}

	public boolean isThisAnAlphaAnagram(String s, String anagram) {
		char[] alpha1 = alphabetize(s);
		char[] alpha2 = alphabetize(anagram);
		return Arrays.equals(alpha1, alpha2);
	}

	public char[] alphabetize(String s) {
		char[] chars = s.toCharArray();
		Arrays.sort(chars);
		return chars;
	}

	public HashMap<String, String> getWords() {
		return words;
	}

}
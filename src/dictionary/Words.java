package dictionary;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Scanner;

public class Words {

	private HashMap<String, String> words;
	private File f;
	private Scanner readFile;

	public Words() throws FileNotFoundException {
		words = new HashMap<String, String>();
		f = new File("./wordlist.txt");
		readFile = new Scanner(f);
		readFileIntoHashMap();
		readFile.close();
	}

	private void readFileIntoHashMap() throws FileNotFoundException {
		while (readFile.hasNext()) {
			String word = readFile.next();
			words.put(word, word);
		}
	}

	public LinkedList<String> readFileIntoList() throws FileNotFoundException {
		readFile = new Scanner(f);
		LinkedList<String> wordList = new LinkedList<String>();
		while (readFile.hasNext()) {
			String word = readFile.next();
			wordList.add(word);
		}
		return wordList;
	}

	public ArrayList<LinkedList<String>> readFileIntoListsBySize()
			throws FileNotFoundException {
		readFile = new Scanner(f);
		ArrayList<LinkedList<String>> wordLists = new ArrayList<LinkedList<String>>();
		for (int i = 0; i < 21; i++) {
			wordLists.add(new LinkedList<String>());
		}
		while (readFile.hasNext()) {
			String word = readFile.next();
			int length = word.length();
			wordLists.get(length - 1).add(word);
		}
		return wordLists;
	}

	public boolean contains(String s) {
		return words.containsKey(s);
	}

	public boolean isAnagram(String s, String anagram) {

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

	public boolean isAlphaAnagram(String s, String anagram) {
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
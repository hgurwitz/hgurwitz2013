package gurwitz.dictionary;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Set;

public class FindAnagrams {

	// find largest set of anagrams in the list
	private HashMap<String, String> words;
	private Dictionary w;
	private Iterator<String> iter;

	private ArrayList<LinkedList<String>> wordListsByLength;
	private ArrayList<ArrayList<String>> fullListOfAnagrams;

	public FindAnagrams() throws FileNotFoundException {
		w = new Dictionary();
		words = w.getWords();

		wordListsByLength = w.readFileIntoListsBySize();
	}

	public void printAllAnagramsToFile() throws FileNotFoundException {
		File f = new File("./anagramList2.txt");
		PrintWriter p = new PrintWriter(f);
		for (ArrayList<String> list : fullListOfAnagrams) {
			p.println(list.size() + " \t" + list.toString());
		}
		p.close();

	}

	public static void main(String[] args) {
		try {
			FindAnagrams f = new FindAnagrams();
			System.out
					.println("\n\nLONGEST LIST:" + f.getLongestAnagramLists());
			System.out.println(f.fullListOfAnagrams.size());
			f.printAllAnagramsToFile();

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

	}

	public ArrayList<ArrayList<String>> getLongestAnagramLists() {
		int max = fullListOfAnagrams.get(0).size();
		ArrayList<Integer> indexesOfMax = new ArrayList<Integer>();
		indexesOfMax.add(0);
		for (int i = 1; i < fullListOfAnagrams.size(); i++) {
			int currentSize = fullListOfAnagrams.get(i).size();
			if (currentSize > max) {
				max = currentSize;
				indexesOfMax.clear();
				indexesOfMax.add(i);
			} else if (currentSize == max) {
				indexesOfMax.add(i);
			}
		}
		ArrayList<ArrayList<String>> longestAnagrams = new ArrayList<ArrayList<String>>();
		for (Integer i : indexesOfMax) {
			longestAnagrams.add(fullListOfAnagrams.get(i));
		}
		return longestAnagrams;
	}

	public void findAnagrams() {
		HashMap<String, String> wordsAlreadyAnagrammed = new HashMap<String, String>();
		fullListOfAnagrams = new ArrayList<ArrayList<String>>();
		ArrayList<String> anagrams = new ArrayList<String>();

		Set<String> wordSet = words.keySet();
		iter = wordSet.iterator();
		int i = 0;

		while (iter.hasNext()) {
			String word = iter.next();
			if (!wordsAlreadyAnagrammed.containsKey(word)) {
				wordsAlreadyAnagrammed.put(word, word);
				anagrams = getAnagramsOfWord(word);
				for (String anagram : anagrams) {
					wordsAlreadyAnagrammed.put(anagram, anagram);
				}
				if (!anagrams.isEmpty()) {
					anagrams.add(word);
					fullListOfAnagrams.add(anagrams);
				}
			}
		}
	}

	public ArrayList<String> getAnagramsOfWord(String s) {
		ArrayList<String> anagrams = new ArrayList<String>();
		Iterator<String> iterator = wordListsByLength.get(s.length() - 1)
				.iterator();
		while (iterator.hasNext()) {
			String word = iterator.next();
			if (w.isAlphaAnagram(s, word) && !w.equals(s)) {
				anagrams.add(word);
			}
		}
		return anagrams;
	}

}

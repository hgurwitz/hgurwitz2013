package gurwitz.dictionary;

import static org.junit.Assert.*;

import gurwitz.dictionary.FormWordsWithTiles;

import java.io.FileNotFoundException;
import java.util.ArrayList;

import org.junit.Test;

public class FormWordsWithTilesTest {

	@Test
	public void testGettingListOfValidWordsThatCanBeFormedByTiles() {
		try {
			FormWordsWithTiles f = new FormWordsWithTiles(7);
			System.out.println(f.getTiles());
			System.out.println(f.getWordsThatCanBeFormedWithTiles());
			ArrayList<Character> tiles = new ArrayList<Character>();
			tiles.add('a');
			tiles.add('b');
			tiles.add('c');
			tiles.add('d');
			ArrayList<String> words = new ArrayList<String>();
			words.add("da");
			words.add("ba");
			words.add("ad");
			words.add("bad");
			words.add("cab");
			words.add("cad");
			words.add("dab");
			f = new FormWordsWithTiles(tiles);
			ArrayList<String> wordsFromTiles = f
					.getWordsThatCanBeFormedWithTiles();
			assertEquals(words.size(), wordsFromTiles.size());
			for (String s : wordsFromTiles) {
				assertTrue(words.contains(s));
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}

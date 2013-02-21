package scrabble;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

public class WordsTest {

	private Words w;

	@Before
	public void setUp() throws Exception {
		w = new Words();
	}

	@Test
	public void testWordsThatDoExist() {
		assertTrue(w.contains("apple"));
		assertTrue(w.contains("giraffe"));
		assertTrue(w.contains("scooter"));
		assertTrue(w.contains("zygote"));

	}

	@Test
	public void testAnagrams() {
		assertTrue(w.isThisAnAlphaAnagram("apple", "apple"));

	}

	@Test
	public void testNonAnagrams() {
		assertFalse(w.isThisAnAlphaAnagram("pear", "para"));
		assertFalse(w.isThisAnAlphaAnagram("apple", "aplee"));
		assertFalse(w.isThisAnAlphaAnagram("apple", "applep"));

	}

	@Test
	public void testHashMapAnagrams() {
		assertTrue(w.isThisAnAnagram("apple", "apple"));

	}

	@Test
	public void testHashMapNonAnagrams() {
		assertFalse(w.isThisAnAnagram("pear", "para"));
		assertFalse(w.isThisAnAnagram("apple", "aplee"));
		assertFalse(w.isThisAnAnagram("apple2", "applep"));

	}

	@Test
	public void testWordsThatDoNotExist() {
		assertFalse(w.contains("hadassah"));
		assertFalse(w.contains("gurwitz"));
		assertFalse(w.contains("aaaaaaaaaa"));
		assertFalse(w.contains("herethere"));

	}

}

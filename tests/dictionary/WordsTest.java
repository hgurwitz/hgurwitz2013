package dictionary;

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
		assertTrue(w.isAlphaAnagram("apple", "apple"));

	}

	@Test
	public void testNonAnagrams() {
		assertFalse(w.isAlphaAnagram("pear", "para"));
		assertFalse(w.isAlphaAnagram("apple", "aplee"));
		assertFalse(w.isAlphaAnagram("apple", "applep"));

	}

	@Test
	public void testHashMapAnagrams() {
		assertTrue(w.isAnagram("apple", "apple"));

	}

	@Test
	public void testHashMapNonAnagrams() {
		assertFalse(w.isAnagram("pear", "para"));
		assertFalse(w.isAnagram("apple", "aplee"));
		assertFalse(w.isAnagram("apple2", "applep"));

	}

	@Test
	public void testWordsThatDoNotExist() {
		assertFalse(w.contains("hadassah"));
		assertFalse(w.contains("gurwitz"));
		assertFalse(w.contains("aaaaaaaaaa"));
		assertFalse(w.contains("herethere"));

	}

}

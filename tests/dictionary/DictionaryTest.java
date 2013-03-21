package dictionary;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

public class DictionaryTest {

	private Dictionary d;

	@Before
	public void setUp() throws Exception {
		d = new Dictionary();
	}

	@Test
	public void testWordsThatDoExist() {
		assertTrue(d.contains("apple"));
		assertTrue(d.contains("giraffe"));
		assertTrue(d.contains("scooter"));
		assertTrue(d.contains("zygote"));

	}

	@Test
	public void testAnagrams() {
		assertTrue(d.isAlphaAnagram("apple", "apple"));

	}

	@Test
	public void testNonAnagrams() {
		assertFalse(d.isAlphaAnagram("pear", "para"));
		assertFalse(d.isAlphaAnagram("apple", "aplee"));
		assertFalse(d.isAlphaAnagram("apple", "applep"));

	}

	@Test
	public void testHashMapAnagrams() {
		assertTrue(d.isAnagram("apple", "apple"));

	}

	@Test
	public void testHashMapNonAnagrams() {
		assertFalse(d.isAnagram("pear", "para"));
		assertFalse(d.isAnagram("apple", "aplee"));
		assertFalse(d.isAnagram("apple2", "applep"));

	}

	@Test
	public void testWordsThatDoNotExist() {
		assertFalse(d.contains("hadassah"));
		assertFalse(d.contains("gurwitz"));
		assertFalse(d.contains("aaaaaaaaaa"));
		assertFalse(d.contains("herethere"));

	}

}

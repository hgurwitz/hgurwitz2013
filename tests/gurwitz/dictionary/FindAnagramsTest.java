package gurwitz.dictionary;

import static org.junit.Assert.*;

import gurwitz.dictionary.FindAnagrams;

import java.io.FileNotFoundException;
import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

public class FindAnagramsTest {

	private FindAnagrams f;

	@Before
	public void setUp() {
		try {
			f = new FindAnagrams();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	public void testAnagramming() {
		ArrayList<String> testAnagrams = f.getAnagramsOfWord("abc");
		assertTrue(testAnagrams.size() == 1);
		assertTrue(testAnagrams.contains("cab"));

		testAnagrams = f.getAnagramsOfWord("pear");
		assertEquals(testAnagrams.size(), 5);
		assertTrue(testAnagrams.contains("pare"));
		assertTrue(testAnagrams.contains("reap"));
		assertTrue(testAnagrams.contains("rape"));
		assertTrue(testAnagrams.contains("aper"));
		assertTrue(testAnagrams.contains("pear"));

		testAnagrams = f.getAnagramsOfWord("a");
		assertTrue(testAnagrams.size() == 0);
	}

	@Test
	public void testFindingLargestAnagramList() {
		ArrayList<ArrayList<String>> tests = f.getLongestAnagramLists();
		ArrayList<String> test1 = tests.get(0);
		ArrayList<String> test2 = tests.get(1);
		ArrayList<String> largestAnagramList1 = new ArrayList<String>();
		largestAnagramList1.add("alerts");
		largestAnagramList1.add("alters");
		largestAnagramList1.add("artels");
		largestAnagramList1.add("estral");
		largestAnagramList1.add("laster");
		largestAnagramList1.add("ratels");
		largestAnagramList1.add("salter");
		largestAnagramList1.add("staler");
		largestAnagramList1.add("stelar");
		largestAnagramList1.add("talers");
		largestAnagramList1.add("slater");
		ArrayList<String> largestAnagramList2 = new ArrayList<String>();
		largestAnagramList2.add("asper");
		largestAnagramList2.add("pares");
		largestAnagramList2.add("parse");
		largestAnagramList2.add("pears");
		largestAnagramList2.add("prase");
		largestAnagramList2.add("presa");
		largestAnagramList2.add("reaps");
		largestAnagramList2.add("rapes");
		largestAnagramList2.add("spare");
		largestAnagramList2.add("spear");
		largestAnagramList2.add("apers");
		assertEquals(test1.size(), largestAnagramList1.size());
		assertEquals(test2.size(), largestAnagramList2.size());
		for (String s : largestAnagramList1) {
			assertTrue(test1.contains(s));
		}
		for (String s : largestAnagramList2) {
			assertTrue(test2.contains(s));
		}

	}

}

import static org.junit.Assert.*;

import gurwitz.interviewQs.FindDuplicate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

import org.junit.Test;

public class FindDuplicateTest {

	@Test
	public void test() {
		int[] numsArray = { 6, 7, 8, 9, 10, 11, 12, 12 };
		ArrayList<Integer> nums;
		nums = new ArrayList<Integer>();
		for (int i : numsArray) {
			nums.add(i);
		}
		FindDuplicate f = new FindDuplicate(nums);
		assertEquals(f.findTheDuplicate(), new Integer(12));
		nums.set(7, 13);
		f = new FindDuplicate(nums);
		assertEquals(f.findTheDuplicate(), null);
		nums.set(7, 12);
		nums.set(6, 11);
		nums.set(5, 10);
		f = new FindDuplicate(nums);
		assertEquals(f.findTheDuplicate(), new Integer(10));
	}

}

package gurwitz.interviewQs;

import java.util.ArrayList;

public class FindDuplicate {

	private ArrayList<Integer> nums;

	public FindDuplicate(ArrayList<Integer> nums) {
		this.nums = nums;
	}

	public Integer findTheDuplicate() {
		int size = nums.size();
		int first = nums.get(0);
		int low = 0;
		int high = size - 1;
		int mid = (high + low) / 2;
		while (low >= 0 && high < size) {
			mid = (high + low) / 2;
			if (high == low || (high == (low + 1))) {
				int answer = nums.get(high);
				if (answer == (first + high)) {
					return null;
				} else {
					return answer;
				}
			}

			if (nums.get(mid) == (first + mid)) {
				low = mid;
			} else {
				high = mid;
			}
		}
		return null;
	}

}

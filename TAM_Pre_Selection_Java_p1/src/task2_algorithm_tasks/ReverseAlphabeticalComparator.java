package task2_algorithm_tasks;

import java.util.Comparator;

public class ReverseAlphabeticalComparator implements Comparator<String> {
	@Override
	public int compare(String o1, String o2) {
		return -o1.compareTo(o2);
	}
}

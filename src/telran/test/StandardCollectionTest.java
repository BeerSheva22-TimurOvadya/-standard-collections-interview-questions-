package telran.test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import java.util.*;
import java.util.stream.Collectors;
class StandardCollectionTest {

	@BeforeEach
	void setUp() throws Exception {
	}

	@Test
	@Disabled
	void SubListtest() {
		List<Integer> list = new ArrayList<>(Arrays.asList(1,2,3,4,5,6,70,-20));
		list.add(5);
		List<Integer> listSub = list.subList(6, 9);
		
		System.out.println(listSub);
		listSub.add(1, -2);
		listSub.sort(Integer::compare);
		listSub.clear();
		System.out.println(list);
		
	}
	@Test
	@Disabled
	void displayOccurrencesCount() {
		String [] strings = {"lmn", "abc", "abc", "lmn", "a", "lmn"};
		Arrays.stream(strings)
				.collect(Collectors.groupingBy(s -> s,Collectors.counting()))
				.entrySet().stream().sorted((e1, e2) -> Long.compare(e2.getValue(), e1.getValue()))
				.forEach(e -> System.out.printf("%s: %d\n", e.getKey(), e.getValue()));
		
		
	}
	@Test
	@Disabled
	void displayDigitStatistics() {
		//Generate 1000000 random numbers [1-Integer.MAX_VALUE)
		//Display digits and counts of their occurrences in descending order of the counts
		//consider using flatMap for getting many from one
		new Random().ints(1_000_000, 1, Integer.MAX_VALUE)
		.flatMap(n -> Integer.toString(n).chars()).boxed()
		.collect(Collectors.groupingBy(digit -> digit, Collectors.counting()))
		.entrySet().stream().sorted((e1, e2) -> Long.compare(e2.getValue(), e1.getValue()))
		.forEach(e -> System.out.printf("%d: %d\n",   e.getKey() - '0', e.getValue()));
		;
	}
	@Test
	void maxNumberWithNegativeImageTest() {
		int ar[] = {10000000, 3, -2, 200, -200, -3, 2};
		int ar1[] = {1000000, -1000000000, 3, -4};
		assertEquals(200, maxNumberWithNegativeImage(ar));
		assertEquals(-1, maxNumberWithNegativeImage(ar1));
		
		
	}
	int maxNumberWithNegativeImage(int array[]) {
		int maxValue = -1;
		int candidate = -1;
		HashSet<Integer> helper = new HashSet<>();
		for(int num: array) {
			if (helper.contains(-num)) {
				int absNum = Math.abs(num);
				if(absNum > maxValue) {
					maxValue = absNum;
				}
			}
			helper.add(num);
			
			
		}
		return maxValue;
	}
	@Test
	void treeIteratingTest() {
		Integer array[] = {1, 11, 111, 32, 9, 1234, 99, 992};
		createAndIterateTreeInOrder(array);
	}

	private void createAndIterateTreeInOrder(Integer[] array) {
		//create tree, add in tree numbers from a given array
		//and iterate in the order of array defined in 69
		TreeSet<Integer> set = new TreeSet<>((num1, num2) ->
		Integer.compare(getSumDigits(num1), getSumDigits(num2)));
		for(int num: array) {
			set.add(num);
		}
		assertArrayEquals(array, set.toArray(new Integer[0]));
		
		
	}

	private int getSumDigits(Integer num1) {
		
		return num1.toString().chars().map(c -> c - '0').sum();
	}
	

}
package telran.test;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import telran.util.StackInt;

import java.util.*;
import java.util.stream.Collectors;

class StandardCollectionTest {

	@BeforeEach
	void setUp() throws Exception {
	}

	@Test
	@Disabled
	void SubListtest() {
		List<Integer> list = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5, 6, 70, -20));
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
		String[] strings = { "lmn", "abc", "abc", "lmn", "a", "lmn" };
		Arrays.stream(strings).collect(Collectors.groupingBy(s -> s, Collectors.counting())).entrySet().stream()
				.sorted((e1, e2) -> Long.compare(e2.getValue(), e1.getValue()))
				.forEach(e -> System.out.printf("%s: %d\n", e.getKey(), e.getValue()));

	}

	@Test
	@Disabled
	void displayDigitStatistics() {
		new Random().ints(1000000, 1, Integer.MAX_VALUE).flatMap(n -> Integer.toString(n).chars()).boxed()
				.collect(Collectors.groupingBy(digit -> digit, Collectors.counting())).entrySet().stream()
				.sorted((e1, e2) -> Long.compare(e2.getValue(), e1.getValue()))
				.forEach(e -> System.out.printf("Element: %d, occurs: %d times.\n", e.getKey() - '0', e.getValue()));
	}

	@Test
	@Disabled
	void stackIntTest() {
		StackInt stack = new StackInt();
		assertTrue(stack.isEmpty());
		assertThrows(NoSuchElementException.class, stack::pop);
		assertThrows(NoSuchElementException.class, stack::getMax);
		stack.push(-20);
		stack.push(-8);
		stack.push(-134);
		stack.push(58);
		stack.push(0);
		stack.push(6656);
		stack.push(3);
		assertFalse(stack.isEmpty());
		assertEquals(3, stack.pop());
		assertEquals(6656, stack.getMax());
		assertEquals(6656, stack.pop());
		assertEquals(58, stack.getMax());
		stack.pop();
		stack.pop();
		assertEquals(-8, stack.getMax());
		stack.pop();
		stack.pop();
		assertEquals(-20, stack.getMax());
		stack.pop();
		assertTrue(stack.isEmpty());
		assertThrows(NoSuchElementException.class, stack::getMax);
		assertThrows(NoSuchElementException.class, stack::pop);
	}

	@Test
	@Disabled
	void maxNumberWithNegativeImageTest() {
		int ar[] = { 10000000, 3, -2, -200, 200, -3, 2 };
		int ar1[] = { 1000000, -1000000000, 3, -4 }; 
		assertEquals(200, maxNumberWithNegativeImage(ar));
		assertEquals(-1, maxNumberWithNegativeImage(ar1));
	}

	int maxNumberWithNegativeImage(int array[]) {
		HashSet<Integer> HashSethelper = new HashSet<>();
		int res = -1;
		for (int num : array) {
			if (HashSethelper.contains(-num) && res < Math.abs(num)) {
				res = Math.abs(num);
			}
			HashSethelper.add(num);
		}
		return res;
	}

	@Test
//	@Disabled
	void treeIteratingTest() {
		int array[] = { 1, 11, 111, 32, 9, 1234, 99, 992 };
		TreeSet<Integer> tree = createAndIterateTreeInOrder(array);
		int[] expected = new int[array.length];
		Iterator<Integer> iterator = tree.iterator();
		int i = 0;
		while (iterator.hasNext()) {
			expected[i++] = iterator.next();
		}
		assertArrayEquals(expected, array);
	}

	private TreeSet<Integer> createAndIterateTreeInOrder(int[] array) {
		Comparator<Integer> comp = Comparator.comparingInt(this::sumOfDigits);
		TreeSet<Integer> treeSet = new TreeSet<>(comp);
		Arrays.stream(array).forEach(treeSet::add);
		return treeSet;
	}

	private int sumOfDigits(Integer number) {
		int sum = 0;
		while (number > 0) {
			sum = sum + number % 10;
			number = number / 10;
		}
		return sum;
	}

	@Test
//	@Disabled
	void treeIteratingSecondCaseTest() {
		int[] array = { 147, 1, 23, 3, 1460, 18597, 55 }; // %7 = [0, 1, 2, 3, 4, 5, 6]
		createAndIterateTreeInOrderSecondCase(array);
	}

	private void createAndIterateTreeInOrderSecondCase(int[] array) {
		Comparator<Integer> comp = Comparator.comparingInt(this::remainderOfTheDivision);
		TreeSet<Integer> treeSet = new TreeSet<>(comp);
		for (int i = 0; i < array.length; i++) {
			treeSet.add(array[i]);
		}
		System.out.println("TreeSet remainder divided by 7: " + treeSet + "\n");
	}

	private int remainderOfTheDivision(Integer number) {
		return number % 7;
	}

	@Test
//	@Disabled
	void treeIteratingThirdCaseTest() {
		int[] array = { 240, 264, 35, 1793, 74, 29, 269, 8 }; // %23 = [10, 11, 12, 22, 5, 6, 16, 8] = [1, 2, 3, 4, 5,6, 7, 8]
		createAndIterateTreeInOrderThirdCase(array);
	}

	private void createAndIterateTreeInOrderThirdCase(int[] array) {
		Comparator<Integer> comp = Comparator.comparingInt(this::sumOfTheRemainderDigits);
		TreeSet<Integer> treeSet = new TreeSet<>(comp);
		Arrays.stream(array).forEach(treeSet::add);
		System.out.println("TreeSet sum of remainder digits divided by 23: " + treeSet);
	}

	private int sumOfTheRemainderDigits(Integer number) {
		int remainder = number % 23;
		return sumOfDigits(remainder);
	}
	
}
package telran.test;

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
//	@Disabled
	void displayDigitStatistics() {
		new Random().ints(1000000, 1, Integer.MAX_VALUE).mapToObj(num -> Integer.toString(num))
				.flatMapToInt(String::chars).boxed().collect(Collectors.groupingBy(s -> s, Collectors.counting()))
				.entrySet().stream().sorted((e1, e2) -> Long.compare(e2.getValue(), e1.getValue()))
				.forEach(e -> System.out.printf("Element: %1c, occurs: %d times.\n", e.getKey(), e.getValue()));
	}

	@Test
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

}
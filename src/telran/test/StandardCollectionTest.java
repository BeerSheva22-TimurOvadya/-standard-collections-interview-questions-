package telran.test;

import java.util.*;

import org.junit.jupiter.api.*;


class StandardCollectionTest {

	@BeforeEach
	void setUp() throws Exception {
	}

	@Test
	void test() {
		List<Integer> list = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5, 6, 70, -20));
		list.add(5);
		List<Integer> listSub = list.subList(6, 9);
		
		System.out.println(listSub);
		listSub.add(1, -2);
		System.out.println(list);
	}

}

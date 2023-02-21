package telran.test;




import java.util.*;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class StandardCollectionTest {

	@BeforeEach
	void setUp() throws Exception {
	}

	@Test
	void test() {
		List<Integer> list = Arrays.asList(1, 2, 3, 4);
		list.add(5);
	}

}

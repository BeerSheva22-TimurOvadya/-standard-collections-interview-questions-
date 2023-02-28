package telran.test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.HashSet;

import org.junit.jupiter.api.Test;

import telran.structure.MultiCountersImpl;



class MultiCountersStructureTest {

	@Test
	void multiCountersTest() {

		 MultiCountersImpl struct = new  MultiCountersImpl();
		HashSet<Object> set = new HashSet<>();
		
		assertEquals(1, struct.addItem("I love Java"));
		assertEquals(2, struct.addItem("I love Java"));
		assertEquals(3, struct.addItem("I love Java"));
		assertEquals(1, struct.addItem(100));	
		assertEquals(2, struct.addItem(100));		
		assertEquals(1, struct.addItem("Hello"));
		
		assertEquals(null, struct.getValue("World"));
		assertEquals(1, struct.getValue("Hello"));		
		assertEquals(2, struct.getValue(100));
		assertEquals(3, struct.getValue("I love Java"));
		
		String[] arrayExpected= {"I love Java"};
		assertArrayEquals(arrayExpected, struct.getMaxItems().toArray(String[]::new));
		
				
		assertFalse(struct.remove("World"));
		assertTrue(struct.remove("I love Java"));	
		assertEquals(null, struct.getValue("I love Java"));
		
		Integer[] arrayIntExpected = {100};
		assertArrayEquals(arrayIntExpected, struct.getMaxItems().toArray(Integer[]::new));
		
		assertEquals(2, struct.addItem("Hello"));	
		set.add("Hello");
		set.add(100);	
		assertEquals(set, struct.getMaxItems());	
		
	}

}
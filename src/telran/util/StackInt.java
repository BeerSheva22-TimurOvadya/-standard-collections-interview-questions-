package telran.util;

import java.util.*;


public class StackInt {
	LinkedList<Integer> numbers = new LinkedList<>();
	LinkedList<Integer> maxNumbers = new LinkedList<>();
	
	
	public void push(int num) {
		numbers.addLast(num);
		if (maxNumbers.isEmpty() || num >= maxNumbers.getLast()) {
			maxNumbers.addLast(num);
		}
	}
	
	
	public int pop() {		
		int num = numbers.removeLast();
		if (num ==  maxNumbers.getLast()) {
			 maxNumbers.removeLast();
		}
		return num;
	}
	public boolean isEmpty () {
		return numbers.isEmpty();
	}
	public int getMax() {
		return maxNumbers.getLast();
	}
}
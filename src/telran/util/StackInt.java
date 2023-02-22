package telran.util;

import java.util.LinkedList;
import java.util.NoSuchElementException;


public class StackInt {
	private static LinkedList<Integer> stack = new LinkedList<>();
	private static LinkedList<Integer> maxNumber = new LinkedList<>();

	public void push(int num) {

		stack.add(num);
		if (maxNumber.isEmpty() || num >= maxNumber.getLast()) {
			maxNumber.add(num);
		}
	}

	public int pop() {
		possibleException();
		int res = stack.removeLast();
		if (res == maxNumber.getLast()) {
			maxNumber.removeLast();
		}
		return res;
	}

	public boolean isEmpty() {
		return stack.isEmpty();
	}

	public int getMax() {
		possibleException();
		return maxNumber.getLast();
	}

	private void possibleException() {
		if (stack.isEmpty()) {
			throw new NoSuchElementException();
		}
	}
}

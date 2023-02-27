package telran.util;

import java.util.HashMap;

public class MyArrayInt {
	 int value;
	 int size;
	 HashMap<Integer, Integer> array;
	
	public MyArrayInt(int size, int value) {
		this.size = size;
		this.value = value;		
	}
	
	public  void set(int index, int value) {
		if (array == null) {
			array = new HashMap<>();
		}
		if (index < 0 || index >= size) {
			throw new IndexOutOfBoundsException();
		}
		array.put(index, value);
	}
	
	public  int get(int index) {
		if (index < 0 || index >= size) {
			throw new IndexOutOfBoundsException();
		}
		int res = value;
		if (array != null) {
			res = array.getOrDefault(index, value);
		}
		return res;
	}
	
	public  void setAll(int value) {		
		this.value = value;	
		array = null;
	}
}

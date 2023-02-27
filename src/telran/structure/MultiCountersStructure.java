package telran.structure;

import java.util.*;

public class MultiCountersStructure implements MultiCounters {

	HashMap<Object, Integer> numberOfItems = new HashMap<>();
	TreeMap<Integer, Set<Object>> tree = new TreeMap<>();

	@Override
	public Integer addItem(Object item) {
		Integer res = numberOfItems.get(item);
		if (res == null) {
			res = 0;
		} else {
			removeItems(item, res);
		}		
		tree.computeIfAbsent(numberOfItems.merge(item, 1, Integer::sum), x -> new HashSet<>()).add(item);
		numberOfItems.put(item, ++res);
		return res;
	}
	

	private void removeItems(Object item, int count) {
		Set<Object> setItem = tree.get(count);
		setItem.remove(item);
		if (setItem.isEmpty()) {
			tree.remove(count);
		}
	}

	@Override
	public Integer getValue(Object item) {
		return numberOfItems.get(item);
	}

	@Override
	public boolean remove(Object item) {
		boolean res = false;
		Integer removedItem = numberOfItems.remove(item);
		if (removedItem != null) {
			removeItems(item, removedItem);	
			res = true;
		}
		return res;
	}

	@Override
	public Set<Object> getMaxItems() {
		Set<Object> res = new HashSet<>();
		if (tree.lastEntry() != null) {
			res = tree.lastEntry().getValue();
		}
		return res;
	}

}
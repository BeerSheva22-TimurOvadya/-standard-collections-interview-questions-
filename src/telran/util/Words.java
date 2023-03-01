package telran.util;

import java.util.*;

public class Words {
	TreeSet<String> set = new TreeSet<>(String.CASE_INSENSITIVE_ORDER);
	
	public boolean addWord(String word) {		
		return set.add(word);
	}

	
	public List<String> getWordsByPrefix(String prefix) { 		
		int size = prefix.length() - 1;
		char lastChar = prefix.charAt(size);		
		String getPrefix = prefix.substring(0, size) + ((char) (lastChar + 1));		
		return new ArrayList<>(set.subSet(prefix, getPrefix));		
	}
	
	
}
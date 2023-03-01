package telran.test;

import static org.junit.Assert.assertArrayEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import telran.util.Words;

class WordsTest {
	Words search;
	String words[] = { "wwwWww", "qwerty", "SSsSS", "s", "w", "q", "qWERTYQ", "Q1werty", "wsqwerty", " $/% " };

	@BeforeEach
	void setUp() throws Exception {
		search = new Words();
		for (String word : words) {
			search.addWord(word);
		}

	}

	@Test
	void test() {
		String words1[] = { "s", "SSsSS" };
		assertArrayEquals(words1, search.getWordsByPrefix("s").toArray(String[]::new));

		String words2[] = { "q", "Q1werty", "qwerty", "qWERTYQ" };
		assertArrayEquals(words2, search.getWordsByPrefix("Q").toArray(String[]::new));

		String words3[] = { "w", "wsqwerty", "wwwWww" };
		assertArrayEquals(words3, search.getWordsByPrefix("w").toArray(String[]::new));

		String words4[] = { " $/% " };
		assertArrayEquals(words4, search.getWordsByPrefix(" ").toArray(String[]::new));

	}

}
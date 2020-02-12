/*
Name: Kacie Rae
Date: 2-11-20
Description: Counting the occurrence of words and listing them in ascending order of number.
*/
import java.util.*;

public class Exercise21point7 {
	public static void main(String[] args) {
		String text = "Good morning. Have a good class. " + "Have a good visit. Have fun!";
		
		Map<String, Integer> map = new HashMap<>();
		String[] words = text.split("[\\s+\\p{P}]");
		for (int i = 0; i < words.length; i++) {
			String key = words[i].toLowerCase();
			if (key.length() > 0) {
				if (!map.containsKey(key)) {
					map.put(key, 1);
				}
				else {
					int value = map.get(key);
					value++;
					map.put(key, value);
				}
			}
		}
		ArrayList<WordOccurrence> list = new ArrayList<>();
		map.forEach((k, v) -> list.add(new WordOccurrence(k, v)));
		Collections.sort(list);
		list.forEach((word) -> System.out.println(word.word + "\t" + word.count));
	}
	public static class WordOccurrence implements Comparable<WordOccurrence>{
		int count;
		String word;

		public WordOccurrence(String word, int count) {
			this.word = word;
			this.count = count;
		}

		@Override
		public int compareTo(WordOccurrence o) {
			if (o.count > count)
				return -1;
			else if (o.count < count)
				return 1;
			else
				return 0;
		}
	}
}
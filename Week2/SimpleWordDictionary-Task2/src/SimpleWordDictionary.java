import java.util.*;

public class SimpleWordDictionary {
    private Map<Character, List<String>> words;

    public SimpleWordDictionary() {
        words = new TreeMap<>();
    }

    public void addWord(String word) {
        char key = word.charAt(0);
        if (!words.containsKey(key)) {
            words.put(key, new ArrayList<>());
        }
        List<String> wordList = words.get(key);
        wordList.add(word);
        Collections.sort(wordList);
    }

    public void printAll() {
        for (char key : words.keySet()) {
            System.out.println(key + ": " + words.get(key));
        }
    }

    public void printWordsOfLetter(char letter) {
        if (words.containsKey(letter)) {
            System.out.println(letter + ": " + words.get(letter));
        } else {
            System.out.println(letter + ": No words found.");
        }
    }
}

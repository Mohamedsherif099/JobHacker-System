public class Main {
    public static void main(String[] args) {
        SimpleWordDictionary dict = new SimpleWordDictionary();
        dict.addWord("apple");
        dict.addWord("banana");
        dict.addWord("cat");
        dict.addWord("dog");
        dict.addWord("elephant");
        dict.addWord("fish");

        dict.printAll();

        dict.printWordsOfLetter('e');

        dict.printWordsOfLetter('z'); // Output: z: No words found.

    }
}
public class PangramCheck {
    public boolean checkIfPangram(String sentence) {

        boolean[] letters = new boolean[26];
        int index;

        for (int i = 0; i < sentence.length(); i++) {
            if ('a' <= sentence.charAt(i) && sentence.charAt(i) <= 'z') {
                index = sentence.charAt(i) - 'a';
                letters[index] = true;
            }
        }

        for (int i = 0; i < 26; i++) {
            if (!letters[i]) {
                return false;
            }
        }

        return true;
    }
}

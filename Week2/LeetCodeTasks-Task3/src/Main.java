import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        ValidParentheses validParentheses =new ValidParentheses();
        System.out.println(validParentheses.isValid("()[]{}"));

        TwoSum theTwoSum = new TwoSum();
        int[] numbers= { 2, 7, 11, 15 };
        int target = 9;
        int[] result = theTwoSum.twoSum(numbers,target);
        System.out.println(Arrays.toString(result));

        LongestCommonPrefix theLongestCommonPrefix = new LongestCommonPrefix();
        String[] strs = {"flower","flow","flight"};
        System.out.println(theLongestCommonPrefix.longestCommonPrefix(strs));

        PangramCheck pangramCheck = new PangramCheck();
        String sentence = "thequickbrownfoxjumpsoverthelazydog";
        System.out.println(pangramCheck.checkIfPangram(sentence));
    }
}
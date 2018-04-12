package epi.string;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTestHandler;

public class IsStringPalindromicPunctuation {
    @EpiTest(testfile = "is_string_palindromic_punctuation.tsv")

    public static boolean isPalindrome(String s) {
        s = s.toLowerCase();
        int i = 0, j = s.length() - 1;
        while (i <= j) {
            while (i < s.length() &&
                    !Character.isDigit(s.charAt(i)) &&
                    !Character.isAlphabetic(s.charAt(i))) {
                i++;
            }
            while (j >= 0 &&
                    !Character.isDigit(s.charAt(j)) &&
                    !Character.isAlphabetic(s.charAt(j))) {
                j--;
            }
            if (i <= j && s.charAt(i) != s.charAt(j)) {
                return false;
            } else {
                i++;
                j--;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        GenericTestHandler.executeTestsByAnnotation(
                new Object() {
                }.getClass().getEnclosingClass(), args);
    }
}

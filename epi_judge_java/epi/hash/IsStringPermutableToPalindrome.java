package epi.hash;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTestHandler;

import java.util.HashMap;
import java.util.Map;

public class IsStringPermutableToPalindrome {

    @EpiTest(testfile = "can_string_be_palindrome.tsv")
    public static boolean canFormPalindrome(String s) {
        if (s == null || s.length() < 2) {
            return true;
        }
        Map<Character, Integer> countMap = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            countMap.put(c, countMap.getOrDefault(c, 0) + 1);
        }
        boolean odd = false;
        for (int v : countMap.values()) {
            if (v % 2 > 0) {
                if (odd) {
                    return false;
                } else {
                    odd = true;
                }
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

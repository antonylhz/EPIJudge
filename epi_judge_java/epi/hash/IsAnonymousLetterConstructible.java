package epi.hash;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTestHandler;

import java.util.HashMap;
import java.util.Map;

public class IsAnonymousLetterConstructible {
    @EpiTest(testfile = "is_anonymous_letter_constructible.tsv")

    public static boolean isLetterConstructibleFromMagazine(String letterText,
                                                            String magazineText) {
        Map<Character, Integer> countMap = new HashMap<>();
        for (int i = 0; i < magazineText.length(); i++) {
            char c = magazineText.charAt(i);
            countMap.put(c, countMap.getOrDefault(c, 0) + 1);
        }
        for (int i = 0; i < letterText.length(); i++) {
            char c = letterText.charAt(i);
            countMap.put(c, countMap.getOrDefault(c, 0) - 1);
            if (countMap.get(c) < 0) {
                return false;
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

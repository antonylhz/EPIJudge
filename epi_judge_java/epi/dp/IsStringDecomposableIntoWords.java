package epi.dp;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTestHandler;

import java.util.*;

public class IsStringDecomposableIntoWords {
    @EpiTest(testfile = "is_string_decomposable_into_words.tsv")

    public static List<String>
    decomposeIntoDictionaryWords(String domain, Set<String> dictionary) {
        List<LinkedList<String>> cache = new ArrayList<>();
        for (int i = 0; i < domain.length(); i++) {
            cache.add(new LinkedList<>());
        }
        return decomposeIntoDictionaryWords(domain, 0, dictionary, cache);
    }

    private static LinkedList<String> decomposeIntoDictionaryWords(String domain, int start, Set<String> dictionary, List<LinkedList<String>> cache) {
        if (cache.get(start).isEmpty()) {
            for (String str : dictionary) {
                int end = start + str.length();
                if (domain.substring(start, start + str.length()).equals(str)) {
                    if (end >= domain.length()) {
                        cache.set(start, new LinkedList<>(Collections.singletonList(str)));
                        break;
                    } else {
                        LinkedList<String> suffix = decomposeIntoDictionaryWords(domain, end, dictionary, cache);
                        if (!suffix.isEmpty()) {
                            LinkedList<String> list = new LinkedList<>(suffix);
                            list.addFirst(str);
                            cache.set(start, list);
                            break;
                        }
                    }
                }
            }
        }
        return cache.get(start);
    }

    public static void main(String[] args) {
        GenericTestHandler.executeTestsByAnnotation(
                new Object() {
                }.getClass().getEnclosingClass(), args);
    }
}

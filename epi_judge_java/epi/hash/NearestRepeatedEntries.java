package epi.hash;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTestHandler;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class NearestRepeatedEntries {
    @EpiTest(testfile = "nearest_repeated_entries.tsv")

    public static int findNearestRepetition(List<String> paragraph) {
        Map<String, Integer> usageMap = new HashMap<>();
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < paragraph.size(); i++) {
            String s = paragraph.get(i);
            if (usageMap.containsKey(s)) {
                min = Math.min(min, i - usageMap.get(s));
            }
            usageMap.put(s, i);
        }
        return min == Integer.MAX_VALUE ? -1 : min;
    }

    public static void main(String[] args) {
        GenericTestHandler.executeTestsByAnnotation(
                new Object() {
                }.getClass().getEnclosingClass(), args);
    }
}

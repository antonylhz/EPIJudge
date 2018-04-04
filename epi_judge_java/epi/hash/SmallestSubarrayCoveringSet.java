package epi.hash;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTestHandler;
import epi.test_framework.TestFailureException;
import epi.test_framework.TestTimer;

import java.security.InvalidParameterException;
import java.util.*;

public class SmallestSubarrayCoveringSet {

    // Represent subarray by starting and ending indices, inclusive.
    private static class Subarray {
        public Integer start;
        public Integer end;

        public Subarray(Integer start, Integer end) {
            this.start = start;
            this.end = end;
        }

        public void replace(int start, int end) {
            if (end - start < this.end - this.start) {
                this.start = start;
                this.end = end;
            }
        }
    }

    public static Subarray findSmallestSubarrayCoveringSet(List<String> paragraph,
                                                           Set<String> keywords) {
        // 1st pass. find the sub-array starting from index 0
        Map<String, Integer> countMap = new HashMap<>();
        int start = 0, end = completeCovering(paragraph, keywords, countMap, 0);
        if (countMap.size() < keywords.size()) {
            throw new InvalidParameterException();
        }

        Subarray subarray = new Subarray(start, end);
        while (++start <= paragraph.size() - keywords.size()) {
            String removed = paragraph.get(start - 1);
            countMap.put(removed, countMap.getOrDefault(removed, 0) - 1);
            if (countMap.get(removed) <= 0 ) {
                countMap.remove(removed);
            }
            end = completeCovering(paragraph,keywords, countMap, end + 1);
            if (countMap.size() >= keywords.size()) {
                subarray.replace(start, end);
            }
        }
        return subarray;
    }

    private static int completeCovering(List<String> paragraph,
                                        Set<String> keywords, Map<String, Integer> countMap, int start) {
        int end = start - 1;
        while (end + 1 < paragraph.size() && countMap.size() < keywords.size()){
            String s = paragraph.get(++end);
            if (keywords.contains(s)) {
                countMap.put(s, countMap.getOrDefault(s, 0) + 1);
            }
        }
        return end;
    }

    @EpiTest(testfile = "smallest_subarray_covering_set.tsv")
    public static int findSmallestSubarrayCoveringSetWrapper(
            TestTimer timer, List<String> paragraph, Set<String> keywords)
            throws TestFailureException {
        Set<String> copy = new HashSet<>(keywords);

        timer.start();
        Subarray result = findSmallestSubarrayCoveringSet(paragraph, keywords);
        timer.stop();

        if (result.start < 0 || result.start >= paragraph.size() ||
                result.end < 0 || result.end >= paragraph.size() ||
                result.start > result.end)
            throw new TestFailureException("Index out of range");

        for (int i = result.start; i <= result.end; i++)
            copy.remove(paragraph.get(i));

        if (!copy.isEmpty())
            throw new TestFailureException("Not all keywords are in the range");

        return result.end - result.start + 1;
    }

    public static void main(String[] args) {
        GenericTestHandler.executeTestsByAnnotation(
                new Object() {
                }.getClass().getEnclosingClass(), args);
    }
}

package epi.sorting;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTestHandler;

import java.util.LinkedList;
import java.util.List;

public class IntersectSortedArrays {
    @EpiTest(testfile = "intersect_sorted_arrays.tsv")

    public static List<Integer> intersectTwoSortedArrays(List<Integer> A,
                                                         List<Integer> B) {
        LinkedList<Integer> merged = new LinkedList<>();
        int ai = 0, bi = 0;
        while (ai < A.size() && bi < B.size()) {
            int an = A.get(ai), bn = B.get(bi);
            if (an == bn) {
                if (merged.isEmpty() || merged.peekLast() != an) {
                    merged.add(an);
                }
                ai++;
                bi++;
            } else if (an < bn) {
                ai++;
            } else {
                bi++;
            }
        }
        return merged;
    }

    public static void main(String[] args) {
        GenericTestHandler.executeTestsByAnnotation(
                new Object() {
                }.getClass().getEnclosingClass(), args);
    }
}

package epi.heap;

import epi.test_framework.EpiTest;
import epi.test_framework.EpiTestComparator;
import epi.test_framework.GenericTestHandler;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;
import java.util.function.BiPredicate;

public class KLargestInHeap {

    @EpiTest(testfile = "k_largest_in_heap.tsv")

    public static List<Integer> kLargestInBinaryHeap(List<Integer> A, int k) {
        PriorityQueue<Integer> heap = new PriorityQueue<>(k);
        for (int num : A) {
            if (heap.size() < k) {
                heap.add(num);
            } else if (num > heap.peek()) {
                heap.poll();
                heap.add(num);
            }
        }
        return new ArrayList<>(heap);
    }

    @EpiTestComparator
    public static BiPredicate<List<Integer>, List<Integer>> comp =
            (expected, result) -> {
                if (result == null) {
                    return false;
                }
                Collections.sort(expected);
                Collections.sort(result);
                return expected.equals(result);
            };

    public static void main(String[] args) {
        GenericTestHandler.executeTestsByAnnotation(
                new Object() {
                }.getClass().getEnclosingClass(), args);
    }
}

package epi.heap;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTestHandler;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;

public class SortAlmostSortedArray {

    public static List<Integer>
    sortApproximatelySortedData(Iterator<Integer> sequence, int k) {
        List<Integer> sorted = new LinkedList<>();
        PriorityQueue<Integer> heap = new PriorityQueue<>(k);
        while (sequence.hasNext() || !heap.isEmpty()) {
            while (heap.size() < k + 1 && sequence.hasNext()) {
                heap.add(sequence.next());
            }
            sorted.add(heap.poll());
        }
        return sorted;
    }

    @EpiTest(testfile = "sort_almost_sorted_array.tsv")
    public static List<Integer>
    sortApproximatelySortedDataWrapper(List<Integer> sequence, int k) {
        return sortApproximatelySortedData(sequence.iterator(), k);
    }

    public static void main(String[] args) {
        GenericTestHandler.executeTestsByAnnotation(
                new Object() {
                }.getClass().getEnclosingClass(), args);
    }
}

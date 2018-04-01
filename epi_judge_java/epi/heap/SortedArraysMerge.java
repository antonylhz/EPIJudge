package epi.heap;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTestHandler;

import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;

public class SortedArraysMerge {

    @EpiTest(testfile = "sorted_arrays_merge.tsv")
    public static List<Integer>
    mergeSortedArrays(List<List<Integer>> sortedArrays) {
        List<Integer> res = new LinkedList<>();
        if (sortedArrays == null || sortedArrays.size() < 1) return res;
        PriorityQueue<Entry> heap = new PriorityQueue<>(sortedArrays.size());
        for (int i = 0; i < sortedArrays.size(); i++) {
            heap.add(new Entry(i, 0, sortedArrays.get(i).get(0)));
        }
        while (!heap.isEmpty()) {
            Entry ent = heap.poll();
            res.add(ent.value);
            List<Integer> arr = sortedArrays.get(ent.arrIndex);
            if (ent.index < arr.size() - 1) {
                heap.add(new Entry(ent.arrIndex, ent.index + 1, arr.get(ent.index + 1)));
            }
        }
        return res;
    }

    static class Entry implements Comparable<Entry> {
        int arrIndex;
        int index;
        int value;
        public Entry (int arrIndex, int index, int value) {
            this.arrIndex = arrIndex;
            this.index = index;
            this.value = value;
        }

        @Override
        public int compareTo(Entry entry) {
            return this.value - entry.value;
        }
    }

    public static void main(String[] args) {
        GenericTestHandler.executeTestsByAnnotation(
                new Object() {
                }.getClass().getEnclosingClass(), args);
    }
}

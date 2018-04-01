package epi.heap;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTestHandler;

import java.util.*;

public class OnlineMedian {

    public static List<Double> onlineMedian(Iterator<Integer> sequence) {
        List<Double> res = new LinkedList<>();
        PriorityQueue<Integer> minHeap = new PriorityQueue<>(), maxHeap = new PriorityQueue<>(15, Collections.reverseOrder());
        while (sequence.hasNext()) {
            int num = sequence.next();
            if (maxHeap.isEmpty()) {
                maxHeap.add(num);
            } else if (num <= maxHeap.peek()) {
                maxHeap.add(num);
            } else {
                minHeap.add(num);
            }
            if (maxHeap.size() > minHeap.size() + 1) {
                minHeap.add(maxHeap.poll());
            } else if (minHeap.size() > maxHeap.size()) {
                maxHeap.add(minHeap.poll());
            }
            double median = maxHeap.size() > minHeap.size() ? 1.0 * maxHeap.peek() : 0.5 * (maxHeap.peek() + minHeap.peek());
            res.add(median);
        }
        return res;
    }

    @EpiTest(testfile = "online_median.tsv")
    public static List<Double> onlineMedianWrapper(List<Integer> sequence) {
        return onlineMedian(sequence.iterator());
    }

    public static void main(String[] args) {
        GenericTestHandler.executeTestsByAnnotation(
                new Object() {
                }.getClass().getEnclosingClass(), args);
    }
}

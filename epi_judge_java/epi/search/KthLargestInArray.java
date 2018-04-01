package epi.search;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTestHandler;

import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Random;

public class KthLargestInArray {

    // The numbering starts from one, i.e., if A = [3,1,-1,2] then
    // findKthLargest(A, 1) returns 3, findKthLargest(A, 2) returns 2,
    // findKthLargest(A, 3) returns 1, and findKthLargest(A, 4) returns -1.
    @EpiTest(testfile = "kth_largest_in_array.tsv")
    public static int findKthLargest(int k, List<Integer> A) {
        return findKthLargest(k, A, 0, A.size());
    }

    private static int findKthLargest(int k, List<Integer> arr, int s, int e) {
        if (s >= e || k > e - s) {
            throw new NoSuchElementException();
        }
        int p = new Random().nextInt(e - s) + s;
        Collections.swap(arr, p, e - 1);
        int sidx = s, lidx = e - 2;
        while (sidx <= lidx) {
            while (sidx < e - 1 && arr.get(sidx) < arr.get(e - 1)) {
                sidx++;
            }
            while (lidx >= 0 && arr.get(lidx) > arr.get(e - 1)) {
                lidx--;
            }
            if (sidx < lidx) {
                Collections.swap(arr, sidx, lidx);
                sidx++; lidx--;
            }
        }
        if (e - sidx == k) {
            return arr.get(e - 1);
        } else if (e - sidx < k) {
            return findKthLargest(k - e + sidx, arr, s, sidx);
        } else {
            return findKthLargest(k, arr, sidx, e - 1);
        }
    }

    public static void main(String[] args) {
        GenericTestHandler.executeTestsByAnnotation(
                new Object() {
                }.getClass().getEnclosingClass(), args);
    }
}

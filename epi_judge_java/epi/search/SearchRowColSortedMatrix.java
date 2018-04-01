package epi.search;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTestHandler;

import java.util.List;

public class SearchRowColSortedMatrix {
    @EpiTest(testfile = "search_row_col_sorted_matrix.tsv")
    public static boolean matrixSearch(List<List<Integer>> A, int x) {
        if (A == null || A.size() < 1 || A.get(0).size() < 1) {
            return false;
        }
        int m = A.size(), n = A.get(0).size();
        int r = 0, c = n - 1;
        while (r < m && c >= 0) {
            if (A.get(r).get(c) == x) {
                return true;
            } else if (A.get(r).get(c) < x) {
                r++;
            } else {
                c--;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        GenericTestHandler.executeTestsByAnnotation(
                new Object() {
                }.getClass().getEnclosingClass(), args);
    }
}

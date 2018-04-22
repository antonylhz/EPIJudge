package epi.dp;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTestHandler;

import java.util.ArrayList;
import java.util.List;

public class IsStringInMatrix {

    private static final int[][] MOVES = new int[][] {
            new int[] {1, 0},
            new int[] {-1 , 0},
            new int[] {0, 1},
            new int[] {0, -1}
    };

    @EpiTest(testfile = "is_string_in_matrix.tsv")
    public static boolean isPatternContainedInGrid(List<List<Integer>> grid,
                                                   List<Integer> pattern) {
        List<List<Boolean>> visited = new ArrayList<>();
        for (List<Integer> row : grid) {
            List<Boolean> visitedRow = new ArrayList<>();
            for (int i = 0; i < row.size(); i++) {
                visitedRow.add(false);
            }
            visited.add(visitedRow);
        }
        for (int i = 0; i < grid.size(); i++) {
            for (int j = 0; j < grid.get(i).size(); j++) {
                if (backtrack(grid, i, j, pattern, 0, visited)) {
                    return true;
                }
            }
        }
        return false;
    }


    private static boolean backtrack(List<List<Integer>> grid, int m, int n, List<Integer> pattern, int p, List<List<Boolean>> visited) {
        if (!grid.get(m).get(n).equals(pattern.get(p))) {
            return false;
        }
        if (p == pattern.size() - 1) {
            return true;
        }
        visited.get(m).set(n, true);
        for (int[] move : MOVES) {
            int mm = m + move[0];
            int nn = n + move[1];
            if (mm >= 0 && mm < grid.size() && nn >= 0 && nn < grid.get(mm).size()) {
                if (backtrack(grid, mm, nn, pattern, p + 1, visited)) {
                    return true;
                }
            }
        }
        visited.get(m).set(n, false);
        return false;
    }


    public static void main(String[] args) {
        GenericTestHandler.executeTestsByAnnotation(
                new Object() {
                }.getClass().getEnclosingClass(), args);
    }
}

package epi.graph;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTestHandler;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MatrixEnclosedRegions {

    private static final int[][] MOVES = new int[][] {
            {1, 0}, {-1, 0}, {0, 1}, {0, -1}
    };

    public static void fillSurroundedRegions(List<List<Character>> board) {
        List<List<Boolean>> visited = new ArrayList<>();
        for (List<Character> row : board) {
            visited.add(new ArrayList<>(Collections.nCopies(row.size(), false)));
        }
        for (int i = 0; i < board.size(); i++) {
            for (int j = 0; j < board.get(i).size(); j++) {
                if (board.get(i).get(j) == 'W')
                backtrack(board, i, j, visited);
            }
        }
    }

    private static boolean backtrack(List<List<Character>> board, int x, int y,
                                     List<List<Boolean>> visited) {
        if (x == 0 || x == board.size() - 1 ||
                y == 0 || y == board.get(x).size() - 1) {
            return true;
        }
        board.get(x).set(y, 'B');
        visited.get(x).set(y, Boolean.TRUE);
        for (int[] move : MOVES) {
            int nx = x + move[0];
            int ny = y + move[1];
            if (nx >= 0 && nx < board.size()
                    && ny >= 0 && ny < board.get(x).size()
                    && !visited.get(nx).get(ny)
                    && board.get(nx).get(ny) == 'W'
                    && backtrack(board, nx, ny, visited)) {
                board.get(x).set(y, 'W');
                return true;
            }
        }
        return false;
    }

    @EpiTest(testfile = "matrix_enclosed_regions.tsv")
    public static List<List<Character>>
    fillSurroundedRegionsWrapper(List<List<Character>> board) {
        fillSurroundedRegions(board);
        return board;
    }

    public static void main(String[] args) {
        GenericTestHandler.executeTestsByAnnotation(
                new Object() {
                }.getClass().getEnclosingClass(), args);
    }
}

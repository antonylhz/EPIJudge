package epi.recursion;

import com.sun.istack.internal.NotNull;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTestHandler;
import epi.test_framework.TestFailureException;
import epi.test_framework.TestTimer;

import java.util.*;

public class SudokuSolve {

    private static final int SIZE = 9;
    private static final int BLOCK = 3;

    private BitSet[] rowTracks = new BitSet[SIZE],
            colTracks = new BitSet[SIZE],
            regTracks = new BitSet[SIZE];

    private List<List<Integer>> assignment;

    public SudokuSolve() {
        for (int i = 0; i < SIZE; i++) {
            rowTracks[i] = new BitSet(SIZE + 1);
            colTracks[i] = new BitSet(SIZE + 1);
            regTracks[i] = new BitSet(SIZE + 1);
        }
    }

    public void initialize(@NotNull List<List<Integer>> partial) {
        this.assignment = partial;
        for (int i = 0; i < partial.size(); i++) {
            List<Integer> row = partial.get(i);
            for (int j = 0; j < row.size(); j++) {
                if (row.get(j) > 0) {
                    updateTrackers(i, j, row.get(j));
                }
            }
        }
    }

    private void updateTrackers(int r, int c, int num) {
        rowTracks[r].set(num);
        colTracks[c].set(num);
        regTracks[getBlockIndex(r, c)].set(num);
    }

    private void resetTrackers(int r, int c, int num) {
        rowTracks[r].clear(num);
        colTracks[c].clear(num);
        regTracks[getBlockIndex(r, c)].clear(num);
    }

    public boolean solve() {
        if (assignment.size() != SIZE || assignment.get(0).size() != SIZE) {
            return false;
        }
        return backtrack(0, 0);
    }

    public boolean backtrack(int r, int c) {
        if (r >= SIZE || c >= SIZE) {
            return true;
        }
        List<Integer> row = assignment.get(r);
        int nr = r + (c + 1) / SIZE,
                nc = (c + 1) % SIZE;
        if (row.get(c) == 0) {
            for (int n = 1; n <= SIZE; n++) {
                if (isValid(r, c, n)) {
                    row.set(c, n);
                    updateTrackers(r, c, n);
                    if (backtrack(nr, nc)) {
                        return true;
                    } else {
                        row.set(c, 0);
                        resetTrackers(r, c, n);
                    }
                }
            }
            return false;
        } else {
            return backtrack(nr, nc);
        }
    }

    private boolean isValid(int r, int c, int num) {
        return !rowTracks[r].get(num) &&
                !colTracks[c].get(num) &&
                !regTracks[getBlockIndex(r, c)].get(num);
    }

    private int getBlockIndex(int r, int c) {
        int br = r / BLOCK, bc = c / BLOCK;
        return br * BLOCK + bc;
    }

    public static boolean solveSudoku(List<List<Integer>> partial) {
        SudokuSolve solver = new SudokuSolve();
        solver.initialize(partial);
        return solver.solve();
    }

    @EpiTest(testfile = "sudoku_solve.tsv")
    public static void solveSudokuWrapper(TestTimer timer,
                                          List<List<Integer>> partialAssignment)
            throws TestFailureException {
        List<List<Integer>> solved = new ArrayList<>();
        for (List<Integer> row : partialAssignment) {
            List<Integer> copy = new ArrayList<>();
            copy.addAll(row);
            solved.add(copy);
        }

        timer.start();
        solveSudoku(solved);
        timer.stop();

        if (partialAssignment.size() != solved.size()) {
            throw new TestFailureException(
                    "Initial cell assignment has been changed");
        }

        for (int i = 0; i < partialAssignment.size(); i++) {
            List<Integer> br = partialAssignment.get(i);
            List<Integer> sr = solved.get(i);
            if (br.size() != sr.size()) {
                throw new TestFailureException(
                        "Initial cell assignment has been changed");
            }
            for (int j = 0; j < br.size(); j++)
                if (br.get(j) != 0 && !Objects.equals(br.get(j), sr.get(j)))
                    throw new TestFailureException(
                            "Initial cell assignment has been changed");
        }

        int blockSize = (int) Math.sqrt(solved.size());
        for (int i = 0; i < solved.size(); i++) {
            assertUniqueSeq(solved.get(i));
            assertUniqueSeq(gatherColumn(solved, i));
            assertUniqueSeq(gatherSquareBlock(solved, blockSize, i));
        }
    }

    private static void assertUniqueSeq(List<Integer> seq)
            throws TestFailureException {
        Set<Integer> seen = new HashSet<>();
        for (Integer x : seq) {
            if (x == 0) {
                throw new TestFailureException("Cell left uninitialized");
            }
            if (x < 0 || x > seq.size()) {
                throw new TestFailureException("Cell value out of range");
            }
            if (seen.contains(x)) {
                throw new TestFailureException("Duplicate value in section");
            }
            seen.add(x);
        }
    }

    private static List<Integer> gatherColumn(List<List<Integer>> data, int i) {
        List<Integer> result = new ArrayList<>();
        for (List<Integer> row : data) {
            result.add(row.get(i));
        }
        return result;
    }

    private static List<Integer> gatherSquareBlock(List<List<Integer>> data,
                                                   int blockSize, int n) {
        List<Integer> result = new ArrayList<>();
        int blockX = n % blockSize;
        int blockY = n / blockSize;
        for (int i = blockX * blockSize; i < (blockX + 1) * blockSize; i++) {
            for (int j = blockY * blockSize; j < (blockY + 1) * blockSize; j++) {
                result.add(data.get(i).get(j));
            }
        }

        return result;
    }

    public static void main(String[] args) {
        GenericTestHandler.executeTestsByAnnotation(
                new Object() {
                }.getClass().getEnclosingClass(), args);
    }
}

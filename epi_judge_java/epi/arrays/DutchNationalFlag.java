package epi.arrays;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTestHandler;
import epi.test_framework.TestFailureException;
import epi.test_framework.TestTimer;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DutchNationalFlag {
    public static enum Color {RED, WHITE, BLUE}

    public static void dutchFlagPartition(int pivotIndex, List<Color> A) {
        Color pivot = A.get(pivotIndex);
        for (int i = 0; i < A.size(); i++) {
            if (A.get(i).ordinal() >= pivot.ordinal()) {
                for (int j = i + 1; j < A.size(); j++) {
                    if (A.get(j).ordinal() < pivot.ordinal()) {
                        Collections.swap(A, i, j);
                        break;
                    }
                }
            }
        }
        for (int i = A.size() - 1; i >= 0; i--) {
            if (A.get(i).ordinal() <= pivot.ordinal()) {
                for (int j = i - 1; j >= 0; j--) {
                    if (A.get(j).ordinal() > pivot.ordinal()) {
                        Collections.swap(A, i , j);
                        break;
                    }
                }
            }
        }
    }

    @EpiTest(testfile = "dutch_national_flag.tsv")
    public static void dutchFlagPartitionWrapper(TestTimer timer, List<Integer> A,
                                                 int pivotIdx)
            throws TestFailureException {
        List<Color> colors = new ArrayList<>();
        int[] count = new int[3];

        Color[] C = Color.values();
        for (int i = 0; i < A.size(); i++) {
            count[A.get(i)]++;
            colors.add(C[A.get(i)]);
        }
        Color pivot = colors.get(pivotIdx);

        timer.start();
        dutchFlagPartition(pivotIdx, colors);
        timer.stop();

        int i = 0;
        while (i < colors.size() && colors.get(i).ordinal() < pivot.ordinal()) {
            count[colors.get(i).ordinal()]--;
            ++i;
        }

        while (i < colors.size() && colors.get(i).ordinal() == pivot.ordinal()) {
            count[colors.get(i).ordinal()]--;
            ++i;
        }

        while (i < colors.size() && colors.get(i).ordinal() > pivot.ordinal()) {
            count[colors.get(i).ordinal()]--;
            ++i;
        }

        if (i != colors.size() || count[0] != 0 || count[1] != 0 || count[2] != 0) {
            throw new TestFailureException("Invalid output");
        }
    }

    public static void main(String[] args) {
        GenericTestHandler.executeTestsByAnnotation(
                new Object() {
                }.getClass().getEnclosingClass(), args);
    }
}

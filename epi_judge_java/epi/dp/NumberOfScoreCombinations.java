package epi.dp;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTestHandler;

import java.util.Arrays;
import java.util.List;

public class NumberOfScoreCombinations {

    @EpiTest(testfile = "number_of_score_combinations.tsv")
    public static int
    numCombinationsForFinalScore(int finalScore,
                                 List<Integer> individualPlayScores) {
        int[] ncum = new int[finalScore + 1];
        ncum[0] = 1;
        for (int score : individualPlayScores) {
            int[] nncum = Arrays.copyOf(ncum, ncum.length);
            nncum[0] = 1;
            for (int i = score; i < ncum.length; i++) {
                for (int j = score; j <= i; j += score) {
                    nncum[i] += ncum[i - j];
                }
            }
            ncum = nncum;
        }
        return ncum[finalScore];
    }

    public static void main(String[] args) {
        GenericTestHandler.executeTestsByAnnotation(
                new Object() {
                }.getClass().getEnclosingClass(), args);
    }
}

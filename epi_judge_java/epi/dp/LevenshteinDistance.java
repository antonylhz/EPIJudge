package epi.dp;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTestHandler;

public class LevenshteinDistance {
    @EpiTest(testfile = "levenshtein_distance.tsv")

    public static int levenshteinDistance(String A, String B) {
        if (A.isEmpty()) return B.length();
        else if (B.isEmpty()) return A.length();
        int[][] edits = new int[A.length() + 1][B.length() + 1];
        for (int i = 0; i <= A.length(); i++) {
            for (int j = 0; j <= B.length(); j++) {
                if (i == 0 || j == 0) {
                    edits[i][j] = Math.max(i, j);
                } else if (A.charAt(i - 1) == B.charAt(j - 1)) {
                    edits[i][j] = edits[i - 1][j - 1];
                } else {
                    edits[i][j] = 1 + Math.min(Math.min(edits[i - 1][j], edits[i][j - 1]), edits[i - 1][j - 1]);
                }
            }
        }
        return edits[A.length()][B.length()];
    }

    public static void main(String[] args) {
        GenericTestHandler.executeTestsByAnnotation(
                new Object() {
                }.getClass().getEnclosingClass(), args);
    }
}

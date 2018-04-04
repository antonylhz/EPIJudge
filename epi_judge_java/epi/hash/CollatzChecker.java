package epi.hash;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTestHandler;

public class CollatzChecker {
    @EpiTest(testfile = "collatz_checker.tsv")

    public static boolean testCollatzConjecture(int n) {
        boolean[] cache = new boolean[n + 1];
        for (int i = 1; i <= n; i++) {
            if (cache[i]) continue;
            long t = i;
            while (t > 1) {
                if (t <= n) {
                    cache[(int) t] = true;
                }
                if (t % 2 == 0) {
                    t /= 2;
                } else {
                    t *= 3;
                    t++;
                }
            }
            if (t < 1) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        GenericTestHandler.executeTestsByAnnotation(
                new Object() {
                }.getClass().getEnclosingClass(), args);
    }
}

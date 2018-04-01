package epi.search;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTestHandler;

import java.util.BitSet;
import java.util.NoSuchElementException;

public class AbsentValueArray {
    private static final int BUCKET = 1 << 16;

    @EpiTest(testfile = "absent_value_array.tsv")
    public static int findMissingElement(Iterable<Integer> stream) {
        for (int i = 0; i < BUCKET; i++) {
            BitSet set = new BitSet();
            int prefix = i << 16;
            int finalI = i;
            stream.forEach(number -> {
                if ((number >>> 16) == finalI) {
                    set.set(number & (BUCKET - 1));
                }
            });

            for (int j = 0; j < BUCKET; j++) {
                if (!set.get(j)) {
                    return prefix + j;
                }
            }
        }
        throw new NoSuchElementException("No missing ip address");
    }

    public static void main(String[] args) {
        GenericTestHandler.executeTestsByAnnotation(
                new Object() {
                }.getClass().getEnclosingClass(), args);
    }
}

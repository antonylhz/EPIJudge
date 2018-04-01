package epi.search;

import epi.test_framework.EpiTest;
import epi.test_framework.EpiUserType;
import epi.test_framework.GenericTestHandler;

import java.util.List;

public class SearchForMinMaxInArray {
    @EpiUserType(ctorParams = {Integer.class, Integer.class})

    public static class MinMax {
        public Integer smallest;
        public Integer largest;

        public MinMax(Integer smallest, Integer largest) {
            this.smallest = smallest;
            this.largest = largest;
        }

        private static MinMax minMax(Integer a, Integer b) {
            return Integer.compare(b, a) < 0 ? new MinMax(b, a) : new MinMax(a, b);
        }

        public void merge(MinMax minMax) {
            this.smallest = Math.min(this.smallest, minMax.smallest);
            this.largest = Math.max(this.largest, minMax.largest);
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }

            MinMax minMax = (MinMax) o;

            if (!smallest.equals(minMax.smallest)) {
                return false;
            }
            return largest.equals(minMax.largest);
        }

        @Override
        public String toString() {
            return "min: " + smallest + ", max: " + largest;
        }
    }

    @EpiTest(testfile = "search_for_min_max_in_array.tsv")
    public static MinMax findMinMax(List<Integer> A) {
        if (A == null || A.size() < 1) return null;
        MinMax res = MinMax.minMax(A.get(0), A.get(A.size() - 1));
        for (int i = 1, j = A.size() - 2; i <= j; i++, j--) {
            res.merge(MinMax.minMax(A.get(i), A.get(j)));
        }
        return res;
    }

    public static void main(String[] args) {
        GenericTestHandler.executeTestsByAnnotation(
                new Object() {
                }.getClass().getEnclosingClass(), args);
    }
}

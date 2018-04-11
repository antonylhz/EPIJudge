package epi.sorting;

import epi.test_framework.EpiTest;
import epi.test_framework.EpiUserType;
import epi.test_framework.GenericTestHandler;

import java.util.ArrayList;
import java.util.List;

public class IntervalAdd {
    @EpiUserType(ctorParams = {int.class, int.class})

    public static class Interval {
        public int left, right;

        public Interval(int l, int r) {
            this.left = l;
            this.right = r;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }

            Interval interval = (Interval) o;

            if (left != interval.left) {
                return false;
            }
            return right == interval.right;
        }

        @Override
        public String toString() {
            return "[" + left + ", " + right + "]";
        }
    }

    @EpiTest(testfile = "interval_add.tsv")

    public static List<Interval> addInterval(List<Interval> disjointIntervals,
                                             Interval newInterval) {
        List<Interval> res = new ArrayList<>();
        int i = 0;
        while (i < disjointIntervals.size() && disjointIntervals.get(i).right < newInterval.left) {
            res.add(disjointIntervals.get(i++));
        }
        while (i < disjointIntervals.size()) {
            Interval itv = disjointIntervals.get(i);
            if (itv.left <= newInterval.right) {
                newInterval.left = Math.min(newInterval.left, itv.left);
                newInterval.right = Math.max(newInterval.right, itv.right);
                i++;
            } else {
                break;
            }
        }
        res.add(newInterval);
        res.addAll(disjointIntervals.subList(i, disjointIntervals.size()));
        return res;
    }

    public static void main(String[] args) {
        GenericTestHandler.executeTestsByAnnotation(
                new Object() {
                }.getClass().getEnclosingClass(), args);
    }
}

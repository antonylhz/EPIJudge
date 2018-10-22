package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.EpiUserType;
import epi.test_framework.GenericTestHandler;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class MinimumPointsCoveringIntervals {
    @EpiUserType(ctorParams = {int.class, int.class})

    public static class Interval {
        public int left, right;

        public Interval(int l, int r) {
            this.left = l;
            this.right = r;
        }
    }

    @EpiTest(testfile = "points_covering_intervals.tsv")

    public static Integer findMinimumVisits(List<Interval> intervals) {
        if (intervals == null || intervals.isEmpty()) return 0;
        Collections.sort(intervals, new Comparator<Interval>() {
            @Override
            public int compare(Interval o1, Interval o2) {
                return o1.right == o2.right ? o1.left - o2.left : o1.right - o2.right;
            }
        });
        int visits = 0, right = -1;
        for (Interval interval : intervals) {
            if (interval.left > right) {
                visits++;
                right = interval.right;
            }
        }
        return visits;
    }

    public static void main(String[] args) {
        GenericTestHandler.executeTestsByAnnotation(
                new Object() {
                }.getClass().getEnclosingClass(), args);
    }
}

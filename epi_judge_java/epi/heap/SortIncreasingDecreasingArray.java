package epi.heap;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTestHandler;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class SortIncreasingDecreasingArray {
  @EpiTest(testfile = "sort_increasing_decreasing_array.tsv")

  public static List<Integer> sortKIncreasingDecreasingArray(List<Integer> A) {
      return SortedArraysMerge.mergeSortedArrays(decomposeKIncreasingDecreasingArray(A));
  }

  public static List<List<Integer>> decomposeKIncreasingDecreasingArray(List<Integer> list) {
      List<List<Integer>> res = new ArrayList<>();
      if (list == null || list.size() < 1) return res;
      List<Integer> sublist = new LinkedList<>();
      int prev = list.get(0);
      boolean increment = true;
      for (int i = 1; i < list.size(); i++) {
          int cur = list.get(i);
          if ((increment && cur > prev) ||
                  (!increment && cur < prev)) {
              sublist.add(prev);
          } else {
              if (!increment) Collections.reverse(sublist);
              if (!sublist.isEmpty()) res.add(sublist);
              sublist = new LinkedList<>();
              sublist.add(prev);
              increment = !increment;
          }
          prev = cur;
      }
      sublist.add(prev);
      if (!increment) Collections.reverse(sublist);
      res.add(sublist);
      return res;
  }

  public static void main(String[] args) {
    GenericTestHandler.executeTestsByAnnotation(
        new Object() {}.getClass().getEnclosingClass(), args);
  }
}

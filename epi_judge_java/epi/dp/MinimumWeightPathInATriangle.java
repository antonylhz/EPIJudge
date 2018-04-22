package epi.dp;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTestHandler;

import java.util.ArrayList;
import java.util.List;

public class MinimumWeightPathInATriangle {
  @EpiTest(testfile = "minimum_weight_path_in_a_triangle.tsv")

  public static int minimumPathTotal(List<List<Integer>> triangle) {
      if (triangle == null || triangle.isEmpty()) {
          return 0;
      }
      List<Integer> path = new ArrayList<>();
      for (List<Integer> row : triangle) {
          List<Integer> next = new ArrayList<>();
          for (int i = 0; i < row.size(); i++) {
              int min = Integer.MAX_VALUE;
              if (i - 1 >= 0) {
                  min = Math.min(min, path.get(i - 1));
              }
              if (i < path.size()) {
                  min = Math.min(min, path.get(i));
              }
              if (min == Integer.MAX_VALUE) min = 0;
              next.add(min + row.get(i));
          }
          path = next;
      }
      int min = Integer.MAX_VALUE;
      for (int p : path) {
          min = Math.min(min, p);
      }
      return min;
  }

  public static void main(String[] args) {
    GenericTestHandler.executeTestsByAnnotation(
        new Object() {}.getClass().getEnclosingClass(), args);
  }
}

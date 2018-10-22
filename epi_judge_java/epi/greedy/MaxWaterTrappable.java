package epi.greedy;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTestHandler;

import java.util.List;

public class MaxWaterTrappable {
  @EpiTest(testfile = "max_water_trappable.tsv")

  public static int calculateTrappingWater(List<Integer> heights) {
      return calculateTrappingWater(heights, 0, heights.size() - 1, 0, 0);
  }

  private static int calculateTrappingWater(List<Integer> heights, int start, int end, int max, int diff) {
      if (start >= end) return diff;
      int h1 = heights.get(start), h2 = heights.get(end);
      int trapped = (end - start) * Math.min(h1, h2);
      if (trapped > max) {
          max = trapped;
          diff = end - start;
      }
      if (h1 == h2) {
          return calculateTrappingWater(heights, start + 1, end - 1, max, diff);
      } else if (h1 > h2) {
          return calculateTrappingWater(heights, start, end - 1, max, diff);
      } else {
          return calculateTrappingWater(heights, start + 1, end, max, diff);
      }
  }

  public static void main(String[] args) {
    GenericTestHandler.executeTestsByAnnotation(
        new Object() {}.getClass().getEnclosingClass(), args);
  }
}

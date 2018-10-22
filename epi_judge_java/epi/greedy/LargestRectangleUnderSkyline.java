package epi.greedy;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTestHandler;

import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

public class LargestRectangleUnderSkyline {
  @EpiTest(testfile = "largest_rectangle_under_skyline.tsv")

  public static int calculateLargestRectangle(List<Integer> heights) {
      Deque<Integer> stack = new LinkedList<>();
      int max = 0;
      for (int i = 0; i < heights.size(); i++) {
          if (stack.isEmpty() || heights.get(stack.peek()) < heights.get(i)) {
              stack.push(i);
          } else {
              while (!stack.isEmpty() && heights.get(stack.peek()) >= heights.get(i)) {
                  int height = heights.get(stack.pop());
                  int left = stack.isEmpty() ? 0 : stack.peek() + 1;
                  max = Math.max(max, height * (i - left));
              }
              stack.push(i);
          }
      }

      int right = heights.size();
      while (!stack.isEmpty()) {
          int height = heights.get(stack.pop());
          int left = stack.isEmpty() ? 0 : stack.peek() + 1;
          max = Math.max(max, height * (right - left));
      }
      return max;
  }

  public static void main(String[] args) {
    GenericTestHandler.executeTestsByAnnotation(
        new Object() {}.getClass().getEnclosingClass(), args);
  }
}

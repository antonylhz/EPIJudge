package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTestHandler;

import java.util.List;

public class MaximumSubarrayInCircularArray {
  @EpiTest(testfile = "maximum_subarray_in_circular_array.tsv")

  public static int maxSubarraySumInCircular(List<Integer> A) {
      int sum = 0;
      for (int num : A) sum += num;
      return Math.max(maxSubarray(A), sum - minSubarray(A));
  }

  private static int minSubarray(List<Integer> A) {
      int sum = 0, maxSum = Integer.MIN_VALUE, min = Integer.MAX_VALUE;
      for (int num : A) {
          sum += num;
          maxSum = Math.max(maxSum, sum);
          min = Math.min(min, sum - maxSum);
      }
      return min;
  }

  private static int maxSubarray(List<Integer> A) {
      int sum = 0, minSum = Integer.MAX_VALUE, max = Integer.MIN_VALUE;
      for (int num : A) {
          sum += num;
          minSum = Math.min(minSum, sum);
          max = Math.max(max, sum - minSum);
      }
      return max;
  }



  public static void main(String[] args) {
    GenericTestHandler.executeTestsByAnnotation(
        new Object() {}.getClass().getEnclosingClass(), args);
  }
}

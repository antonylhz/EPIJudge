package epi.string;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTestHandler;
import epi.test_framework.TestFailureException;

public class StringIntegerInterconversion {

  public static String intToString(int x) {
      StringBuilder sb = new StringBuilder();
      boolean neg = x < 0;
      long l = neg ? -1L * x : x;
      while (l > 0) {
          sb.append(l % 10);
          l /= 10;
      }
      if (neg) {
          sb.append("-");
      }
      return sb.length() == 0 ? "0" : sb.reverse().toString();
  }

  public static int stringToInt(String s) {
      if (s == null || s.length() == 0) return 0;
      boolean neg = s.charAt(0) == '-';
      int res = 0;
      for (int i = neg ? 1 : 0; i < s.length(); i++) {
          res *= 10;
          res += s.charAt(i) - '0';
      }
      return neg ? -res : res;
  }

  @EpiTest(testfile = "string_integer_interconversion.tsv")
  public static void wrapper(int x, String s) throws TestFailureException {
    if (!intToString(x).equals(s)) {
      throw new TestFailureException("Int to string conversion failed");
    }
    if (stringToInt(s) != x) {
      throw new TestFailureException("String to int conversion failed");
    }
  }

  public static void main(String[] args) {
    GenericTestHandler.executeTestsByAnnotation(
        new Object() {}.getClass().getEnclosingClass(), args);
  }
}

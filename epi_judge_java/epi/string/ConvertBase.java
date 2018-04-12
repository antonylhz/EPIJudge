package epi.string;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTestHandler;

public class ConvertBase {
  @EpiTest(testfile = "convert_base.tsv")

  public static String convertBase(String numAsString, int b1, int b2) {
      boolean neg = numAsString.length() > 0 && numAsString.charAt(0) == '-';
      if (neg) numAsString = numAsString.substring(1);
      return (neg ? "-" : "") + convertToString(convertToInt(numAsString, b1), b2);
  }

  private static int convertToInt(String s, int b) {
      if (s == null || s.length() == 0) return 0;
      int n = 0;
      for (int i = 0; i < s.length(); i++) {
          n *= b;
          n += getDigit(s.charAt(i));
      }
      return n;
  }

  private static String convertToString(int n, int b) {
      StringBuilder sb = new StringBuilder();
      while (n > 0) {
          sb.append(getChar(n % b));
          n /= b;
      }
      return sb.length() == 0 ? "0" : sb.reverse().toString();
  }

  private static int getDigit(char c) {
      if (Character.isDigit(c)) {
          return c - '0';
      } else {
          return 10 + c - 'A';
      }
  }

  private static char getChar(int d) {
      if (d < 10) return (char) ('0' + d);
      else return  (char) ('A' + d - 10);
  }

  public static void main(String[] args) {
    GenericTestHandler.executeTestsByAnnotation(
        new Object() {}.getClass().getEnclosingClass(), args);
  }
}

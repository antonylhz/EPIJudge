package epi.string;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTestHandler;
import epi.test_framework.TestFailureException;

public class RunLengthCompression {

    public static String decoding(String s) {
        StringBuilder sb = new StringBuilder();
        int count = 0;
        for (char c : s.toCharArray()) {
            if (Character.isDigit(c)) {
                count *= 10;
                count += c - '0';
            } else {
                while (count > 0) {
                    sb.append(c);
                    count--;
                }
            }
        }
        return sb.toString();
    }

    public static String encoding(String s) {
        StringBuilder sb = new StringBuilder();
        int count = 0;
        for (int i = 0; i < s.length(); i++) {
            count++;
            if (i == s.length() - 1 || s.charAt(i) != s.charAt(i + 1)) {
                sb.append(count).append(s.charAt(i));
                count = 0;
            }
        }
        return sb.toString();
    }

    @EpiTest(testfile = "run_length_compression.tsv")
    public static void rleTester(String encoded, String decoded)
            throws TestFailureException {
        if (!decoding(encoded).equals(decoded)) {
            throw new TestFailureException("Decoding failed");
        }
        if (!encoding(decoded).equals(encoded)) {
            throw new TestFailureException("Encoding failed");
        }
    }

    public static void main(String[] args) {
        GenericTestHandler.executeTestsByAnnotation(
                new Object() {
                }.getClass().getEnclosingClass(), args);
    }
}

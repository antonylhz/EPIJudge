package epi.stack_queue;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTestHandler;

import java.util.Deque;
import java.util.LinkedList;

public class EvaluateRpn {
  @EpiTest(testfile = "evaluate_rpn.tsv")

  public static int eval(String expression) {
      Deque<Integer> stack = new LinkedList<>();
      String[] tokens = expression.split(",");
      for (String token : tokens) {
          if (token.equals("+")) {
              int opr2 = stack.pop(), opr1 = stack.pop();
              stack.push(opr1 + opr2);
          } else if (token.equals("-")) {
              int opr2 = stack.pop(), opr1 = stack.pop();
              stack.push(opr1 - opr2);
          } else if (token.equals("*")) {
              int opr2 = stack.pop(), opr1 = stack.pop();
              stack.push(opr1 * opr2);
          } else if (token.equals("/")) {
              int opr2 = stack.pop(), opr1 = stack.pop();
              stack.push(opr1 / opr2);
          } else {
              stack.push(Integer.parseInt(token));
          }
      }
      return stack.peek();
  }

  public static void main(String[] args) {
    GenericTestHandler.executeTestsByAnnotation(
        new Object() {}.getClass().getEnclosingClass(), args);
  }
}

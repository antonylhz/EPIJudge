package epi.stack_queue;

import epi.test_framework.EpiTest;
import epi.test_framework.EpiUserType;
import epi.test_framework.GenericTestHandler;
import epi.test_framework.TestFailureException;

import java.util.*;

public class StackWithMax {

    public static class Stack {

        private Deque<Integer> numbers, maxes;

        public Stack () {
            this.numbers = new LinkedList<>();
            this.maxes = new LinkedList<>();
        }

        public boolean empty() {
            return this.numbers.isEmpty();
        }

        public Integer max() {
            // Implement this placeholder.
            return maxes.peek();
        }

        public Integer pop() {
            maxes.pop();
            return numbers.pop();
        }

        public void push(Integer x) {
            numbers.push(x);
            if (maxes.isEmpty()) {
                maxes.push(x);
            } else {
                maxes.push(Math.max(x, maxes.peek()));
            }
        }
    }

    @EpiUserType(ctorParams = {String.class, int.class})
    public static class StackOp {
        public String op;
        public int arg;

        public StackOp(String op, int arg) {
            this.op = op;
            this.arg = arg;
        }
    }

    @EpiTest(testfile = "stack_with_max.tsv")
    public static void stackTest(List<StackOp> ops) throws TestFailureException {
        try {
            Stack s = new Stack();
            int result;
            for (StackOp op : ops) {
                switch (op.op) {
                    case "Stack":
                        s = new Stack();
                        break;
                    case "push":
                        s.push(op.arg);
                        break;
                    case "pop":
                        result = s.pop();
                        if (result != op.arg) {
                            throw new TestFailureException("Pop: expected " +
                                    String.valueOf(op.arg) + ", got " +
                                    String.valueOf(result));
                        }
                        break;
                    case "max":
                        result = s.max();
                        if (result != op.arg) {
                            throw new TestFailureException("Max: expected " +
                                    String.valueOf(op.arg) + ", got " +
                                    String.valueOf(result));
                        }
                        break;
                    case "empty":
                        result = s.empty() ? 1 : 0;
                        if (result != op.arg) {
                            throw new TestFailureException("Empty: expected " +
                                    String.valueOf(op.arg) + ", got " +
                                    String.valueOf(s));
                        }
                        break;
                    default:
                        throw new RuntimeException("Unsupported stack operation: " + op.op);
                }
            }
        } catch (NoSuchElementException e) {
            throw new TestFailureException("Unexpected NoSuchElement exception");
        }
    }

    public static void main(String[] args) {
        GenericTestHandler.executeTestsByAnnotation(
                new Object() {
                }.getClass().getEnclosingClass(), args);
    }
}

package epi.stack_queue;

import epi.test_framework.EpiTest;
import epi.test_framework.EpiUserType;
import epi.test_framework.GenericTestHandler;
import epi.test_framework.TestFailureException;

import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;

public class QueueFromStacks {

    public static class Queue {

        Deque<Integer> stack = new LinkedList<>(),
        temp = new LinkedList<>();

        public void enqueue(Integer x) {
            stack.push(x);
        }

        public Integer dequeue() {
            while (!stack.isEmpty()) {
                temp.push(stack.pop());
            }
            Integer res = temp.pop();
            while (!temp.isEmpty()) {
                stack.push(temp.pop());
            }
            return res;
        }
    }

    @EpiUserType(ctorParams = {String.class, int.class})
    public static class QueueOp {
        public String op;
        public int arg;

        public QueueOp(String op, int arg) {
            this.op = op;
            this.arg = arg;
        }
    }

    @EpiTest(testfile = "queue_from_stacks.tsv")
    public static void queueTest(List<QueueOp> ops) throws TestFailureException {
        try {
            Queue q = new Queue();

            for (QueueOp op : ops) {
                switch (op.op) {
                    case "QueueWithMax":
                        q = new Queue();
                        break;
                    case "enqueue":
                        q.enqueue(op.arg);
                        break;
                    case "dequeue":
                        int result = q.dequeue();
                        if (result != op.arg) {
                            throw new TestFailureException("Dequeue: expected " +
                                    String.valueOf(op.arg) + ", got " +
                                    String.valueOf(result));
                        }
                        break;
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

package epi.stack_queue;

import epi.test_framework.EpiTest;
import epi.test_framework.EpiUserType;
import epi.test_framework.GenericTestHandler;
import epi.test_framework.TestFailureException;

import java.util.*;

public class QueueWithMax {

    private Queue<Integer> queue = new LinkedList<>();
    private Deque<Integer> deque = new LinkedList<>();

    public void enqueue(Integer x) {
        queue.add(x);
        while (!deque.isEmpty() && deque.peekLast() < x) {
            deque.pollLast();
        }
        deque.addLast(x);
    }

    public Integer dequeue() {
        if (queue.peek().equals(deque.peekFirst())) {
            deque.pollFirst();
        }
        return queue.poll();
    }

    public Integer max() {
        return deque.peekFirst();
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

    @EpiTest(testfile = "queue_with_max.tsv")
    public static void queueTest(List<QueueOp> ops) throws TestFailureException {
        try {
            QueueWithMax q = new QueueWithMax();

            for (QueueOp op : ops) {
                switch (op.op) {
                    case "QueueWithMax":
                        q = new QueueWithMax();
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
                    case "max":
                        int s = q.max();
                        if (s != op.arg) {
                            throw new TestFailureException("Max: expected " +
                                    String.valueOf(op.arg) + ", got " +
                                    String.valueOf(s));
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

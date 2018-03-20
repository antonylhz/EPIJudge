package epi.linked_list;

import epi.ListNode;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTestHandler;
import epi.test_framework.TestFailureException;
import epi.test_framework.TestTimer;

public class DoTerminatedListsOverlap {

    public static ListNode<Integer>
    overlappingNoCycleLists(ListNode<Integer> l0, ListNode<Integer> l1) {
        int s0 = length(l0), s1 = length(l1), diff = Math.abs(s0 - s1);
        return s0 >= s1 ? overlappingNoCycleLists(l0, l1, diff) : overlappingNoCycleLists(l1, l0, diff);
    }

    private static int length(ListNode<Integer> list) {
        ListNode<Integer> node = list;
        int size = 0;
        while (node != null) {
            size++;
            node = node.next;
        }
        return size;
    }

    private static ListNode<Integer> advance(ListNode<Integer> node, int steps) {
        for (int i = 0; i < steps; i++) {
            if (node.next == null) {
                return null;
            }
            node = node.next;
        }
        return node;
    }

    private static ListNode<Integer> overlappingNoCycleLists(ListNode<Integer> longList,
                                                             ListNode<Integer> shortList, int diff) {
        ListNode<Integer> ln = longList, sn = shortList;
        ln = advance(ln, diff);
        do {
            if (ln == null || sn == null) {
                return null;
            } else if (ln == sn) {
                return ln;
            } else {
                ln = ln.next;
                sn = sn.next;
            }
        } while (true);
    }

    @EpiTest(testfile = "do_terminated_lists_overlap.tsv")
    public static void
    overlappingNoCycleListsWrapper(TestTimer timer, ListNode<Integer> l0,
                                   ListNode<Integer> l1, ListNode<Integer> common)
            throws TestFailureException {
        if (common != null) {
            if (l0 != null) {
                ListNode<Integer> i = l0;
                while (i.next != null) {
                    i = i.next;
                }
                i.next = common;
            } else {
                l0 = common;
            }

            if (l1 != null) {
                ListNode<Integer> i = l1;
                while (i.next != null) {
                    i = i.next;
                }
                i.next = common;
            } else {
                l1 = common;
            }
        }

        timer.start();
        ListNode<Integer> result = overlappingNoCycleLists(l0, l1);
        timer.stop();

        if (result != common) {
            throw new TestFailureException("Invalid result");
        }
    }

    public static void main(String[] args) {
        GenericTestHandler.executeTestsByAnnotation(
                new Object() {
                }.getClass().getEnclosingClass(), args);
    }
}

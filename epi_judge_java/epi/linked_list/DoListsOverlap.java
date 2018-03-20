package epi.linked_list;

import epi.ListNode;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTestHandler;
import epi.test_framework.TestFailureException;
import epi.test_framework.TestTimer;

import java.util.HashSet;
import java.util.Set;

public class DoListsOverlap {

  public static ListNode<Integer> overlappingLists(ListNode<Integer> l0,
                                                   ListNode<Integer> l1) {
      int s0 = length(l0), s1 = length(l1), diff = Math.abs(s0 - s1);
      return s0 >= s1 ? overlappingLists(l0, l1, diff) : overlappingLists(l1, l0, diff);
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

  private static ListNode<Integer> overlappingLists(ListNode<Integer> longList,
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

  @EpiTest(testfile = "do_lists_overlap.tsv")
  public static void
  overlappingListsWrapper(TestTimer timer, ListNode<Integer> l0,
                          ListNode<Integer> l1, ListNode<Integer> common,
                          int cycle0, int cycle1) throws TestFailureException {
    if (common != null) {
      if (l0 == null) {
        l0 = common;
      } else {
        ListNode<Integer> it = l0;
        while (it.next != null) {
          it = it.next;
        }
        it.next = common;
      }

      if (l1 == null) {
        l1 = common;
      } else {
        ListNode<Integer> it = l1;
        while (it.next != null) {
          it = it.next;
        }
        it.next = common;
      }
    }

    if (cycle0 != -1 && l0 != null) {
      ListNode<Integer> last = l0;
      while (last.next != null) {
        last = last.next;
      }
      ListNode<Integer> it = l0;
      while (cycle0-- > 0) {
        if (it == null) {
          throw new RuntimeException("Invalid input data");
        }
        it = it.next;
      }
      last.next = it;
    }

    if (cycle1 != -1 && l1 != null) {
      ListNode<Integer> last = l1;
      while (last.next != null) {
        last = last.next;
      }
      ListNode<Integer> it = l1;
      while (cycle1-- > 0) {
        if (it == null) {
          throw new RuntimeException("Invalid input data");
        }
        it = it.next;
      }
      last.next = it;
    }

    Set<Integer> commonNodes = new HashSet<>();
    ListNode<Integer> it = common;
    while (it != null && !commonNodes.contains(it.data)) {
      commonNodes.add(it.data);
      it = it.next;
    }

    timer.start();
    ListNode<Integer> result = overlappingLists(l0, l1);
    timer.stop();

    if (!((commonNodes.isEmpty() && result == null) ||
          (result != null && commonNodes.contains(result.data)))) {
      throw new TestFailureException("Invalid result");
    }
  }

  public static void main(String[] args) {
    GenericTestHandler.executeTestsByAnnotation(
        new Object() {}.getClass().getEnclosingClass(), args);
  }
}

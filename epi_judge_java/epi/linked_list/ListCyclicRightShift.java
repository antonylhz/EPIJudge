package epi.linked_list;

import epi.ListNode;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTestHandler;

public class ListCyclicRightShift {
  @EpiTest(testfile = "list_cyclic_right_shift.tsv")

  public static ListNode<Integer> cyclicallyRightShiftList(
          ListNode<Integer> L, int k) {
      if (L == null || k == 0) return L;
      k = k % length(L);
      ListNode<Integer> prev = new ListNode<>(0, L), tail = advance(prev, k), res;
      while (tail.next!= null) {
          prev = prev.next;
          tail = tail.next;
      }
      tail.next = L;
      res = prev.next;
      prev.next = null;
      return res;
  }

  private static int length(ListNode<Integer> list) {
      int size = 0;
      while (list != null) {
          size++;
          list = list.next;
      }
      return size;
  }

  private static ListNode<Integer> advance(ListNode<Integer> node, int steps) {
      for (int i = 0; i < steps; i++) {
          if (node == null) {
              return null;
          }
          node = node.next;
      }
      return node;
  }

  public static void main(String[] args) {
    GenericTestHandler.executeTestsByAnnotation(
        new Object() {}.getClass().getEnclosingClass(), args);
  }
}

package epi.linked_list;

import epi.ListNode;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTestHandler;

public class DeleteKthLastFromList {
  @EpiTest(testfile = "delete_kth_last_from_list.tsv")

  // Assumes L has at least k nodes, deletes the k-th last node in L.
  public static ListNode<Integer> removeKthLast(ListNode<Integer> L, int k) {
      ListNode<Integer> dum = new ListNode<>(0, L), n1 = dum, n2 = dum;
      for (int i = 0; i < k; i++) {
          n2 = n2.next;
      }
      while (n2 != null && n2.next != null) {
          n1 = n1.next;
          n2 = n2.next;
      }
      n1.next = n1.next.next;
      return dum.next;
  }

  public static void main(String[] args) {
    GenericTestHandler.executeTestsByAnnotation(
        new Object() {}.getClass().getEnclosingClass(), args);
  }
}

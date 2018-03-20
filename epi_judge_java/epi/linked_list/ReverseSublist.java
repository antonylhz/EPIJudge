package epi.linked_list;

import epi.ListNode;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTestHandler;

public class ReverseSublist {
  @EpiTest(testfile = "reverse_sublist.tsv")

  public static ListNode<Integer> reverseSublist(ListNode<Integer> L, int start,
                                                 int finish) {
      ListNode<Integer> cur = L, head = null, tail;
      int i = 1;
      while (i < start && cur.next != null) {
          head = cur;
          cur = cur.next;
          i++;
      }
      tail = cur;
      ListNode<Integer> next = cur.next;
      while (i <= finish && next != null) {
          ListNode<Integer> nnext = next.next;
          next.next = cur;
          cur = next;
          next = nnext;
      }
      head.next = cur;
      tail.next = next;
      return L;
  }

  public static void main(String[] args) {
    GenericTestHandler.executeTestsByAnnotation(
        new Object() {}.getClass().getEnclosingClass(), args);
  }
}

package epi.linked_list;

import epi.ListNode;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTestHandler;

public class SortedListsMerge {
  @EpiTest(testfile = "sorted_lists_merge.tsv")
  //@include
  public static ListNode<Integer> mergeTwoSortedLists(ListNode<Integer> n1,
                                                      ListNode<Integer> n2) {
      ListNode<Integer> dum = new ListNode<Integer>(0, null),
              cur = dum;
      while (n1 != null && n2 != null) {
          if (n1.data <= n2.data) {
              cur.next= n1;
              n1 = n1.next;
          } else {
              cur.next = n2;
              n2 = n2.next;
          }
          cur = cur.next;
      }
      cur.next = n1 == null ? n2 : n1;
      return dum.next;
  }

  public static void main(String[] args) {
    GenericTestHandler.executeTestsByAnnotation(
        new Object() {}.getClass().getEnclosingClass(), args);
  }
}

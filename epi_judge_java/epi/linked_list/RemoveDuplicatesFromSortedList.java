package epi.linked_list;

import epi.ListNode;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTestHandler;

public class RemoveDuplicatesFromSortedList {
  @EpiTest(testfile = "remove_duplicates_from_sorted_list.tsv")

  public static ListNode<Integer> removeDuplicates(ListNode<Integer> L) {
      ListNode<Integer> dum = new ListNode<>(0, L), prev = dum, cur = dum.next, next;
      while (cur != null && (next = cur.next) != null) {
          if (cur.data.equals(next.data)) {
              prev.next = next;
          }
          prev = prev.next;
          cur = prev.next;
      }
      return dum.next;
  }

  public static void main(String[] args) {
    GenericTestHandler.executeTestsByAnnotation(
        new Object() {}.getClass().getEnclosingClass(), args);
  }
}

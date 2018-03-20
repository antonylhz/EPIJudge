package epi.linked_list;

import epi.ListNode;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTestHandler;

public class EvenOddListMerge {
  @EpiTest(testfile = "even_odd_list_merge.tsv")

  public static ListNode<Integer> evenOddMerge(ListNode<Integer> list) {
      if (list == null || list.next == null) return list;
      ListNode<Integer> odd = new ListNode<>(0, null), oddTail = odd, itr = list, evenTail = list;
      while (itr != null) {
          evenTail = itr;
          if (itr.next != null) {
              oddTail.next = itr.next;
              oddTail = oddTail.next;
              itr.next = itr.next.next;
              itr = itr.next;
          } else break;
      }
      evenTail.next = odd.next;
      oddTail.next = null;
      return list;
  }

  public static void main(String[] args) {
    GenericTestHandler.executeTestsByAnnotation(
        new Object() {}.getClass().getEnclosingClass(), args);
  }
}

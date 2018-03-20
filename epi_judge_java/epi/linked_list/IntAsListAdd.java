package epi.linked_list;

import epi.ListNode;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTestHandler;

public class IntAsListAdd {
  @EpiTest(testfile = "int_as_list_add.tsv")

  public static ListNode<Integer> addTwoNumbers(ListNode<Integer> L1,
                                                ListNode<Integer> L2) {
      int co = 0;
      ListNode<Integer> dum = new ListNode<>(0, null), itr = dum, itr1 = L1, itr2 = L2;
      while (itr1 != null || itr2 != null || co > 0) {
          int sum = co;
          if (itr1 != null) {
              sum += itr1.data;
              itr1 = itr1.next;
          }
          if (itr2 != null) {
              sum += itr2.data;
              itr2 = itr2.next;
          }
          itr.next = new ListNode<>(sum % 10, null);
          itr = itr.next;
          co = sum / 10;
      }
      return dum.next;
  }

  public static void main(String[] args) {
    GenericTestHandler.executeTestsByAnnotation(
        new Object() {}.getClass().getEnclosingClass(), args);
  }
}

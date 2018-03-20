package epi.linked_list;

import epi.ListNode;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTestHandler;

public class IsListPalindromic {
  @EpiTest(testfile = "is_list_palindromic.tsv")

  public static boolean isLinkedListAPalindrome(ListNode<Integer> list) {
      if (list == null || list.next == null) {
          return true;
      }
      ListNode<Integer> slow = list, fast = list;
      do {
          slow = slow.next;
          fast = fast.next.next;
      } while (fast != null && fast.next != null);
      ListNode<Integer> right = reverse(slow.next), left = list;
      while (right != null) {
          if (!left.data.equals(right.data)) {
              return false;
          }
          left = left.next;
          right = right.next;
      }
      return true;
  }

  private static ListNode<Integer> reverse(ListNode<Integer> list) {
      if (list == null || list.next == null) {
          return list;
      }
      ListNode<Integer> cur = list, next = cur.next;
      cur.next = null;
      while (next != null) {
          ListNode<Integer> nnext = next.next;
          next.next = cur;
          cur = next;
          next = nnext;
      }
      return cur;
  }


  public static void main(String[] args) {
    GenericTestHandler.executeTestsByAnnotation(
        new Object() {}.getClass().getEnclosingClass(), args);
  }
}

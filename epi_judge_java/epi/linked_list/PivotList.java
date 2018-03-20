package epi.linked_list;

import epi.ListNode;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTestHandler;
import epi.test_framework.TestFailureException;
import epi.test_framework.TestTimer;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PivotList {

  public static ListNode<Integer> listPivoting(ListNode<Integer> l, int x) {
      ListNode<Integer> larger = new ListNode<>(0, null), ltail = larger,
      dum = new ListNode<>(0, l), itr = dum;
      while (itr.next != null) {
          if (itr.next.data < x) {
              itr = itr.next;
          } else if (itr.next.data == x) {
              ListNode<Integer> target = itr.next;
              itr.next = target.next;
              target.next = larger.next;
              larger.next = target;
              if (ltail == larger) ltail = target;
          } else {
              ListNode<Integer> target = itr.next;
              itr.next = target.next;
              ltail.next = target;
              ltail = target;
              target.next = null;
          }
      }
      itr.next = larger.next;
      return dum.next;
  }

  public static List<Integer> linkedToList(ListNode<Integer> l) {
    List<Integer> v = new ArrayList<>();
    while (l != null) {
      v.add(l.data);
      l = l.next;
    }
    return v;
  }

  @EpiTest(testfile = "pivot_list.tsv")
  public static void listPivotingWrapper(TestTimer timer, ListNode<Integer> l,
                                         int x) throws TestFailureException {
    List<Integer> original = linkedToList(l);

    timer.start();
    l = listPivoting(l, x);
    timer.stop();

    List<Integer> pivoted = linkedToList(l);

    int mode = -1;
    for (Integer i : pivoted) {
      switch (mode) {
      case -1:
        if (i == x) {
          mode = 0;
        } else if (i > x) {
          mode = 1;
        }
        break;
      case 0:
        if (i < x) {
          throw new TestFailureException("List is not pivoted");
        } else if (i > x) {
          mode = 1;
        }
        break;
      case 1:
        if (i <= x) {
          throw new TestFailureException("List is not pivoted");
        }
      }
    }

    Collections.sort(original);
    Collections.sort(pivoted);
    if (!original.equals(pivoted))
      throw new TestFailureException("Result list contains different values");
  }

  public static void main(String[] args) {
    GenericTestHandler.executeTestsByAnnotation(
        new Object() {}.getClass().getEnclosingClass(), args);
  }
}

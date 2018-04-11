package epi.bst;

import epi.BstNode;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTestHandler;

public class SearchFirstGreaterValueInBst {

    public static BstNode<Integer> findFirstGreaterThanK(BstNode<Integer> tree,
                                                         Integer k) {
        if (tree == null) return null;
        BstNode<Integer> cur = tree, last = null;
        while (cur != null) {
            if (cur.data <= k) {
                cur = cur.right;
            } else {
                last = cur;
                cur = cur.left;
            }
        }
        return last;
    }

    @EpiTest(testfile = "search_first_greater_value_in_bst.tsv")
    public static int findFirstGreaterThanKWrapper(BstNode<Integer> tree,
                                                   Integer k) {
        BstNode<Integer> result = findFirstGreaterThanK(tree, k);
        return result != null ? result.data : -1;
    }

    public static void main(String[] args) {
        GenericTestHandler.executeTestsByAnnotation(
                new Object() {
                }.getClass().getEnclosingClass(), args);
    }
}

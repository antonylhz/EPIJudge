package epi.binary_tree;

import epi.BinaryTree;
import epi.test_framework.BinaryTreeUtils;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTestHandler;
import epi.test_framework.TestFailureException;
import epi.test_framework.TestTimer;

import java.util.HashSet;
import java.util.Set;

public class LowestCommonAncestorWithParent {

    public static BinaryTree<Integer> LCA(BinaryTree<Integer> node0,
                                          BinaryTree<Integer> node1) {
        Set<BinaryTree<Integer>> ancestors = new HashSet<>();
        while (node0 != null) {
            ancestors.add(node0);
            node0 = node0.parent;
        }
        while (node1 != null) {
            if (ancestors.contains(node1)) {
                return node1;
            }
            node1 = node1.parent;
        }
        return null;
    }

    @EpiTest(testfile = "lowest_common_ancestor.tsv")
    public static int lcaWrapper(TestTimer timer, BinaryTree<Integer> tree,
                                 Integer node0, int node1)
            throws TestFailureException {
        timer.start();
        BinaryTree<Integer> result = LCA(BinaryTreeUtils.mustFindNode(tree, node0),
                BinaryTreeUtils.mustFindNode(tree, node1));
        timer.stop();

        if (result == null) {
            throw new TestFailureException("Result can not be null");
        }
        return result.data;
    }

    public static void main(String[] args) {
        GenericTestHandler.executeTestsByAnnotation(
                new Object() {
                }.getClass().getEnclosingClass(), args);
    }
}

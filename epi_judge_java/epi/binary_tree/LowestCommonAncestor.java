package epi.binary_tree;

import epi.BinaryTreeNode;
import epi.test_framework.BinaryTreeUtils;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTestHandler;
import epi.test_framework.TestFailureException;
import epi.test_framework.TestTimer;

public class LowestCommonAncestor {

    static BinaryTreeNode<Integer> lca;

    public static BinaryTreeNode<Integer> LCA(BinaryTreeNode<Integer> tree,
                                              BinaryTreeNode<Integer> node0,
                                              BinaryTreeNode<Integer> node1) {
        lca = null;
        find(tree,node0, node1);
        return lca;
    }

    private static int find(BinaryTreeNode<Integer> tree,
                                       BinaryTreeNode<Integer> node0,
                                       BinaryTreeNode<Integer> node1) {
        if (tree == null) return 0;
        int left = find(tree.left, node0, node1);
        if (left == 2) return 2;
        int right = find(tree.right, node0, node1);
        if (right == 2) return 2;
        int found = left + right + (tree == node0 ? 1 : 0)  + (tree == node1 ? 1 : 0);
        if (found == 2) lca = tree;
        return found;
    }

    @EpiTest(testfile = "lowest_common_ancestor.tsv")
    public static int lcaWrapper(TestTimer timer, BinaryTreeNode<Integer> tree,
                                 Integer node0, int node1)
            throws TestFailureException {
        timer.start();
        BinaryTreeNode<Integer> result =
                LCA(tree, BinaryTreeUtils.mustFindNode(tree, node0),
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

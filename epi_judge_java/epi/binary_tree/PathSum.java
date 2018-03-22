package epi.binary_tree;

import epi.BinaryTreeNode;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTestHandler;

public class PathSum {
    @EpiTest(testfile = "path_sum.tsv")

    public static boolean hasPathSum(BinaryTreeNode<Integer> tree,
                                     int remainingWeight) {
        if (tree == null) return remainingWeight == 0;
        if (tree.left == null && tree.right == null) return remainingWeight == tree.data;
        remainingWeight -= tree.data;
        if (tree.left != null && hasPathSum(tree.left, remainingWeight)) return true;
        if (tree.right != null && hasPathSum(tree.right, remainingWeight)) return true;
        return false;
    }

    public static void main(String[] args) {
        GenericTestHandler.executeTestsByAnnotation(
                new Object() {
                }.getClass().getEnclosingClass(), args);
    }
}

package epi;

import epi.test_framework.EpiTest;
import epi.test_framework.GenericTestHandler;

public class IsTreeSymmetric {
    @EpiTest(testfile = "is_tree_symmetric.tsv")

    public static boolean isSymmetric(BinaryTreeNode<Integer> tree) {
        return tree == null || isSymmetric(tree.left, tree.right);
    }

    private static boolean isSymmetric(BinaryTreeNode<Integer> left, BinaryTreeNode<Integer> right) {
        if (left == null && right == null) return true;
        else if (left == null || right == null) return false;
        return left.data.equals(right.data) && isSymmetric(left.left, right.right) && isSymmetric(left.right, right.left);
    }

    public static void main(String[] args) {
        GenericTestHandler.executeTestsByAnnotation(
                new Object() {
                }.getClass().getEnclosingClass(), args);
    }
}

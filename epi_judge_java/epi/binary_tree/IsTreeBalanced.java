package epi.binary_tree;

import epi.BinaryTreeNode;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTestHandler;

public class IsTreeBalanced {

    @EpiTest(testfile = "is_tree_balanced.tsv")

    public static boolean isBalanced(BinaryTreeNode<Integer> tree) {
        return getBalancedStatus(tree).balanced;
    }

    private static Status getBalancedStatus(BinaryTreeNode<Integer> root) {
        if (root == null) return new Status(-1, true);
        Status lStatus = getBalancedStatus(root.left);
        Status rStatus = getBalancedStatus(root.right);
        int height = Math.max(lStatus.height, rStatus.height) + 1;
        boolean balanced = lStatus.balanced && rStatus.balanced && Math.abs(lStatus.height - rStatus.height) <= 1;
        return new Status(height, balanced);
    }


    public static void main(String[] args) {
        GenericTestHandler.executeTestsByAnnotation(
                new Object() {
                }.getClass().getEnclosingClass(), args);
    }

    static class Status {
        private int height;
        private boolean balanced;

        public Status(int height, boolean balanced) {
            this.height = height;
            this.balanced = balanced;
        }
    }
}

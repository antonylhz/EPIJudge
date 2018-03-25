package epi.binary_tree;

import epi.BinaryTree;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTestHandler;
import epi.test_framework.TestFailureException;
import epi.test_framework.TestTimer;

public class KthNodeInTree {
    public static class BinaryTreeNode<T> {
        public T data;
        public BinaryTreeNode<T> left, right;
        public int size;

        public BinaryTreeNode(T data, BinaryTreeNode<T> left,
                              BinaryTreeNode<T> right, int size) {
            this.data = data;
            this.left = left;
            this.right = right;
            this.size = size;
        }
    }

    public static BinaryTreeNode<Integer>
    findKthNodeBinaryTree(BinaryTreeNode<Integer> tree, int k) {
        if (tree == null || k > tree.size) {
            return null;
        }
        int lsize = tree.left == null ? 0 : tree.left.size;
        if (k <= lsize) {
            return findKthNodeBinaryTree(tree.left, k);
        } else if (k == lsize + 1) {
            return tree;
        } else {
            return findKthNodeBinaryTree(tree.right, k - lsize - 1);
        }
    }

    public static BinaryTreeNode<Integer>
    convertToTreeWithSize(BinaryTree<Integer> original) {
        if (original == null)
            return null;
        BinaryTreeNode<Integer> left = convertToTreeWithSize(original.left);
        BinaryTreeNode<Integer> right = convertToTreeWithSize(original.right);
        int lSize = left == null ? 0 : left.size;
        int rSize = right == null ? 0 : right.size;
        return new BinaryTreeNode<>(original.data, left, right, 1 + lSize + rSize);
    }

    @EpiTest(testfile = "kth_node_in_tree.tsv")
    public static int
    findKthNodeBinaryTreeWrapper(TestTimer timer, BinaryTree<Integer> tree, int k)
            throws TestFailureException {
        BinaryTreeNode<Integer> converted = convertToTreeWithSize(tree);

        timer.start();
        BinaryTreeNode<Integer> result = findKthNodeBinaryTree(converted, k);
        timer.stop();

        if (result == null) {
            throw new TestFailureException("Result can't be null");
        }
        return result.data;
    }

    public static void main(String[] args) {
        GenericTestHandler.executeTestsByAnnotation(
                new Object() {
                }.getClass().getEnclosingClass(), args);
    }
}

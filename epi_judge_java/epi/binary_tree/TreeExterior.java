package epi.binary_tree;

import epi.BinaryTreeNode;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTestHandler;
import epi.test_framework.TestFailureException;
import epi.test_framework.TestTimer;

import java.util.ArrayList;
import java.util.List;

public class TreeExterior {

    public static List<BinaryTreeNode<Integer>>
    exteriorBinaryTree(BinaryTreeNode<Integer> tree) {
        List<BinaryTreeNode<Integer>> exterior = new ArrayList<>();
        if (tree != null) {
            exterior.add(tree);
            exterior.addAll(preorder(tree.left, true));
            exterior.addAll(postorder(tree.right, true));
        }
        return exterior;
    }

    private static List<BinaryTreeNode<Integer>>
    preorder(BinaryTreeNode<Integer> root, boolean isBoundary) {
        List<BinaryTreeNode<Integer>> res = new ArrayList<>();
        if (root != null) {
            if ((root.left == null && root.right == null) || isBoundary) {
                res.add(root);
            }
            res.addAll(preorder(root.left, isBoundary));
            res.addAll(preorder(root.right, isBoundary && root.left == null));
        }
        return res;
    }

    private static List<BinaryTreeNode<Integer>>
    postorder(BinaryTreeNode<Integer> root, boolean isBoundary) {
        List<BinaryTreeNode<Integer>> res = new ArrayList<>();
        if (root != null) {
            res.addAll(postorder(root.left, isBoundary && root.right == null));
            res.addAll(postorder(root.right, isBoundary));
            if ((root.left == null && root.right == null) || isBoundary) {
                res.add(root);
            }
        }
        return res;
    }

    private static List<Integer> createOutputList(List<BinaryTreeNode<Integer>> L)
            throws TestFailureException {
        if (L.contains(null)) {
            throw new TestFailureException("Resulting list contains null");
        }
        List<Integer> output = new ArrayList<>();
        for (BinaryTreeNode<Integer> l : L) {
            output.add(l.data);
        }
        return output;
    }

    @EpiTest(testfile = "tree_exterior.tsv")
    public static List<Integer>
    exteriorBinaryTreeWrapper(TestTimer timer, BinaryTreeNode<Integer> tree)
            throws TestFailureException {
        timer.start();
        List<BinaryTreeNode<Integer>> l = exteriorBinaryTree(tree);
        timer.stop();
        return createOutputList(l);
    }

    public static void main(String[] args) {
        GenericTestHandler.executeTestsByAnnotation(
                new Object() {
                }.getClass().getEnclosingClass(), args);
    }
}

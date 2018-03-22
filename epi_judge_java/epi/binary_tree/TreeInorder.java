package epi.binary_tree;

import epi.BinaryTreeNode;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTestHandler;

import java.util.ArrayList;
import java.util.List;

public class TreeInorder {
    @EpiTest(testfile = "tree_inorder.tsv")

    public static List<Integer> inorderTraversal(BinaryTreeNode<Integer> tree) {
        List<Integer> res = new ArrayList<>();
        if (tree.left != null) {
            res.addAll(inorderTraversal(tree.left));
        }
        res.add(tree.data);
        if (tree.right != null) {
            res.addAll(inorderTraversal(tree.right));
        }
        return res;
    }

    public static void main(String[] args) {
        GenericTestHandler.executeTestsByAnnotation(
                new Object() {
                }.getClass().getEnclosingClass(), args);
    }
}

package epi.binary_tree;

import epi.BinaryTree;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTestHandler;

import java.util.ArrayList;
import java.util.List;

public class TreeWithParentInorder {
    @EpiTest(testfile = "tree_with_parent_inorder.tsv")

    public static List<Integer> inorderTraversal(BinaryTree<Integer> tree) {
        List<Integer> res = new ArrayList<>();
        BinaryTree<Integer> node = tree;
        while (node != null && node.left != null) {
            node = node.left;
        }
        while (node != null) {
            res.add(node.data);
            if (node.right != null) {
                BinaryTree<Integer> itr = node.right;
                while (itr.left != null) {
                    itr = itr.left;
                }
                node = itr;
            } else {
                BinaryTree<Integer> itr = node;
                while (itr.parent != null && itr.parent.left != itr) {
                    itr = itr.parent;
                }
                node = itr.parent;
            }
        }
        return res;
    }

    public static void main(String[] args) {
        GenericTestHandler.executeTestsByAnnotation(
                new Object() {
                }.getClass().getEnclosingClass(), args);
    }
}

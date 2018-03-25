package epi.binary_tree;

import epi.BinaryTreeNode;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTestHandler;

import java.util.List;

public class TreeFromPreorderInorder {
    @EpiTest(testfile = "tree_from_preorder_inorder.tsv")

    public static BinaryTreeNode<Integer>
    binaryTreeFromPreorderInorder(List<Integer> preorder, List<Integer> inorder) {
        if (preorder == null || inorder == null || preorder.isEmpty() || inorder.isEmpty() || preorder.size() != inorder.size()) {
            return null;
        }
        BinaryTreeNode<Integer> root = new BinaryTreeNode<>(preorder.get(0));
        int i = 0;
        while (i < inorder.size() && !inorder.get(i).equals(root.data)) {
            i++;
        }
        root.left = binaryTreeFromPreorderInorder(preorder.subList(1, i + 1), inorder.subList(0, i));
        root.right = binaryTreeFromPreorderInorder(preorder.subList(i + 1, preorder.size()), inorder.subList(i + 1, inorder.size()));
        return root;
    }

    public static void main(String[] args) {
        GenericTestHandler.executeTestsByAnnotation(
                new Object() {
                }.getClass().getEnclosingClass(), args);
    }
}

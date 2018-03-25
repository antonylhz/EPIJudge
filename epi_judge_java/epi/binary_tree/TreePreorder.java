package epi.binary_tree;

import epi.BinaryTreeNode;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTestHandler;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

public class TreePreorder {
    @EpiTest(testfile = "tree_preorder.tsv")
    public static List<Integer> preorderTraversal(BinaryTreeNode<Integer> tree) {
        List<Integer> list = new ArrayList<>();
        Deque<BinaryTreeNode<Integer>> stack = new LinkedList<>();
        if (tree != null) {
            stack.push(tree);
        }
        while (!stack.isEmpty()) {
            BinaryTreeNode<Integer> node = stack.pop();
            list.add(node.data);
            if (node.right != null) {
                stack.push(node.right);
            }
            if (node.left != null) {
                stack.push(node.left);
            }
        }
        return list;
    }

    public static void main(String[] args) {
        GenericTestHandler.executeTestsByAnnotation(
                new Object() {
                }.getClass().getEnclosingClass(), args);
    }
}

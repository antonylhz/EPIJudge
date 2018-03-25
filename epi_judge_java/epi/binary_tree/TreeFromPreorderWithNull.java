package epi.binary_tree;

import epi.BinaryTreeNode;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTestHandler;
import epi.test_framework.TestTimer;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

public class TreeFromPreorderWithNull {

    public static BinaryTreeNode<Integer>
    reconstructPreorder(List<Integer> preorder) {
        if (preorder == null || preorder.isEmpty()) return  null;
        Deque<BinaryTreeNode<Integer>> nodes = new LinkedList<>();
        Deque<Boolean> done = new LinkedList<>();
        nodes.push(new BinaryTreeNode<>(preorder.get(0)));
        done.push(false);
        for (int i = 1; i < preorder.size(); i++) {
            Integer data = preorder.get(i);
            if (data != null) {
                nodes.push(new BinaryTreeNode<>(data));
                done.push(false);
            } else {
                BinaryTreeNode<Integer> node = null;
                while (!done.isEmpty() && done.peek()) {
                    BinaryTreeNode<Integer> left = nodes.pop();
                    done.pop();
                    BinaryTreeNode<Integer> curRoot = nodes.pop();
                    done.pop();
                    curRoot.left = left; curRoot.right = node;
                    node = curRoot;
                }
                nodes.push(node);
                done.push(true);
            }
        }
        return done.peek() && nodes.size() == 1 ? nodes.peek() : null;
    }

    @EpiTest(testfile = "tree_from_preorder_with_null.tsv")
    public static BinaryTreeNode<Integer>
    reconstructPreorderWrapper(TestTimer timer, List<String> strings) {
        List<Integer> ints = new ArrayList<>();
        for (String s : strings) {
            if (s.equals("null")) {
                ints.add(null);
            } else {
                ints.add(Integer.parseInt(s));
            }
        }
        timer.start();
        BinaryTreeNode<Integer> result = reconstructPreorder(ints);
        timer.stop();
        return result;
    }

    public static void main(String[] args) {
        GenericTestHandler.executeTestsByAnnotation(
                new Object() {
                }.getClass().getEnclosingClass(), args);
    }
}

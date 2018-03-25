package epi.binary_tree;

import epi.BinaryTree;
import epi.test_framework.BinaryTreeUtils;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTestHandler;
import epi.test_framework.TestTimer;

public class SuccessorInTree {

    public static BinaryTree<Integer> findSuccessor(BinaryTree<Integer> node) {
        if (node.right != null) {
            BinaryTree<Integer> itr = node.right;
            while (itr.left != null) {
                itr = itr.left;
            }
            return itr;
        } else {
            BinaryTree<Integer> itr = node;
            while (itr.parent != null && itr.parent.left != itr) {
                itr = itr.parent;
            }
            return itr.parent;
        }
    }

    @EpiTest(testfile = "successor_in_tree.tsv")
    public static int
    findSuccessorWrapper(TestTimer timer, BinaryTree<Integer> tree, int nodeIdx) {
        BinaryTree<Integer> n = BinaryTreeUtils.mustFindNode(tree, nodeIdx);

        timer.start();
        BinaryTree<Integer> result = findSuccessor(n);
        timer.stop();

        return result == null ? -1 : result.data;
    }

    public static void main(String[] args) {
        GenericTestHandler.executeTestsByAnnotation(
                new Object() {
                }.getClass().getEnclosingClass(), args);
    }
}

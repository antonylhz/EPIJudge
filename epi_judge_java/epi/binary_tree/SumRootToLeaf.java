package epi.binary_tree;

import com.sun.istack.internal.NotNull;
import epi.BinaryTreeNode;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTestHandler;

public class SumRootToLeaf {
    @EpiTest(testfile = "sum_root_to_leaf.tsv")

    public static int sumRootToLeaf(BinaryTreeNode<Integer> tree) {
        return tree == null ? 0 : sumRootToLeaf(tree, 0);
    }

    public static int sumRootToLeaf(@NotNull BinaryTreeNode<Integer> tree, int num) {
        int nnum = (num << 1) + tree.data;
        int sum = 0;
        if (tree.left != null) {
            sum += sumRootToLeaf(tree.left, nnum);
        }
        if (tree.right != null) {
            sum += sumRootToLeaf(tree.right, nnum);
        }
        return sum == 0 ? nnum : sum;
    }

    public static void main(String[] args) {
        GenericTestHandler.executeTestsByAnnotation(
                new Object() {
                }.getClass().getEnclosingClass(), args);
    }
}

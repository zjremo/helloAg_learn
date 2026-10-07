package com.demo.helloag;

import java.util.List;

import org.junit.Test;

public class BackTraceTest {
    @Test
    public void TestPreOrder() {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);
        TreeNode target = new TreeNode(6);
        root.right.left = target;
        root.right.right = new TreeNode(7);
        List<TreeNode> res = BackTraceLearn.preOrder(root, target);
        res.forEach(node -> System.out.print(node.val + " "));
        System.out.println();
    }

    @Test
    public void TestPermutationsI() {
        int[] nums = { 1, 2, 3 };
        List<List<Integer>> res = BackTraceLearn.permutationsI(nums);
        res.forEach(list -> {
            list.forEach(System.out::print);
            System.out.println();
        });
    }

    @Test
    public void TestPermutationsII() {
        int[] nums = { 1, 2, 2 };
        List<List<Integer>> res = BackTraceLearn.permutationsII(nums);
        res.forEach(list -> {
            list.forEach(System.out::print);
            System.out.println();
        });
    }

    @Test
    public void TestSubsetSumI() {
        int[] nums = { 1, 2, 3, 4, 5 };
        int target = 5;
        List<List<Integer>> res = BackTraceLearn.subsetSumI(nums, target);
        res.forEach(list -> {
            list.forEach(i -> System.out.print(i + " "));
            System.out.println();
        });
    }

    @Test
    public void TestSubsetSumII() {
        int[] nums = { 1, 2, 2, 3, 4, 5 };
        int target = 5;
        List<List<Integer>> res = BackTraceLearn.subSetSumII(nums, target);
        res.forEach(list -> {
            list.forEach(i -> System.out.print(i + " "));
            System.out.println();
        });
    }

    @Test
    public void TestNQueues() {
        int n = 5;
        List<List<List<String>>> res = BackTraceLearn.nQueues(n);
        res.forEach(board -> {
            board.forEach(row -> {
                row.forEach(System.out::print);
                System.out.println();
            });
            System.out.println();
        });
    }
}

package com.demo.helloag;

import java.util.Deque;
import java.util.LinkedList;

import org.junit.Test;

public class DivideLearnTest {
    @Test
    public void TestBuildTree() {
        int[] preOrder = {3, 9, 20, 15, 7};
        int[] inOrder = {9, 3, 15, 20, 7};
        TreeNode root = DivideLearn.buildTree(preOrder, inOrder);
        TreeLearn.layorTraverse(root);
    }

    @Test 
    public void TestHanota() {
        Deque<Integer> a = new LinkedList<>();
        for (int i = 6; i >= 1; --i) {
            a.push(i);
        }
        Deque<Integer> b = new LinkedList<>();
        Deque<Integer> c = new LinkedList<>();
        DivideLearn.solveHanota(a, b, c);
        System.out.println(c);
    }

    @Test 
    public void TestGetMax() {
        int[] nums = {3, 2, 1, 6, 0, 5};
        int max = DivideLearn.getMax(nums, 0, nums.length - 1);
        System.out.println(max);
    }

    @Test 
    public void TestFastPow() {
        int x = 2;
        int n = 10;
        int result = DivideLearn.fastPow(x, n);
        System.out.println(result);
    }
}

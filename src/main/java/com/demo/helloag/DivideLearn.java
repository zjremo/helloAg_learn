package com.demo.helloag;

import java.util.Deque;
import java.util.HashMap;
import java.util.Map;

public class DivideLearn {
    // 利用中序遍历数组和前序遍历数组来建立二叉树
    public static TreeNode buildTree(int[] preOrder, int[] inOrder) {
        Map<Integer, Integer> inOrderMap = new HashMap<>();
        for (int i = 0; i < inOrder.length; ++i) {
            inOrderMap.put(inOrder[i], i);
        }
        return dfs(preOrder, inOrderMap, 0, 0, preOrder.length - 1);
    }

    /*
     * ridx: the root idx for preOrder
     * l: the left idx for inOrder
     * r: the right idx for inOrder
     */
    public static TreeNode dfs(int[] preOrder, Map<Integer, Integer> inOrderMap, int ridx, int l, int r) {
        if (r < l)
            return null;
        TreeNode root = new TreeNode(preOrder[ridx]);
        assert inOrderMap.containsKey(preOrder[ridx]);
        int idx = inOrderMap.get(preOrder[ridx]);
        TreeNode left = dfs(preOrder, inOrderMap, ridx + 1, l, idx - 1);
        TreeNode right = dfs(preOrder, inOrderMap, ridx + idx - l + 1, idx + 1, r);
        root.left = left;
        root.right = right;
        return root;
    }

    // 汉诺塔问题
    public static void solveHanota(Deque<Integer> a, Deque<Integer> b, Deque<Integer> c) {
        int n = a.size();
        assert n > 0;
        dfsHanota(n, a, b, c);
    }

    public static void move(Deque<Integer> src, Deque<Integer> dst) {
        dst.push(src.pop());
    }

    public static void dfsHanota(int n, Deque<Integer> src, Deque<Integer> buffer, Deque<Integer> dst) {
        if (n == 1) {
            move(src, dst);
            return;
        }

        dfsHanota(n - 1, src, dst, buffer);
        move(src, dst);
        dfsHanota(n - 1, buffer, src, dst);
    }

    // 获取数组最大值
    public static int getMax(int[] nums) {
        return getMax(nums, 0, nums.length - 1);
    }

    public static int getMax(int[] nums, int l, int r) {
        if (l > r) {
            return Integer.MIN_VALUE;
        }

        int mid = l + ((r - l) >> 1);
        int leftMax = getMax(nums, l, mid - 1);
        int rightMax = getMax(nums, mid + 1, r);
        return Math.max(nums[mid], Math.max(leftMax, rightMax));
    }

    public static int fastPow(int x, int n) {
        if (n == 0)
            return 1;

        int half = fastPow(x, n / 2);
        if (n % 2 == 0) 
            return half * half;
        return half * half * x;
    }
}
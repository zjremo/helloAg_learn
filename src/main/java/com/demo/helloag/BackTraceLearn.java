package com.demo.helloag;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class BackTraceLearn {
    public static List<TreeNode> preOrder(TreeNode root, TreeNode target) {
        List<TreeNode> res = new ArrayList<>();
        dfs(root, target, new ArrayList<>(), res);
        return res;
    }

    private static void dfs(TreeNode node, TreeNode target, List<TreeNode> path, List<TreeNode> res) {
        // 剪枝
        if (node == null) {
            return;
        }

        // 尝试
        path.add(node);
        if (node == target) {
            res.addAll(path);
        } else {
            dfs(node.left, target, path, res);
            dfs(node.right, target, path, res);
        }

        // 回退
        path.remove(path.size() - 1);
    }

    // 全排列I: 给定一个没有重复数字的序列，返回其所有可能的全排列。
    public static List<List<Integer>> permutationsI(int[] nums) {
        boolean[] selected = new boolean[nums.length];
        List<List<Integer>> res = new ArrayList<>();
        dfsPermutationsI(res, new ArrayList<>(), selected, nums);
        return res;
    }

    private static void dfsPermutationsI(List<List<Integer>> res, List<Integer> path, boolean[] selected, int[] nums) {
        if (path.size() == nums.length) {
            res.add(new ArrayList<>(path)); // must deepcopy
            return;
        }

        for (int i = 0; i < nums.length; ++i) {
            if (selected[i])
                continue;
            selected[i] = true;
            path.add(nums[i]);
            // 尝试
            dfsPermutationsI(res, path, selected, nums);
            // 清理
            path.remove(path.size() - 1);
            selected[i] = false;
        }
    }

    // 全排列II: 给定一个包含重复数字的序列，返回其所有可能的全排列。
    public static List<List<Integer>> permutationsII(int[] nums) {
        boolean[] selected = new boolean[nums.length];
        List<List<Integer>> res = new ArrayList<>();
        dfsPermutationsII(res, new ArrayList<>(), selected, nums);
        return res;
    }

    private static void dfsPermutationsII(List<List<Integer>> res, List<Integer> path, boolean[] selected, int[] nums) {
        if (path.size() == nums.length) {
            res.add(new ArrayList<>(path)); // must deepcopy
            return;
        }

        Set<Integer> duplicateSet = new HashSet<>();
        for (int i = 0; i < nums.length; ++i) {
            if (selected[i] || duplicateSet.contains(nums[i]))
                continue;
            // 尝试
            selected[i] = true;
            duplicateSet.add(nums[i]);
            path.add(nums[i]);
            // dfs
            dfsPermutationsII(res, path, selected, nums);
            // 清理
            path.remove(path.size() - 1);
            selected[i] = false;
        }
    }

    // 子集元素和 = target 无重复元素
    public static List<List<Integer>> subsetSumI(int[] nums, int target) {
        Arrays.sort(nums);
        List<List<Integer>> res = new ArrayList<>();
        backTrackI(res, new ArrayList<>(), nums, target, 0);
        return res;
    }

    private static void backTrackI(List<List<Integer>> res, List<Integer> path, int[] nums, int target, int start) {
        if (target == 0) {
            res.add(new ArrayList<>(path));
            return;
        }

        for (int i = start; i < nums.length; ++i) {
            if (target - nums[i] < 0)
                break;

            // 尝试
            path.add(nums[i]);
            backTrackI(res, path, nums, target - nums[i], i);
            // 清理
            path.remove(path.size() - 1);
        }
    }

    // 子集元素和 = target 含有重复元素，且元素只能使用一次
    public static List<List<Integer>> subSetSumII(int[] nums, int target) {
        Arrays.sort(nums);
        List<List<Integer>> res = new ArrayList<>();
        backTrackII(res, new ArrayList<>(), nums, target, 0); 
        return res;
    }

    private static void backTrackII(List<List<Integer>> res, List<Integer> path, int[] nums, int target, int start) {
        if (target == 0) {
            res.add(new ArrayList<>(path));
            return;
        }

        for (int i = start; i < nums.length; ++i) {
            if (target - nums[i] < 0)
                break;

            if (i > start && nums[i] == nums[i - 1])
                continue;

            path.add(nums[i]);
            backTrackII(res, path, nums, target - nums[i], i + 1);
            path.remove(path.size() - 1);
        }
    }

    public static List<List<List<String>>> nQueues(int n) {
        // 1. init board
        List<List<String>> board = new ArrayList<>();
        for (int i = 0; i < n; ++i) {
            List<String> row = new ArrayList<>();
            for (int j = 0; j < n; ++j) 
                row.add("#");
            board.add(row);
        }

        // 2. records
        boolean[] cols = new boolean[n];
        boolean[] diags1 = new boolean[2 * n - 1]; // 主对角线上是否有元素
        boolean[] diags2 = new boolean[2 * n - 1]; // 次对角线上是否有元素, -n + 1 ~ n - 1

        // 3. backtrace
        List<List<List<String>>> res = new ArrayList<>();
        nQueuesBackTrack(board, res, 0, cols, diags1, diags2);
        return res;
    }

    private static void nQueuesBackTrack(List<List<String>> board, List<List<List<String>>> res, int row, boolean[] cols, boolean[] diags1, boolean[] diags2) {
        int n = board.size();
        if (row == n) { // 此时找到一个解
            // 拷贝board到res 
            List<List<String>> copy = new ArrayList<>();
            for (List<String> r : board) 
                copy.add(new ArrayList<>(r));
            res.add(copy);
            return;
        }

        for (int i = 0; i < n; ++i) {
            /*
                令y = col, x = row
                1. cols 不能在同一列 
                2. 不能在同一主对角线 y + x 恒定
                3. 不能在同一次对角线 y - x恒定 映射需要 + (n - 1)
             */
            if (cols[i] || diags1[row + i] || diags2[i - row + n - 1])
                continue;

            // 尝试
            board.get(row).set(i, "Q");
            cols[i] = true;
            diags1[row + i] = true;
            diags2[i - row + n - 1] = true;

            nQueuesBackTrack(board, res, row + 1, cols, diags1, diags2);
            // clean environment
            board.get(row).set(i, "#");
            cols[i] = false;
            diags1[row + i] = false;
            diags2[i - row + n - 1] = false;
        }
    }
}

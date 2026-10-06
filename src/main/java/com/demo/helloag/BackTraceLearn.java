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
}

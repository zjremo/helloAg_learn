package com.demo.helloag;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DPLearn {
    public static List<List<Integer>> climingStairs(int n) {
        List<List<Integer>> res = new ArrayList<>();
        climingStairsBacktrace(res, new ArrayList<>(), 0, 2, n);
        return res;
    }

    // 回溯法爬楼梯
    public static void climingStairsBacktrace(List<List<Integer>> res, List<Integer> path, int start, int maxStep,
            int n) {
        if (start == n) {
            res.add(new ArrayList<>(path));
            return;
        }

        for (int i = 1; i <= maxStep; ++i) { // candidates
            int cur = start + i;
            // 剪枝
            if (cur > n)
                continue;
            // try
            path.add(cur);
            climingStairsBacktrace(res, path, cur, maxStep, n);
            // clean
            path.remove(path.size() - 1);
        }
    }

    // 记忆化搜索爬楼梯
    public static int climingStairsMem(int n) {
        int[] mem = new int[n + 1];
        Arrays.fill(mem, -1);
        return dfs(n, mem);
    }

    public static int dfs(int i, int[] mem) {
        if (i == 1 || i == 2)
            return i;
        if (mem[i] != -1) {
            return mem[i]; // 此时已经之前搜索过了
        }

        int count = dfs(i - 1, mem) + dfs(i - 2, mem);
        mem[i] = count;
        return count;
    }

    // 爬楼梯无优化DP
    public static int climingStairsDP(int n) {
        if (n == 1 || n == 2)
            return n;
        int[] dp = new int[n + 1];
        dp[1] = 1;
        dp[2] = 2;

        for (int i = 3; i <= n; ++i)
            dp[i] = dp[i - 1] + dp[i - 2];

        return dp[n];
    }

    // 爬楼梯优化DP
    public static int climingStairsDPComp(int n) {
        if (n == 1 || n == 2)
            return n;

        // dp[i] = dp[i - 1] + dp[i - 2]
        int a = 1, b = 2;
        for (int i = 3; i <= n; ++i) {
            int tmp = b;
            b = a + b;
            a = tmp;
        }
        return b;
    }

    // 爬楼梯问题引入代价，DP
    // cost[i] 表示第 i 层楼梯的代价，从0开始
    public static int minClimingStairsDP(int[] cost) {
        // cost: [15, 20, 10] 第一级阶梯代价为15，以此类推
        int n = cost.length;
        if (n == 1 || n == 2)
            return cost[n - 1];

        int[] dp = new int[n];
        dp[0] = cost[0];
        dp[1] = cost[1];
        for (int i = 2; i < n; ++i) {
            dp[i] = Math.min(dp[i - 1], dp[i - 2]) + cost[i];
        }
        return dp[n - 1];
    }

    // 爬楼梯问题引入代价，优化DP
    public static int minClimingStairsDPComp(int[] cost) {
        int n = cost.length;
        if (n == 1 || n == 2)
            return cost[n - 1];

        int a = cost[0], b = cost[1];
        for (int i = 2; i < n; ++i) {
            int tmp = b;
            b = Math.min(a, b) + cost[i];
            a = tmp; // 滚动数组优化，优化空间复杂度
        }
        return b;
    }

    // 不能连续跳一步到达目标
    // dp1[i]: 跳到第i阶最后一步是一步 dp1[i] = dp2[i - 1]
    // dp2[i]: 跳到第i阶最后一步是两步 dp2[i] = dp1[i - 2] + dp2[i - 2]
    // sum = dp1[i] + dp2[i]
    public static int climbingStairsConstraintDP(int n) {
        if (n == 1 || n == 2)
            return 1;
        int[] dp1 = new int[n]; // dp1: 最后一步是一步
        int[] dp2 = new int[n]; // dp2: 最后一步是两步
        dp1[0] = 1;
        dp1[1] = 0;
        dp2[0] = 0;
        dp2[1] = 1;
        for (int i = 2; i < n; ++i) {
            dp1[i] = dp2[i - 1];
            dp2[i] = dp1[i - 2] + dp2[i - 2];
        }
        return dp1[n - 1] + dp2[n - 1];
    }

    public static int climbingStairsConstraintDP2(int n) {
        if (n == 1 || n == 2) {
            return 1;
        }
        // 初始化 dp 表，用于存储子问题的解
        int[][] dp = new int[n + 1][3];
        // 初始状态：预设最小子问题的解
        dp[1][1] = 1;
        dp[1][2] = 0;
        dp[2][1] = 0;
        dp[2][2] = 1;
        // 状态转移：从较小子问题逐步求解较大子问题
        for (int i = 3; i <= n; i++) {
            dp[i][1] = dp[i - 1][2];
            dp[i][2] = dp[i - 2][1] + dp[i - 2][2];
        }
        return dp[n][1] + dp[n][2];
    }

    // 不适合使用DP来做, DP适合无后效性的问题
    // 无后效性：给定一个确定的状态，它的未来发展只与当前状态有关，而与过去经历的所有状态无关。
    // 这个问题明显当前状态的选择过度依赖过往大量状态 无法满足无后效性
    public static int climbingStairsBarrier(int n) {
        boolean[] traverse = new boolean[2 * n + 1];
        Arrays.fill(traverse, true);
        return barrierDfs(n, traverse, 0);
    }

    /*
        1. n: 一共多少级台阶；
        2. traverse：层数是否可以遍历;
        3. layor：当前所处层级。
    */
    public static int barrierDfs(int n, boolean[] traverse, int layor) {
        if (layor == n) {
            return 1;
        }

        // candidates
        int cnt = 0;
        for (int i = 1; i <= 2; ++i) {
            int next = layor + i;
            if (next > n) // 第一次剪枝
                break;
            if (!traverse[next]) // 第二次剪枝
                continue;
            // try
            traverse[next] = false;
            traverse[next * 2] = false;
            cnt += barrierDfs(n, traverse, next);
            // clean env
            traverse[next] = true;
            traverse[next * 2] = true;
        }
        return cnt;
    }
}

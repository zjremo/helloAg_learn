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
}

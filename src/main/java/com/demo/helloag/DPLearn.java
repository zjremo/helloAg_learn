package com.demo.helloag;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import cn.hutool.core.lang.Assert;

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
     * 1. n: 一共多少级台阶；
     * 2. traverse：层数是否可以遍历;
     * 3. layor：当前所处层级。
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

    // 最小路径和
    public static int minPathSumDFS(int[][] grid) {
        int n = grid.length, m = grid[0].length;
        int[][] mem = new int[n][m];
        for (int[] row : mem) {
            Arrays.fill(row, -1);
        }
        return dfs(grid, n - 1, m - 1, n, m, mem);
    }

    public static int dfs(int[][] grid, int i, int j, int n, int m, int[][] mem) {
        if (i == 0 && j == 0) {
            return grid[0][0];
        }

        if (i < 0 || j < 0) {
            return Integer.MAX_VALUE;
        }

        if (mem[i][j] != -1) {
            return mem[i][j]; // 记忆化
        }
        int res = Math.min(dfs(grid, i - 1, j, n, m, mem), dfs(grid, i, j - 1, n, m, mem)) + grid[i][j];
        mem[i][j] = res;
        return res;
    }

    public static int minPathSumDP(int[][] grid) {
        int n = grid.length, m = grid[0].length;

        int[][] dp = new int[n][m];
        // 外围填充
        int cur = 0;
        for (int i = 0; i < m; ++i) {
            cur += grid[0][i];
            dp[0][i] = cur;
        }

        cur = 0;
        for (int i = 0; i < n; ++i) {
            cur += grid[i][0];
            dp[i][0] = cur;
        }

        for (int i = 1; i < n; ++i) {
            for (int j = 1; j < m; ++j) {
                dp[i][j] = Math.min(dp[i - 1][j], dp[i][j - 1]) + grid[i][j];
            }
        }
        return dp[n - 1][m - 1];
    }

    public static int minPathSumDPComp(int[][] grid) {
        // dp[i][j] = min(dp[i][j - 1], dp[i - 1][j]) + grid[i][j]
        // dp 表只用记录上一行的状态，然后过一行更新一行
        int n = grid.length, m = grid[0].length;
        int[] dp = new int[m];
        int cur = 0;
        for (int i = 0; i < m; ++i) {
            cur += grid[0][i];
            dp[i] = cur;
        }

        for (int i = 1; i < n; ++i) {
            dp[0] = dp[0] + grid[i][0]; // 更新当前行的第一个元素
            for (int j = 1; j < m; ++j) {
                dp[j] = Math.min(dp[j - 1], dp[j]) + grid[i][j]; // 更新当前行的其他元素
            }
        }
        return dp[m - 1];
    }

    /* 自己写的 0-1 背包 暴力搜索 */
    public static int knapsackDFS(int[] wgt, int[] val, int weight) {
        Assert.isTrue(wgt.length == val.length);
        if (weight == 0)
            return 0;

        return dfs(wgt, val, 0, 0, weight);
    }

    /* 0-1 背包：官方暴力搜索 */
    public static int knapsackDFSV2(int[] wgt, int[] val, int i, int c) {
        // 若已选完所有物品或背包无剩余容量，则返回价值 0
        if (i == 0 || c == 0) {
            return 0;
        }
        // 若超过背包容量，则只能选择不放入背包
        if (wgt[i - 1] > c) {
            return knapsackDFSV2(wgt, val, i - 1, c);
        }
        // 计算不放入和放入物品 i 的最大价值
        int no = knapsackDFSV2(wgt, val, i - 1, c);
        int yes = knapsackDFSV2(wgt, val, i - 1, c - wgt[i - 1]) + val[i - 1];
        // 返回两种方案中价值更大的那一个
        return Math.max(no, yes);
    }

    public static int dfs(int[] wgt, int[] val, int start, int value, int weight) {
        int n = wgt.length;
        if (start == n) {
            return value;
        }

        int max = value;
        for (int i = start; i < n; ++i) {
            if (weight < wgt[i])
                continue; // 剪枝
            max = Math.max(max, dfs(wgt, val, i + 1, value + val[i], weight - wgt[i]));
        }
        return max;
    }

    /*
     * 0-1 背包问题 记忆化搜索
     * 1. i 代表当前是决定第几个物品
     * 2. c 代表目前还可以允许的重量
     * 3. mem: 记忆
     * dp[i][j] = max(dp[i - 1][j], dp[i - 1][j - capacity] + value)
     */
    public static int knapsackDFSMem(int[] wgt, int[] val, int[][] mem, int i, int c) {
        if (i == 0 || c == 0)
            return 0;

        if (mem[i][c] != -1)
            return mem[i][c];

        // 超过背包容量
        if (wgt[i - 1] > c)
            return knapsackDFSMem(wgt, val, mem, i - 1, c);

        // 对于第i件物品的决策有两种:
        // 1. 不选；2. 选
        int no = knapsackDFSMem(wgt, val, mem, i - 1, c);
        int yes = knapsackDFSMem(wgt, val, mem, i - 1, c - wgt[i - 1]) + val[i - 1];
        mem[i][c] = Math.max(no, yes);
        return mem[i][c];
    }

    // 0-1 背包 动态规划
    public static int knapsackDP(int[] wgt, int[] val, int cap) {
        // dp[i][c] = max(dp[i - 1][c], dp[i - 1][c - wgt[i - 1]] + val[i - 1])
        int n = wgt.length;
        int[][] dp = new int[n + 1][cap + 1];

        for (int i = 1; i <= n; ++i) {
            for (int c = 1; c <= cap; ++c) {
                if (wgt[i - 1] > c) {
                    dp[i][c] = dp[i - 1][c];
                } else {
                    dp[i][c] = Math.max(dp[i - 1][c], dp[i - 1][c - wgt[i - 1]] + val[i - 1]);
                }
            }
        }

        return dp[n][cap];
    }

    // 完全背包 暴力
    public static int unboundedKnapsackDfs(int[] wgt, int[] val, int cap) {
        if (wgt.length == 0 || val.length == 0)
            return 0;
        Assert.isTrue(wgt.length == val.length);
        return unboundedDfs(wgt, val, 0, 0, cap);
    }

    /* 完全背包：动态规划 */
    public static int unboundedKnapsackDP(int[] wgt, int[] val, int cap) {
        int n = wgt.length;
        // 初始化 dp 表
        int[][] dp = new int[n + 1][cap + 1];
        // 状态转移
        for (int i = 1; i <= n; i++) {
            for (int c = 1; c <= cap; c++) {
                if (wgt[i - 1] > c) {
                    // 若超过背包容量，则不选物品 i
                    dp[i][c] = dp[i - 1][c];
                } else {
                    // 不选和选物品 i 这两种方案的较大值
                    dp[i][c] = Math.max(dp[i - 1][c], dp[i][c - wgt[i - 1]] + val[i - 1]);
                }
            }
        }
        return dp[n][cap];
    }

    // 完全背包 优化DP
    public static int unboundedKnapsackDPComp(int[] wgt, int[] val, int cap) {
        int n = wgt.length;
        int[] dp = new int[cap + 1];
        for (int i = 1; i <= n; ++i) {
            for (int c = 1; c <= cap; ++c) {
                if (wgt[i - 1] > c) {
                    dp[c] = dp[c];
                } else {
                    dp[c] = Math.max(dp[c], dp[c - wgt[i - 1]] + val[i - 1]);
                }
            }
        }
        return dp[cap];
    }

    /*
     * dfs 思想:
     * 1. start: 当前遍历到的物品索引
     * 2. value: 当前价值
     * 3. cap: 当前还可以使用的capacity
     */
    public static int unboundedDfs(int[] wgt, int[] val, int start, int value, int cap) {
        int n = wgt.length;

        int maxValue = value; // 当前位置的value
        for (int i = start; i < n; ++i) {
            if (cap < wgt[i])
                continue; // 剪枝，重量不够放
            maxValue = Math.max(maxValue, unboundedDfs(wgt, val, start, value + val[i], cap - wgt[i]));
        }
        return maxValue;
    }

    // 零钱兑换问题 Dfs 最小硬币数
    public static int coinChangeDfs(int[] coins, int amt) {
        Assert.isTrue(coins.length > 0);
        int count = coinDfs(coins, amt, 0, 0, 0);
        // 没有找到的转为-1
        return count == Integer.MAX_VALUE ? -1 : count;
    }

    /*
     * amt: 目标金额
     * start: 当前的硬币索引位置
     * times: 当前硬币的使用枚数
     * cur: 当前的金额
     */
    public static int coinDfs(int[] coins, int amt, int start, int times, int cur) {
        if (cur == amt) // 此时已经凑数成功
            return times;
        int minTimes = Integer.MAX_VALUE;
        for (int i = start; i < coins.length; ++i) {
            if (cur + coins[i] > amt)
                continue;
            minTimes = Math.min(minTimes, coinDfs(coins, amt, i, times + 1, cur + coins[i])); // 剪枝: 额度超了
        }
        return minTimes;
    }

    // 零钱兑换 DP
    // dp[i][j] 表示前i个硬币组成金额j的最小硬币数
    // dp[i][a] = min(dp[i - 1][a], dp[i][a - coin] + 1)
    public static int coinChangeDP(int[] coins, int amt) {
        int n = coins.length;
        // 初始化dp
        int[][] dp = new int[n + 1][amt + 1];
        int MAX = amt + 1; // 硬币的数目最大不会超过这个值

        for (int a = 1; a <= amt; ++a) {
            dp[0][a] = MAX;
        }

        for (int i = 1; i <= n; ++i) {
            for (int j = 1; j <= amt; ++j) {
                if (coins[i - 1] > j) { // 此时不能选取当前硬币
                    dp[i][j] = dp[i - 1][j];
                } else {
                    // 可能会出现dp[i][0] 这种情况为0，初始化默认值直接兜底
                    dp[i][j] = Math.min(dp[i - 1][j], dp[i][j - coins[i - 1]] + 1);
                }
            }
        }
        return dp[n][amt] == MAX ? -1 : dp[n][amt];
    }

    // 零钱兑换 优化DP 空间优化
    public static int coinChangeDPComp(int[] coins, int amt) {
        int n = coins.length;
        int[] dp = new int[amt + 1]; // 只需要用一行
        int MAX = amt + 1;
        Arrays.fill(dp, MAX);
        // dp[0]这种情况为0，需要覆盖
        dp[0] = 0;

        for (int i = 1; i <= n; ++i) {
            for (int j = 1; j <= amt; ++j) {
                if (coins[i - 1] > j) {
                    dp[j] = dp[j]; // 直接就是上一行同位置
                } else {
                    dp[j] = Math.min(dp[j], dp[j - coins[i - 1]] + 1);
                }
            }
        }
        return dp[amt] != MAX ? dp[amt] : -1;
    }

    /*
     * 零钱兑换问题II: 凑成目标金额的硬币组合数
     * dp[i][j] 表示前i个硬币组成金额j的组合数
     * dp[i][j] = dp[i - 1][j] + dp[i][j - coins[i - 1]]
     */
    public static int coinChangeIIDP(int[] coins, int amt) {
        int n = coins.length;

        int[][] dp = new int[n + 1][amt + 1];
        for (int i = 1; i <= n; ++i) {
            dp[i][0] = 1;
        }

        for (int i = 1; i <= n; ++i) {
            for (int j = 1; j <= amt; ++j) {
                if (j < coins[i - 1])
                    dp[i][j] = dp[i - 1][j];
                else
                    dp[i][j] = dp[i - 1][j] + dp[i][j - coins[i - 1]];
            }
        }
        return dp[n][amt];
    }

    /*
     * 零钱兑换问题II: 凑成目标金额的硬币组合数
     * 空间优化DP
     */
    public static int coinChangeIIDPComp(int[] coins, int amt) {
        int n = coins.length;

        int[] dp = new int[amt + 1];
        dp[0] = 1;

        for (int i = 1; i <= n; ++i) {
            for (int j = 1; j <= amt; ++j) {
                if (coins[i - 1] > j)
                    dp[j] = dp[j];
                else
                    dp[j] = dp[j] + dp[j - coins[i - 1]];
            }
        }
        return dp[amt];
    }
}

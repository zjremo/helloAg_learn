package com.demo.helloag;

import java.util.Arrays;

public class GreedyLearn {
    /*
     * 零钱兑换问题，贪心算法 不能保证正确性
     * 反例: coins = [1, 20, 50] amt = 60
     */
    public static int coinChangeGreedy(int[] coins, int amt) {
        int n = coins.length;

        int[] coins_copy = Arrays.copyOf(coins, n);
        Arrays.sort(coins_copy); // 面值排序

        int p = n - 1; // 从最大面额开始
        int steps = 0;

        while (amt > 0) {
            while (p > 0 && coins_copy[p] > amt) {
                --p;
            }
            // 一直尽可能选最大面值
            amt -= coins_copy[p];
            ++steps;
        }
        return amt == 0 ? steps : -1;
    }

    /*
     * 零钱兑换问题 引入回溯
     * 上面的算法有一个问题，比如 coins = [5, 3] -> target = 11
     * 明显利用现在的算法无法得到正确结果
     */
    public static int coinChangeDfs(int[] coins, int amt) {
        int n = coins.length;

        int[] coins_copy = Arrays.copyOf(coins, n);
        Arrays.sort(coins_copy);
        int steps = coinChangeBacktrace(coins_copy, 0, 0, amt);
        return steps == Integer.MAX_VALUE ? -1 : steps;
    }

    /*
     * 回溯算法解决零钱兑换问题
     */
    public static int coinChangeBacktrace(int[] coins, int start, int steps, int amt) {
        if (amt == 0)
            return steps; // 一种方案成功

        int n = coins.length;
        int minSteps = Integer.MAX_VALUE;
        // coins 是顺序的
        for (int i = start; i < n; ++i) {
            if (coins[i] > amt)
                break;
            int tmp = coinChangeBacktrace(coins, i, steps + 1, amt - coins[i]);
            minSteps = Math.min(minSteps, tmp);
        }

        return minSteps;
    }

    /*
     * 零钱兑换问题 记忆化搜索
     */
    public static int coinChangeMemSearch(int[] coins, int amt) {
        int n = coins.length;

        int[] coins_copy = Arrays.copyOf(coins, n);
        Arrays.sort(coins_copy);

        int[][] mem = new int[n][amt + 1];

        for (int[] row: mem) {
            Arrays.fill(row, -2); // -2 代表没有进行计算
        }
        int steps = coinChangeMemSearch(coins_copy, 0, amt, mem);
        return steps == Integer.MAX_VALUE ? -1 : steps;
    }

    /*
     * 零钱兑换问题 记忆化搜索
     * Mem: mem数组存储最小兑换steps amt 二维 mem[start][amt]
     * 
     */
    public static int coinChangeMemSearch(int[] coins, int start, int amt, int[][] mem) {
        if (amt == 0)
            return 0;
        
        if (mem[start][amt] != -2)
            return mem[start][amt];

        int minSteps = Integer.MAX_VALUE;
        for (int i = start; i < coins.length; ++i) {
            if (coins[i] > amt)
                break;

            int tmp = coinChangeMemSearch(coins, i, amt - coins[i], mem);
            if (tmp != Integer.MAX_VALUE)
                minSteps = Math.min(minSteps, tmp + 1);
        }
        // 将结果存储到minSteps中
        mem[start][amt] = minSteps;
        return minSteps;
    }
}

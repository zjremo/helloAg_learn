package com.demo.helloag;

import java.util.Arrays;

public class GreedyLearn {
    /*
    零钱兑换问题，贪心算法 不能保证正确性
    反例: coins = [1, 20, 50] amt = 60
    */
    public static int coinChangeGreedy(int[] coins, int amt) {
        int n = coins.length;

        int[] coins_copy = Arrays.copyOf(coins, n);
        Arrays.sort(coins_copy); // 面值排序

        int p = n - 1; // 从最大面额开始
        int steps = 0;
        
        while (p >= 0) {
            if (amt < coins_copy[p]) {
                --p;
                continue;
            }
            ++steps;
            amt -= coins_copy[p];
            if (amt == 0)
                break;
            --p;
        }
        return amt == 0 ? steps : -1;
    }
}

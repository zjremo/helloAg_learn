package com.demo.helloag;

import org.junit.Test;

import cn.hutool.core.lang.Assert;

public class GreedyTest {
    @Test
    public void testCoinChangeGreedy() {
        int[] coins = {1, 5, 10, 20, 50, 100};
        int amt = 131;
        int result = GreedyLearn.coinChangeGreedy(coins, amt);
        System.out.println(result);
        Assert.isTrue(result == 4);
    }

    @Test
    public void testCoinChangeDfs() {
        int[] coins = {1, 5, 10, 20, 50, 100};
        int amt = 131;
        int result = GreedyLearn.coinChangeDfs(coins, amt);
        System.out.println(result);
        Assert.isTrue(result == 4);
    }

    @Test
    public void testCoinChangeMemSearch() {
        int[] coins = {1, 5, 10, 20, 50, 100};
        int amt = 131;
        int result = GreedyLearn.coinChangeMemSearch(coins, amt);
        System.out.println(result);
        Assert.isTrue(result == 4);
    }
}

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
        Assert.isTrue(false);
    }
}

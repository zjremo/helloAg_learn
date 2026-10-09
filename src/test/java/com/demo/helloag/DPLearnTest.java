package com.demo.helloag;

import java.util.List;

import org.junit.Test;

public class DPLearnTest {
    @Test
    public void testClimbingStairs() {
        int n = 3;
        List<List<Integer>> result = DPLearn.climingStairs(n);
        System.out.println(result);
    }

    @Test
    public void testClimingStairsDPComp() {
        int n = 8;
        int result = DPLearn.climingStairsDPComp(n);
        System.out.println(result);
    }
}
package com.demo.helloag;

import java.util.concurrent.ThreadLocalRandom;

public class ArrayLearn {
    public int randomAccess(int[] nums) {
        int randomIndex = ThreadLocalRandom.current().nextInt(0, nums.length);
        int randNum = nums[randomIndex];
        return randNum;
    } 

    public void remove(int[] nums, int idx) {
        assert idx >= 0 && idx < nums.length;
        for (int i = idx; i < nums.length - 1; ++i) {
            nums[i] = nums[i + 1];
        }
    }
}

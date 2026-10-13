package com.demo.helloag;

import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import org.junit.Test;

import cn.hutool.core.lang.Assert;

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

    @Test
    public void testMinClimingStairsDP() {
        int[] cost = { 10, 15, 20 };
        int result = DPLearn.minClimingStairsDP(cost);
        System.out.println(result);
    }

    @Test
    public void testMinClimingStairsDPComp() {
        int[] cost = { 10, 15, 20 };
        int result = DPLearn.minClimingStairsDPComp(cost);
        System.out.println(result);
    }

    @Test
    public void testClimbingStairsConstraintDP() {
        // constraintDP1 and constraintDP2 should be the same
        int count = 50; // 对拍count次
        String filePath = "./src/test/document/climbingStairsConstraintDP_data.txt";
        StringBuilder sb = new StringBuilder();
        StringBuilder failSb = new StringBuilder();
        int failCount = 0;
        int passCount = 0;

        try (FileOutputStream fileOutputStream = new FileOutputStream(filePath)) {
            FileChannel fileChannel = fileOutputStream.getChannel();
            ByteBuffer byteBuffer = ByteBuffer.allocate(4096);
            for (int i = 0; i < count; ++i) {
                int n = ThreadLocalRandom.current().nextInt(1, 300);
                int result1 = DPLearn.climbingStairsConstraintDP(n);
                int result2 = DPLearn.climbingStairsConstraintDP2(n);
                if (result1 != result2) {
                    failCount++;
                    System.out.println("Test failed: n = " + n + ", result1 = " + result1 + ", result2 = " + result2);
                    failSb.append(n).append("\n");
                } else {
                    passCount++;
                    sb.append(n).append("\n");
                }
            }
            System.out.println("Test passed: " + passCount + " times");
            System.out.println("Test failed: " + failCount + " times");
            String s = "Test passed: " + passCount + " times, the numbers are:\n";
            byteBuffer.put(s.getBytes());
            byteBuffer.put(sb.toString().getBytes());
            byteBuffer.put("\n\n".getBytes());
            s = "Test failed: " + failCount + " times, the numbers are:\n";
            byteBuffer.put(s.getBytes());
            byteBuffer.put(failSb.toString().getBytes());
            byteBuffer.flip();
            fileChannel.write(byteBuffer);
        } catch (IOException e) {
            e.printStackTrace(System.out);
        }
    }

    @Test
    public void testClimbingStairsBarrier() {
        int n = 4;
        int result = DPLearn.climbingStairsBarrier(n);
        System.out.println(result);
    }

    @Test
    public void testMinPathSumDP() {
        int[][] grid = { { 1, 3, 1 }, { 1, 5, 1 }, { 4, 2, 1 } };
        int result1 = DPLearn.minPathSumDFS(grid);
        int result2 = DPLearn.minPathSumDP(grid);
        System.out.println(result1);
        System.out.println(result2);
        Assert.isTrue(result1 == result2, "result1 != result2");
    }

    @Test
    public void testMinPathSumDPComp() {
        int[][] grid = { { 1, 3, 1 }, { 1, 5, 1 }, { 4, 2, 1 } };
        int result1 = DPLearn.minPathSumDFS(grid);
        int result2 = DPLearn.minPathSumDP(grid);
        int result3 = DPLearn.minPathSumDPComp(grid);
        System.out.println(result1);
        System.out.println(result2);
        System.out.println(result3);
        Assert.isTrue(result1 == result2, "result1 != result2");
        Assert.isTrue(result1 == result3, "result1 != result3");
    }

    @Test
    public void testKnapsackDFS() {
        // 对拍count次
        int count = 50;
        String filePath = "./src/test/document/knapsackDFS_data.txt";
        StringBuilder sb = new StringBuilder();
        StringBuilder failSb = new StringBuilder();
        int failCount = 0;
        int passCount = 0;

        try (FileOutputStream fileOutputStream = new FileOutputStream(filePath)) {
            FileChannel fileChannel = fileOutputStream.getChannel();
            for (int i = 0; i < count; ++i) {
                int n = ThreadLocalRandom.current().nextInt(1, 50);
                int weight = ThreadLocalRandom.current().nextInt(1, 100);
                int[] wgt = new int[n];
                int[] val = new int[n];
                for (int j = 0; j < n; ++j) {
                    wgt[j] = ThreadLocalRandom.current().nextInt(1, 30);
                    val[j] = ThreadLocalRandom.current().nextInt(1, 100);
                }
                int result1 = DPLearn.knapsackDFS(wgt, val, weight);
                int result2 = DPLearn.knapsackDFSV2(wgt, val, n, weight);
                if (result1 != result2) {
                    failCount++;
                    System.out.println(String.format("Test failed: n = %d, weight = %d, val = %s, wgt = %s, result1 = %d, result2 = %d", n, weight, Arrays.toString(val), Arrays.toString(wgt), result1, result2));
                    failSb.append(String.format("weight = %d, val = %s, wgt = %s, result1 = %d, result2 = %d\n", weight, Arrays.toString(val), Arrays.toString(wgt), result1, result2));
                } else {
                    passCount++;
                    sb.append(String.format("weight = %d, val = %s, wgt = %s\n", weight, Arrays.toString(val), Arrays.toString(wgt)));
                }
            }
            System.out.println("Test passed: " + passCount + " times");
            System.out.println("Test failed: " + failCount + " times");
            String content = "Test passed: " + passCount + " times, the numbers are:\n"
                    + sb
                    + "\n\n"
                    + "Test failed: " + failCount + " times, the datas are:\n"
                    + failSb;
            // 按照实际长度写入文件
            fileChannel.write(ByteBuffer.wrap(content.getBytes()));
        } catch (IOException e) {
            e.printStackTrace(System.out);
        }
    }

    @Test
    public void testKnapsackDFSMem() {
        int[] wgt = { 1, 2, 3, 4, 5 };
        int[] val = { 1, 2, 3, 4, 5 };
        int weight = 10;
        int[][] mem = new int[wgt.length + 1][weight + 1];
        for (int[] row : mem) {
            Arrays.fill(row, -1);
        }
        int result = DPLearn.knapsackDFSMem(wgt, val, mem, wgt.length, weight);
        System.out.println(result);
    }

    @Test
    public void testKnapsackDP() {
        int[] wgt = { 1, 2, 3, 4, 5 };
        int[] val = { 1, 2, 3, 4, 5 };
        int cap = 10;
        int result = DPLearn.knapsackDP(wgt, val, cap);
        System.out.println(result);
    }

    @Test
    public void testUnboundedKnapsackDP() {
        int[] wgt = { 1, 2, 3, 4, 5 };
        int[] val = { 1, 2, 3, 4, 5 };
        int cap = 10;
        int result = DPLearn.unboundedKnapsackDfs(wgt, val, cap);
        System.out.println("dfs: " + result);
        int result2 = DPLearn.unboundedKnapsackDP(wgt, val, cap);
        System.out.println("dp: " + result2);
        int result3 = DPLearn.unboundedKnapsackDPComp(wgt, val, cap);
        System.out.println("dp comp: " + result3);
        Assert.isTrue(result == result2, "result != result2");
        Assert.isTrue(result == result3, "result != result3");
    }
}
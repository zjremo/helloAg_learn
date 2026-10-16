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
                    System.out.println(String.format(
                            "Test failed: n = %d, weight = %d, val = %s, wgt = %s, result1 = %d, result2 = %d", n,
                            weight, Arrays.toString(val), Arrays.toString(wgt), result1, result2));
                    failSb.append(String.format("weight = %d, val = %s, wgt = %s, result1 = %d, result2 = %d\n", weight,
                            Arrays.toString(val), Arrays.toString(wgt), result1, result2));
                } else {
                    passCount++;
                    sb.append(String.format("weight = %d, val = %s, wgt = %s\n", weight, Arrays.toString(val),
                            Arrays.toString(wgt)));
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

    @Test
    public void testCoinChangeDFS() {
        // 对拍count次
        int count = 20;
        String filePath = "./src/test/document/coinChange_data.txt";
        StringBuilder sb = new StringBuilder();
        StringBuilder failSb = new StringBuilder();
        int failCount = 0;
        int passCount = 0;

        try (FileOutputStream outputStream = new FileOutputStream(filePath)) {
            FileChannel fileChannel = outputStream.getChannel();
            /*
             * 需要构造三个东西:
             * 1. the length of coins: len
             * 2. amount: 目标金额
             * 3. coins: must be positive number
             */
            sb.append("Test all cases are: \n");
            failSb.append("Test cases of failed are: \n");
            for (int i = 0; i < count; ++i) {
                System.out.println("Test case " + (i + 1) + " is running...");
                // 用例构造
                int len = ThreadLocalRandom.current().nextInt(1, 20);
                int amount = ThreadLocalRandom.current().nextInt(1, 30);
                int[] coins = new int[len];
                for (int j = 0; j < len; ++j) 
                    coins[j] = ThreadLocalRandom.current().nextInt(1, 30);
                
                sb.append("coins: ").append(Arrays.toString(coins)).append(", amount: ").append(amount).append("\n");
                int resDfs = DPLearn.coinChangeDfs(coins, amount);
                int resDP = DPLearn.coinChangeDP(coins, amount);
                if (resDfs != resDP) {
                    ++failCount;
                    failSb.append("coins: ")
                    .append(Arrays.toString(coins))
                    .append(", amount: ")
                    .append(amount)
                    .append(", result of dfs: ")
                    .append(resDfs)
                    .append(", result of dp: ")
                    .append(resDP)
                    .append("\n");
                } else {
                    ++passCount;
                }
            }
            System.out.println("Test passed count: " + passCount);
            System.out.println("Test failed count: " + failCount);

            String ws = sb + "\n\n" + failSb;
            fileChannel.write(ByteBuffer.wrap(ws.getBytes()));
        } catch (IOException e) {
            e.printStackTrace(System.out);
        }
    }

    @Test
    public void testCoinChangeDP() {
        int[] coins = { 1, 2, 5 };
        int amount = 11;
        int result = DPLearn.coinChangeDP(coins, amount);
        System.out.println(result);
    }

    @Test
    public void testCoinChangeDPComp() {
        int[] coins = { 1, 2, 5 };
        int amount = 11;
        int result = DPLearn.coinChangeDPComp(coins, amount);
        System.out.println(result);
    }

    @Test
    public void testCoinChangeIIDP() {
        int[] coins = { 1, 2, 5 };
        int amount = 11;
        int result = DPLearn.coinChangeIIDP(coins, amount);
        int result2 = DPLearn.coinChangeIIDPComp(coins, amount);
        System.out.println("dp: " + result + ", dp comp: " + result2);
        Assert.isTrue(result == result2, "result != result2");
    }

    @Test
    public void testEditDistanceDPAndComp() { // 简单对拍，DP and DPComp
        StringBuilder passSb = new StringBuilder(), failSb = new StringBuilder();

        int count = 100, passCount = 0, failCount = 0;
        
        /*
        * 需要构造三个东西:
         * 1. s的长度: slen
         * 2. t的长度: tlen
         * 3. s: 源字符串 生成slen次char字符
         * 4. t: 目标字符串 生成tlen次char字符
         */
        for (int i = 0; i < count; ++i) {
            int slen = ThreadLocalRandom.current().nextInt(1, 10);
            int tlen = ThreadLocalRandom.current().nextInt(1, 10);
            char[] s = new char[slen];
            char[] t = new char[tlen];
            for (int j = 0; j < slen; ++j) {
                s[j] = (char) (ThreadLocalRandom.current().nextInt(0, 26) + 'a');
            }
            for (int j = 0; j < tlen; ++j) {
                t[j] = (char) (ThreadLocalRandom.current().nextInt(0, 26) + 'a');
            }
            String sStr = new String(s);
            String tStr = new String(t);
            int resultDP = DPLearn.editDistanceDP(sStr, tStr);
            int resultDPComp = DPLearn.editDistanceDPComp(sStr, tStr);
            if (resultDP != resultDPComp) {
                ++failCount;
                String tmp = "s: " + sStr + ", t: " + tStr + ", resultDP: " + resultDP + ", resultDPComp: " + resultDPComp;
                failSb.append(tmp).append("\n");
            } else {
                ++passCount;
                String tmp = "s: " + sStr + ", t: " + tStr + ", result: " + resultDP;
                passSb.append(tmp).append("\n");
            }
        }
        System.out.format("passCount: %d, details are: \n%s\n", passCount, passSb);
        System.out.format("failCount: %d, details are: \n%s\n", failCount, failSb);
    }
}
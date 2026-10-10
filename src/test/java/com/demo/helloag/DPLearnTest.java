package com.demo.helloag;

import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

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
                sb.append(n).append("\n");
                int result1 = DPLearn.climbingStairsConstraintDP(n);
                int result2 = DPLearn.climbingStairsConstraintDP2(n);
                if (result1 != result2) {
                    failCount++;
                    System.out.println("Test failed: n = " + n + ", result1 = " + result1 + ", result2 = " + result2);
                    failSb.append(n).append("\n");
                } else {
                    passCount++;
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
}
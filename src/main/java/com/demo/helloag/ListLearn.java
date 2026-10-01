package com.demo.helloag;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class ListLearn {
    public void initList() {
        List<Integer> nums1 = new ArrayList<>();
        Integer[] numbers = new Integer[] {
                1, 2, 3, 4, 5
        };
        List<Integer> nums = new ArrayList<>(Arrays.asList(numbers));
        System.out.println("nums is " + nums);
        System.out.println("nums1 is " + nums1);
    }

    public void appendListDemo(List<Integer> nums) {
        List<Integer> nums1 = new ArrayList<>(Arrays.asList(6, 8, 7, 10, 9));
        nums.addAll(nums1);

        Collections.sort(nums);
        System.out.println("nums: " + nums);
    }

    // 加1简便做法
    public int[] plusOneV1(int[] digits) {
        List<Integer> res = new ArrayList<>();
        int n = digits.length;
        assert n >= 1;

        int c = 1;
        for (int i = n - 1; i >= 0; --i) {
            int sum = c + digits[i];
            res.add(sum % 10);
            c = sum / 10;
        }

        if (c != 0) {
            res.add(1);
        }
        Collections.reverse(res);
        return res.stream().mapToInt(i -> i).toArray();
    }

    public static void main(String[] args) {
        int[][] examples = new int[3][6];
        examples[0] = new int[] {
                9, 9, 9, 9, 9, 9
        };
        examples[1] = new int[] {
                1, 2, 9, 3, 9, 9
        };
        examples[2] = new int[] {
                9, 0, 0, 0, 0, 0
        };

        for (int[] example : examples) {
            int[] res = plusOneV2(example);
            System.out.println(Arrays.toString(res));
        }
    }

    // 加1 优化
    public static int[] plusOneV2(int[] digits) {
        int n = digits.length;
        assert n >= 1;
        // step1: reverse order traverse
        // step2: search first pos of non 9
        // step3: reverse init to 0
        for (int i = n - 1; i >= 0; --i) {
            if (digits[i] != 9) {
                ++digits[i];
                for (int j = i + 1; j < n; ++j) {
                    digits[j] = 0;
                }
                return digits;
            }
        }

        // 后面全是9, 此时需要补位, 然后全部置为0
        int[] ndigits = new int[n + 1];
        Arrays.fill(ndigits, 0);
        ndigits[0] = 1;
        return ndigits;
    }

}

class MyArrayList {
    private int[] arr;
    private int capacity = 10; // 列表容量
    private int size = 0; // 列表长度
    private int extendRatio = 2; // 每次扩容倍数

    public MyArrayList() {
        arr = new int[capacity];
    }

    public int size() {
        return size;
    }

    public int capacity() {
        return capacity;
    }

    public int get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }
        return arr[index];
    }

    public void add(int num) {
        if (size == capacity) {
            extendCapacity();
        }
        arr[size] = num;
        size++;
    }

    public void insert(int index, int num) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }
        // 元素数量超过容量，触发扩容
        if (size == capacity())
            extendCapacity();
        for (int j = size - 1; j >= index; --j) {
            arr[j + 1] = arr[j];
        }
        arr[index] = num;
        ++size;
    }

    public int remove(int index) {
        if (index < 0 || index >= size)
            throw new IndexOutOfBoundsException();
        int num = arr[index];
        for (int j = index; j < size - 1; ++j) {
            arr[j] = arr[j + 1];
        }
        --size;
        return num;
    }

    public int[] toArray() {
        int size = size();
        int[] copyArr = new int[size];
        for (int i = 0; i < size; ++i) {
            copyArr[i] = get(i);
        }
        return copyArr;
    }

    private void extendCapacity() {
        arr = Arrays.copyOf(arr, capacity() * extendRatio);
        capacity = arr.length;
    }
}

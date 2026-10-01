package com.demo.helloag;

import java.util.ArrayList;
import java.util.List;

import cn.hutool.core.lang.Pair;

public class HashLearn {
    public static void main(String[] args) {
        HashMapChaining hashMap = new HashMapChaining();
        hashMap.put(1, "hello");
        hashMap.put(2, "world");
        hashMap.put(3, "hi");
        hashMap.put(4, "xiaozhang");
        hashMap.put(5, "xiaorui");

        System.out.println(hashMap.get(1));
        System.out.println(hashMap.get(2));
        System.out.println(hashMap.get(3));
        System.out.println("size: " + hashMap.size());
        hashMap.remove(5);
        System.out.println(hashMap.get(5));
    }
}

class HashMapChaining {
    private List<List<Pair<Integer, String>>> buckets;
    private int size; // key-value number
    private int capacity; // hashTable capacity
    private double loadThres; // 负载因子
    private int extendRatio; // 扩容倍数

    public HashMapChaining() {
        size = 0;
        capacity = 4;
        loadThres = 2 / 3;
        extendRatio = 2;

        buckets = new ArrayList<>(capacity);
        for (int i = 0; i < capacity; ++i) {
            buckets.add(new ArrayList<>());
        }
    }

    public int size() {
        return size;
    }

    public double loadFactor() {
        return size / loadThres;
    }

    public int hashFunc(int key) {
        return key % 100;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public String get(int key) {
        if (isEmpty()) {
            return null;
        }
        int index = hashFunc(key);
        List<Pair<Integer, String>> bucket = buckets.get(index);
        for (Pair<Integer, String> pair : bucket) {
            if (key == pair.getKey()) {
                return pair.getValue();
            }
        }
        return null;
    }

    private void extend() {
        List<List<Pair<Integer, String>>> bucketsTmp = buckets;

        // Step1: 分配新的桶位
        buckets = new ArrayList<>(capacity);
        capacity *= extendRatio;
        for (int i = 0; i < capacity; ++i) {
            buckets.add(new ArrayList<>());
        }

        // Step2: 将现有的键值对进行转移
        size = 0; // 后面的put会重新计算size
        for (List<Pair<Integer, String>> bucket : bucketsTmp) {
            for (Pair<Integer, String> pair : bucket) {
                put(pair.getKey(), pair.getValue());
            }
        }
    }

    public void put(int key, String value) {
        if (loadFactor() > loadThres) {
            extend();
        }

        int index = hashFunc(key);
        List<Pair<Integer, String>> bucket = buckets.get(index);
        bucket.add(new Pair<>(key, value));
        ++size;
    }

    public void remove(int key) {
        if (isEmpty()) {
            throw new IndexOutOfBoundsException();
        }

        int index = hashFunc(key);
        List<Pair<Integer, String>> bucket = buckets.get(index);

        for (Pair<Integer, String> pair : bucket) {
            if (pair.getKey() == key) {
                bucket.remove(pair);
                break;
            }
        }
    }
}

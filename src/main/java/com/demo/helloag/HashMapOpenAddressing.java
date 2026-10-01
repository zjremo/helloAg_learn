package com.demo.helloag;

import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data 
@AllArgsConstructor 
class Pair<K, V> {
    private K key;
    private V value;
}

public class HashMapOpenAddressing{
    private Pair<Integer, String>[] buckets; // 桶数组
    private int size; // 键值对统计
    private int capacity; // 容量
    private double loadThres = 2.0 / 3.0; // 负载因子, 一定不能为1，为1后面的findBucket会死循环
    private final int extendRatio = 2; // 扩容倍数
    private final Pair<Integer, String> TOMBSTONE = new Pair<>(-1, "-1");

    public static void main(String[] args) {
        HashMapOpenAddressing hashMapOpenAddressing = new HashMapOpenAddressing(4);
        hashMapOpenAddressing.put(1, "1");
        hashMapOpenAddressing.put(2, "2");
        hashMapOpenAddressing.put(3, "3");
        hashMapOpenAddressing.put(4, "4");
        hashMapOpenAddressing.put(5, "5");
        hashMapOpenAddressing.put(6, "6");

        hashMapOpenAddressing.remove(5);
        hashMapOpenAddressing.remove(3);

        hashMapOpenAddressing.print();

        System.out.println(hashMapOpenAddressing.get(4));
        System.out.println(hashMapOpenAddressing.get(6));
        hashMapOpenAddressing.put(12, "12");
        hashMapOpenAddressing.print();
    }

    public boolean isEmpty() {
        return size == 0;
    }

    @SuppressWarnings("unchecked")
    public HashMapOpenAddressing(int capacity) {
        size = 0;
        this.capacity = capacity;
        buckets = new Pair[capacity];
    }

    private int hashFunc(int key) {
        return key % capacity;
    }

    private double loadFactor() {
        return (double) size / capacity;
    }

    private int findBucket(int key) {
        assert size < capacity; // 否则会死循环
        int index = hashFunc(key);
        int firstTombstone = -1;
        while (buckets[index] != null) {
            if (buckets[index].getKey() == key) { // 探测成功
                // 之前有无碰到TOMBSTONE
                if (firstTombstone != -1) { // 碰到了
                    // 将这个index的key-value移动到firstTombstone
                    buckets[firstTombstone] = buckets[index];
                    buckets[index] = TOMBSTONE;
                    return firstTombstone; 
                }
                return index;
            }

            // 碰到Tombstone
            if (firstTombstone == -1 && buckets[index] == TOMBSTONE) {
                firstTombstone = index;
            }

            // step forward
            index = (index + 1) % capacity;
        }
        return firstTombstone == -1 ? index : firstTombstone;
    }

    public String get(int key) {
        if (isEmpty()) {
            return null;
        }

        // find index in buckets
        int idx = findBucket(key);
        if (buckets[idx] != null && buckets[idx] != TOMBSTONE) {
            return buckets[idx].getValue();
        }
        return null;
    }

    public void put(int key, String value) {
        // check capacity 
        if (loadFactor() > loadThres) {
            extend();
        }

        int idx = findBucket(key);
        if (buckets[idx] != null && buckets[idx] != TOMBSTONE) {
            // override value and return
            buckets[idx].setValue(value);
            return;
        }
        buckets[idx] = new Pair<>(key, value);
        ++size;
    }

    public void remove(int key) {
        if (isEmpty()) {
            throw new IndexOutOfBoundsException();
        }
        
        int idx = findBucket(key);
        if (buckets[idx] == null || buckets[idx] == TOMBSTONE) {
            return; // 此时没有找到这个key
        }
        buckets[idx] = TOMBSTONE;
        --size;
    }

    @SuppressWarnings ("unchecked")
    public void extend() {
        Pair<Integer, String>[] bucketsTmp = buckets;
        
        // command a new capacity buckets array and reinsert key-value tuples
        capacity *= extendRatio;
        size = 0;
        buckets = new Pair[capacity];

        for (Pair<Integer, String> pair: bucketsTmp) {
            if (pair != null && pair != TOMBSTONE) {
                put(pair.getKey(), pair.getValue());
            }
        }
    }

    public void print() {
        List<String> res = new ArrayList<>();

        for (Pair<Integer, String> pair : buckets) {
            if (pair == null) {
                res.add("None");
            } else if (pair == TOMBSTONE) {
                res.add("TOMBSTONE");
            } else {
                res.add(pair.getValue());
            }
        } 

        System.out.println(String.join(",", res));
    }
}
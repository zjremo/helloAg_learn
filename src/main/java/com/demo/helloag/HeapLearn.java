package com.demo.helloag;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;

public class HeapLearn {
    public static void main(String[] args) {
        MaxHeap heap = new MaxHeap();
        heap.offer(1);
        heap.offer(2);
        heap.offer(3);
        heap.offer(4);
        heap.offer(5);
        heap.offer(6);

        System.out.println("---------basic metadata---------");
        System.out.println("peek: " + heap.peek());
        System.out.println("size: " + heap.size());
        System.out.println("---------basic metadata---------");
        System.out.println("---------poll---------");
        System.out.println(heap.poll());
        System.out.println(heap.poll());
        System.out.println(heap.poll());
        System.out.println("---------poll---------");
        System.out.println("---------now heap is ---------");
        System.out.println(heap);
    }
}

class MaxHeap {
    private List<Integer> arr; // heapsize = arr.size

    public MaxHeap() {
        arr = new ArrayList<>();
    }

    // get heapSize
    public int size() {
        return arr.size();
    }

    public int left(int i) {
        return 2 * i + 1;
    }

    public int right(int i) {
        return 2 * i + 2;
    }

    public int parent(int i) {
        return (i - 1) / 2;
    }

    public int peek() {
        if (isEmpty()) 
            throw new NoSuchElementException("heap is empty");
        return arr.get(0);
    }

    public boolean isEmpty() {
        return arr.isEmpty();
    }

    private void swap(int i, int j) {
        Collections.swap(arr, i, j);
    }

    public void offer(int val) {
        arr.add(val);

        // siftup
        siftup(size() - 1);
    }

    public int poll() {
        if (isEmpty()) {
            throw new IndexOutOfBoundsException();
        }
        swap(0, size() - 1);
        int val = arr.remove(size() - 1);
        siftdown(0);
        return val;
    }

    private int get(int i) {
        return arr.get(i);
    }

    private void siftdown(int i) {
        // i索引位置value下沉
        int k = i; // 存最后应该换到哪个位置
        int target = get(i);
        int half = size() >>> 1;

        while (k < half) {
            int child = left(k);
            int maxChild = child + 1 < size() && get(child + 1) > get(child) ? child + 1 : child;
            if (get(maxChild) <= target) {
                break;
            }
            arr.set(k, get(maxChild));
            k = maxChild;
        }
        arr.set(k, target);
    }

    private void siftup(int idx) {
        // idx所在位置上浮
        int k = idx;
        int target = get(idx);

        while (k > 0) {
            int parent = parent(k);
            if (target <= get(parent)) 
                break;
            arr.set(k, get(parent));
            k = parent;
        }
        arr.set(k, target);
    }

    @Override
    public String toString() {
        return arr.toString();
    }
}
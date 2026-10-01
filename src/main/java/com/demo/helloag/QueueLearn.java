package com.demo.helloag;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.stream.IntStream;

import lombok.extern.slf4j.Slf4j;

@Slf4j(topic = "C.QueueLearn")
public class QueueLearn {
    public static void main(String[] args) {
        int[] arr = IntStream.range(1, 10).toArray();
        // final StackQueue<Integer> stackQueue = new StackQueue<>();
        final ArrayQueue stackQueue = new ArrayQueue(20);
        Arrays.stream(arr).forEach(stackQueue::offer);

        log.debug("isEmpty: {}, size: {}, peek: {}", stackQueue.isEmpty(), stackQueue.size(), stackQueue.peek());
        while (!stackQueue.isEmpty()) {
            log.debug("poll: {}", stackQueue.poll());
        }
    }
}

// 栈模拟队列
class StackQueue<T> {
    private Deque<T> s1;
    private Deque<T> s2;

    public StackQueue() {
        s1 = new ArrayDeque<>();
        s2 = new ArrayDeque<>();
    }

    public void offer(T val) {
        s1.push(val);
    }

    public T poll() {
        if (isEmpty()) {
            return null;
        }

        if (!s2.isEmpty()) {
            return s2.pop();
        }

        // s1的元素转移到s2
        while (!s1.isEmpty()) {
            s2.push(s1.pop());
        }
        return s2.pop();
    }

    public int size() {
        return s1.size() + s2.size();
    }

    public boolean isEmpty() {
        return s1.isEmpty() && s2.isEmpty();
    }

    public T peek() {
        if (isEmpty()) {
            return null;
        }
        if (!s2.isEmpty()) {
            return s2.peek();
        }
        // s1的元素转移到s2
        while (!s1.isEmpty()) {
            s2.push(s1.pop());
        }
        return s2.peek();
    }
}

class ListNodeQueue<T> {
    private ListNode<T> head;
    private ListNode<T> tail;
    private int size;

    public ListNodeQueue() {
        head = new ListNode<>(null);
        tail = new ListNode<>(null);
        size = 0;
    }

    public T peek() {
        if (size == 0) {
            return null;
        }

        return head.val;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    public void offer(T val) {
        ListNode<T> node = new ListNode<>(val);
        if (isEmpty()) {
            head = node;
            tail = node;
        } else {
            tail.next = node;
            tail = tail.next;
        }
        ++size;
    }

    public T poll() {
        if (isEmpty()) {
            return null;
        }
        
        ListNode<T> node = head;
        head = head.next;
        --size;
        return node.val;
    }
}

class ArrayQueue {
    private int[] nums;
    private int head;
    private int queSize; // 队列长度

    public ArrayQueue(int capacity){
        nums = new int[capacity];
        head = queSize = 0;
    }

    public int capacity() {
        return nums.length;
    }

    public int size() {
        return queSize;
    }

    public boolean isEmpty() {
        return queSize == 0;
    }

    public int peek() {
        if (isEmpty()) {
            throw new IndexOutOfBoundsException();
        }
        return nums[head];
    }

    public void offer(int val) {
        if (queSize == capacity()) {
            System.out.println("queue full");
            return;
        }

        int tail = (head + queSize) % capacity();
        nums[tail] = val;
        ++queSize;
    }

    public int poll() {
        int num = peek();
        head = (head + 1) % capacity();
        --queSize;
        return num;
    }
}
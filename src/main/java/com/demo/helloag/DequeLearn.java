package com.demo.helloag;

import lombok.extern.slf4j.Slf4j;

@Slf4j(topic = "C.DequeLearn")
public class DequeLearn {
    public static void main(String[] args) {
        // MyDeque<Integer> deque = new ListDeque<>();
        MyDeque<Integer> deque = new ArrayDeque<>(20);

        deque.offerLast(2);
        deque.offerLast(5);
        deque.offerLast(4);
        deque.offerFirst(3);
        deque.offerFirst(1);

        int peekFirst = deque.peekFirst();
        int peekLast = deque.peekLast();

        int popFirst = deque.pollFirst();
        int popLast = deque.pollLast();
        log.debug("peekFirst: {}, peekLast: {}, popFirst: {}, popLast: {}",
                peekFirst, peekLast, popFirst, popLast);

        log.debug("size: {}", deque.size());
        log.debug("isEmpty(): {}", deque.isEmpty());
    }
}

interface MyDeque<T> {
    boolean offerFirst(T val);
    boolean offerLast(T val);
    T peekFirst();
    T peekLast();
    T pollFirst();
    T pollLast();
    int size();
    boolean isEmpty();
}

class ListDeque<T> implements MyDeque<T> {
    private ListNode<T> head;
    private ListNode<T> tail;
    private int size;

    public ListDeque() {
        head = tail = null;
        size = 0;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public boolean offerFirst(T val) {
        if (isEmpty()) {
            head = tail = new ListNode<>(val);
        } else {
            ListNode<T> node = new ListNode<>(val);
            node.next = head;
            head.prev = node;
            head = node;
        }
        ++size;
        return true;
    }

    @Override
    public boolean offerLast(T val) {
        
        if (isEmpty()) {
            head = tail = new ListNode<>(val);
        } else {
            ListNode<T> node = new ListNode<>(val);
            tail.next = node;
            node.prev = tail;
            tail = node;
        }
        ++size;
        return true;
    }

    @Override
    public T peekFirst() {
        if (isEmpty()) {
            throw new IndexOutOfBoundsException();
        }

        return head.val;
    }

    @Override
    public T peekLast() {
        if (isEmpty()) {
            throw new IndexOutOfBoundsException();
        }

        return tail.val;
    }

    @Override
    public T pollFirst() {
        if (isEmpty()) {
            throw new IndexOutOfBoundsException();
        }

        ListNode<T> node = head;
        head = head.next;
        head.prev = null;
        
        node.next = null;
        --size;
        return node.val;
    }

    @Override
    public T pollLast() {
        if (isEmpty()) {
            throw new IndexOutOfBoundsException();
        }

        ListNode<T> node = tail;
        tail = tail.prev;
        tail.next = null;

        node.prev = null;
        --size;
        return node.val;
    }

    @Override
    public int size() {
        return size;
    }
}

@Slf4j (topic = "C.ArrayDeque")
class ArrayDeque<T> implements MyDeque<T> {
    private Object[] arr;
    private int front;
    private int size;

    public ArrayDeque(int capacity) {
        arr = new Object[capacity];
        front = 0;
        size = 0;
    }

    private int capacity() {
        return arr.length;
    }

    private int index(int idx) {
        return (idx + capacity()) % capacity();
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public boolean offerFirst(T val) {
        if (size == capacity()) {
            log.debug("deque is full");
            return false;
        }

        arr[front = index(front - 1)] = val;
        ++size;
        return true;
    }

    @Override
    public boolean offerLast(T val) {
        if (size == capacity()) {
            log.debug("deque is full");
            return false;
        }

        arr[index(front + size)] = val;
        ++size;
        return true;
    }

    @SuppressWarnings ("unchecked")
    @Override
    public T peekFirst() {
        if (isEmpty()) {
            throw new IndexOutOfBoundsException();
        }
        
        return (T) arr[front];
    }

    @SuppressWarnings ("unchecked")
    @Override
    public T peekLast() {
        if (isEmpty()) {
            throw new IndexOutOfBoundsException();
        }
        
        return (T) arr[index(front + size - 1)];
    }

    @SuppressWarnings ("unchecked")
    @Override
    public T pollFirst() {
        if (isEmpty()) {
            throw new IndexOutOfBoundsException();
        }

        T val = (T) arr[front];
        front = index(front + 1);
        --size;
        return val;
    }

    @SuppressWarnings ("unchecked")
    @Override
    public T pollLast() {
        if (isEmpty()) {
            throw new IndexOutOfBoundsException();
        }

        T val = (T) arr[index(front + size - 1)];
        --size;
        return val;
    }

    @Override
    public int size() {
        return size;
    }
}
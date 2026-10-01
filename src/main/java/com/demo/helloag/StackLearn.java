package com.demo.helloag;

import java.util.List;

import lombok.extern.slf4j.Slf4j;

@Slf4j (topic = "C.Stack")
public class StackLearn {
    public static void main(String[] args) {
        LinkedListStack<Integer> myStack = new LinkedListStack<>();
        myStack.push(1);
        myStack.push(2);
        myStack.push(3);
        myStack.push(4);
        log.debug("size: {}", myStack.size());
        while (!myStack.isEmpty()) {
            log.debug("pop: {}", myStack.pop());
        }
        log.debug("size: {}", myStack.size());
    }
}

class ArrayStack<T> {
    private List<T> stackList;

    public ArrayStack() {
        stackList = null;
    }

    public void push(T val) {
        stackList.add(val);
    }

    public T pop() {
        if (isEmpty()) {
            return null;
        }
        return stackList.remove(stackList.size() - 1);
    }

    public boolean isEmpty() {
        return stackList.isEmpty();
    }

    public T peek() {
        if (isEmpty()) {
            return null;
        }
        return stackList.getLast();
    }

    public int size() {
        return stackList.size();
    }
}

class LinkedListStack<T> {
    private ListNode<T> stackPeek;
    private int size;

    public LinkedListStack() {
        stackPeek = null;
        size = 0;
    }

    public void push(T val) {
        ListNode<T> node = new ListNode<T>(val);
        node.next = stackPeek;
        stackPeek = node;
        ++size;
    }

    public T pop() {
        if (size == 0) {
            return null;
        }
        ListNode<T> node = stackPeek;
        stackPeek = node.next;
        --size;
        return node.val;
    }

    public int size() {
        return size;
    }

    public T peek() {
        if (size == 0) {
            return null;
        }
        return stackPeek.val;
    }

    public boolean isEmpty() {
        return size == 0;
    }
}



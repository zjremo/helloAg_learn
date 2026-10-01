package com.demo.helloag;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ListNode<T> {
    public ListNode<T> next; // 后继
    public ListNode<T> prev; // 前驱
    public T val;

    public ListNode(T val) {
        this.val = val;
    }
}

package com.demo.helloag;

import org.junit.Test;

public class ListNodeTest {
    @Test 
    public void TestMergeSortList() {
        ListNode<Integer> head = new ListNode<>(1);
        head.next = new ListNode<>(5);
        head.next.next = new ListNode<>(4);
        head.next.next.next = new ListNode<>(3);
        head.next.next.next.next = new ListNode<>(2);
        ListNode<Integer> sortedHead = ListNodeLearn.mergeSortList(head);
        System.out.println(ListNodeLearn.printList(sortedHead));
    }
}

package com.demo.helloag;

import java.util.ArrayList;
import java.util.List;

public class ListNodeLearn {
    public void init() {
        ListNode<Integer> n1 = new ListNode<>(0);
        ListNode<Integer> n2 = new ListNode<>(1);
        ListNode<Integer> n3 = new ListNode<>(2);
        ListNode<Integer> n4 = new ListNode<>(3);

        n1.next = n2;
        n2.next = n3;
        n3.next = n4;
    }

    // insert p insert to the next of n0
    public <T> void insert(ListNode<T> n0, ListNode<T> p) {
        ListNode<T> next = n0.next;
        p.next = next;
        n0.next = p;
    }

    // delete p delete the next of p
    public <T> void remove(ListNode<T> n0) {
        assert n0 != null;

        ListNode<T> p = n0.next;
        ListNode<T> n1 = p.next;
        n0.next = n1;
    }

    // access  
    public <T> ListNode<T> access(ListNode<T> head, int index) {
        assert head != null;
        int p = 0;
        ListNode<T> pNode = head;

        while (pNode != null && p < index) {
            pNode = pNode.next;
            ++p;
        }
        return pNode;
    }

    // find 
    public <T> int find(ListNode<T> head, T target) {
        int index = 0;
        while (head != null) {
            if (head.val == target) {
                return index;
            }
            head = head.next;
            ++index;
        }
        return -1;
    }

    public static void main(String[] args) {
        ListNode<Integer> pHead = new ListNode<>(-1);
        ListNode<Integer> p = pHead;
        for (int i = 1; i < 7; ++i) {
            p.next = new ListNode<>(i);
            p = p.next;
        }

        System.out.println("before reverse list, the list is " + printList(pHead.next));
        ListNode<Integer> nHead = reverseList(pHead.next);
        System.out.println("after reverse list, the list is " + printList(nHead));
    }

    public static <T> String printList(ListNode<T> head) {
        if (head == null) {
            return "";
        }
        ListNode<T> p = head;
        List<String> list = new ArrayList<>();

        while (p != null) {
            list.add(p.val.toString());
            p = p.next;
        }
        return String.join(",", list);
    }

    // 反转链表
    public static ListNode<Integer> reverseList(ListNode<Integer> head) {
        // 1, 2, 3, 4, 5 ,6
        // Todo
        if (head == null || head.next == null) {
            return head;
        }
        
        ListNode<Integer> prev = null, p = head;
        while (p != null) {
            ListNode<Integer> next = p.next;
            p.next = prev;
            prev = p;
            p = next;
        }

        return prev;
    }
    
    public static ListNode<Integer> mergeSortList(ListNode<Integer> head) {
        if (head == null || head.next == null) {
            return head;
        }

        ListNode<Integer> mid = findMid(head);
        ListNode<Integer> left = head, right = mid.next;
        mid.next = null;

        left = mergeSortList(left);
        right = mergeSortList(right);
        return merge(left, right);
    }

    private static ListNode<Integer> merge(ListNode<Integer> left, ListNode<Integer> right) {
        ListNode<Integer> dummy = new ListNode<>(null);
        ListNode<Integer> p1 = left, p2 = right, p = dummy;

        while (p1 != null && p2 != null) {
            if (p1.val < p2.val) {
                p.next = p1;
                p1 = p1.next;
            } else {
                p.next = p2;
                p2 = p2.next;
            }
            p = p.next;
        }

        while (p1 != null) {
            p.next = p1;
            p1 = p1.next;
            p = p.next;
        }

        while (p2 != null) {
            p.next = p2;
            p2 = p2.next;
            p = p.next;
        }

        return dummy.next;
    }

    public static ListNode<Integer> findMid(ListNode<Integer> head) {
        if (head == null || head.next == null) {
            return head;
        }
        
        ListNode<Integer> f = head, s = head;
        while (f.next != null && f.next.next != null) {
            f = f.next.next;
            s = s.next;
        }
        return s;
    }
}

package com.demo.helloag;

import java.util.Arrays;

import org.junit.Test;

public class SortLearnTest {
    @Test 
    public void TestSelectionSort() {
        int[] arr = {3, 1, 4, 1, 5, 9, 2, 6, 5, 3, 5};
        SortLearn.selectionSort(arr);
        System.out.println(Arrays.toString(arr));
    }

    @Test 
    public void TestBubbleSort() {
        int[] arr = {3, 1, 4, 1, 5, 9, 2, 6, 5, 3, 5};
        SortLearn.bubbleSort(arr);
        System.out.println(Arrays.toString(arr));
    }   

    @Test 
    public void TestBubbleSortV2() {
        int[] arr = {3, 1, 4, 1, 5, 9, 2, 6, 5, 3, 5};
        SortLearn.bubbleSortV2(arr);
        System.out.println(Arrays.toString(arr));
    }

    @Test 
    public void TestInsertionSort() {
        int[] arr = {3, 1, 4, 1, 5, 9, 2, 6, 5, 3, 5};
        SortLearn.insertionSort(arr);
        System.out.println(Arrays.toString(arr));
    }

    @Test 
    public void TestQuickSort() {
        int[] arr = {3, 1, 4, 1, 5, 9, 2, 6, 5, 3, 5};
        SortLearn.quickSort(arr);
        System.out.println(Arrays.toString(arr));
    }

    @Test 
    public void TestMergeSort() {
        int[] arr = {3, 1, 4, 1, 5, 9, 2, 6, 5, 3, 5};
        SortLearn.mergeSort(arr);
        System.out.println(Arrays.toString(arr));
    }

    @Test 
    public void TestHeapSort() {
        int[] arr = {3, 1, 4, 1, 5, 9, 2, 6, 5, 3, 5};
        SortLearn.heapSort(arr);
        System.out.println(Arrays.toString(arr));
    }

    @Test 
    public void TestCountingSortNaive() {
        int[] arr = {3, 1, 4, 1, 5, 9, 2, 6, 5, 3, 5};
        SortLearn.countingSortNaive(arr);
        System.out.println(Arrays.toString(arr));
    }

    @Test 
    public void TestCountingSortNaive2() {
        int[] arr = {3, 1, 4, 1, 5, 9, 2, 6, 5, 3, 5};
        SortLearn.countingSortNaive2(arr);
        System.out.println(Arrays.toString(arr));
    }

}

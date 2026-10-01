package com.demo.helloag;

public class BinarySearch {
    public static int binarySearch(int[] arr, int target) {
        int left = 0;
        int right = arr.length - 1;
        while (left <= right) {
            int mid = left + ((right - left) >> 1);
            if (arr[mid] == target) {
                return mid;
            } else if (arr[mid] < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return -1;
    }

    public static int binarySearchInsertionSimple(int[] arr, int target) {
        int left = 0, right = arr.length - 1;
        // 寻找插入位置，那么即第一个大于或等于target的位置
        while (left <= right) {
            int mid = left + ((right - left) >> 1);
            if (arr[mid] < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return left;
    }

    // 二分查找最左的一个target
    public static int binarySearchLeftEdge(int[] arr, int target) {
        int i = binarySearchInsertionSimple(arr, target);
        if (i == arr.length || arr[i] != target) {
            return - 1;
        }
        return i;
    }

    // 二分查找最右的一个target
    public static int binarySearchRightEdge(int[] arr, int target) {
        // 查找下一个元素的左边界
        int i = binarySearchInsertionSimple(arr, target + 1);
        int j = i - 1;

        if (j < 0 || arr[j] != target) {
            return -1;
        }
        return j;
    }

    // 二分查找最右的一个target v2
    public static int binarySearchRightEdgeV2(int[] arr, int target) {
        int i = binarySearchInsertionSimple(arr, target);

        if (i == arr.length || arr[i] != target) {
            return -1;
        }

        int j = i;
        while (j < arr.length && arr[j] == target) {
            ++j;
        }
        return j - 1;
    }
}

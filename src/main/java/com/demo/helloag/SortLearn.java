package com.demo.helloag;

public class SortLearn {
    public static void selectionSort(int[] arr) {
        for (int i = 0; i < arr.length; ++i) {
            int minIdx = i;
            for (int j = i + 1; j < arr.length; ++j) {
                minIdx = arr[j] < arr[minIdx] ? j : minIdx;
            }
            swap(arr, i, minIdx);
        }
    }

    public static void bubbleSort(int[] arr) {
        for (int i = 1; i < arr.length; ++i) {
            for (int j = 0; j < arr.length - i; ++j) {
                if (arr[j] > arr[j + 1]) {
                    swap(arr, j, j + 1);
                }
            }
        }
    }

    // 优化版冒泡排序
    public static void bubbleSortV2(int[] arr) {
        for (int i = 1; i < arr.length; ++i) {
            boolean swapped = false;
            for (int j = 0; j < arr.length - i; ++j) {
                if (arr[j] > arr[j + 1]) {
                    swap(arr, j, j + 1);
                    swapped = true;
                }
            }
            if (!swapped)
                break;
        }
    }

    public static void insertionSort(int[] arr) {
        for (int i = 1; i < arr.length; ++i) {
            int target = arr[i];
            int j = i - 1;
            // 贯彻找插入位置，而不是一直交换值的思路
            while (j >= 0 && arr[j] > target) {
                arr[j + 1] = arr[j];
                --j;
            }
            // 插入位置其实是找第一个大于或等于target的位置
            arr[j + 1] = target;
        }
    }

    public static void quickSort(int[] arr) {
        quickSort(arr, 0, arr.length - 1);
    }

    public static void quickSort(int[] arr, int left, int right) {
        if (left >= right)
            return;
            
        int[] p = partition(arr, left, right);
        quickSort(arr, left, p[0] - 1);
        quickSort(arr, p[1] + 1, right);
    }

    public static int[] partition(int[] arr, int l, int r) {
        int target = arr[r], less = l - 1, more = r;

        while (l < more) {
            if (arr[l] < target) {
                swap(arr, ++less, l++);
            }
            else if (arr[l] > target) {
                swap(arr, --more, l);
            }
            else {
                ++l;
            }
        }

        swap(arr, more, r);
        return new int[] {less + 1, more};
    }

    public static void swap(int[] arr, int i, int j) {
        if (i == j)
            return;
        arr[i] ^= arr[j];
        arr[j] ^= arr[i];
        arr[i] ^= arr[j];
    }

    public static void mergeSort(int[] arr) {
        mergeSort(arr, 0, arr.length - 1);
    }

    public static void mergeSort(int[] arr, int left, int right) {
        if (left >= right)
            return;

        int mid = left + ((right - left) >> 1);
        mergeSort(arr, left, mid);
        mergeSort(arr, mid + 1, right);
        merge(arr, left, mid, right);
    }

    public static void merge(int[] arr, int left, int mid, int right) {
        int[] help = new int[right - left + 1];
        int p1 = left, p2 = mid + 1, p = 0;
        
        while (p1 <= mid && p2 <= right) {
            help[p++] = arr[p1] < arr[p2] ? arr[p1++] : arr[p2++];
        }

        while (p1 <= mid) {
            help[p++] = arr[p1++];
        }

        while (p2 <= right) {
            help[p++] = arr[p2++];
        }

        System.arraycopy(help, 0, arr, left, help.length);
    }

    public static void heapSort(int[] arr) {
        if (arr == null || arr.length < 2)
            return;

        // 1. 构建大根堆 
        int half = (arr.length >> 1) - 1;
        for (int i = half; i >= 0; --i) {
            siftDown(arr, i, arr.length);
        }

        // 2. 排序
        for (int i = arr.length; i > 0; --i) {
            swap(arr, 0, i - 1);
            siftDown(arr, 0, i - 1);
        }
    }

    public static void siftDown(int[] arr, int i, int n) {
        int target = arr[i];
        int k = i;
        while (k < (n >> 1)) {
            int child = (k << 1) + 1;
            int larger = child + 1 < n && arr[child + 1] > arr[child] ? child + 1 : child;

            if (target >= arr[larger]) 
                break;
            arr[k] = arr[larger];
            k = larger;
        }
        arr[k] = target;
    }

    public static void countingSortNaive(int[] arr) {
        if (arr == null || arr.length < 2)
            return;

        int m = arr[0];
        for (int val: arr) {
            m = Math.max(m, val);
        }

        int[] counts = new int[m + 1];
        for (int val: arr) {
            ++counts[val];
        }

        int i = 0;
        for (int num = 0; num < m + 1; ++num) {
            int count = counts[num];
            while (count-- > 0) {
                arr[i++] = num;
            }
        }
    }

    public static void countingSortNaive2(int[] arr) {
        if (arr == null || arr.length < 2)
            return;

        int m = arr[0];
        for (int val: arr) {
            m = Math.max(m, val);
        }

        int[] counts = new int[m + 1];
        for (int val: arr) {
            ++counts[val];
        }

        // 计算前缀和
        for (int i = 1; i < counts.length; ++i) {
            counts[i] += counts[i - 1];
        }

        int[] res = new int[arr.length];
        for (int i = arr.length - 1; i >= 0; --i) {
            res[--counts[arr[i]]] = arr[i];
        }

        System.arraycopy(res, 0, arr, 0, arr.length);
    }
}

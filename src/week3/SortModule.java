package week3;

import java.util.Random;

public class SortModule {

    Random r = new Random();

    public void bubbleSort(int[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = 0; j < arr.length - i - 1; j++) {
                if (arr[j] > arr[j + 1]) {
                    int tmp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = tmp;
                }
            }
        }
    }

    public void selectionSort(int[] arr) {
        for (int i = arr.length - 1; i >= 0; i--) {
            int maxIndex = 0;
            for (int j = 0; j <= i; j++) {
                if (arr[j] > arr[maxIndex])
                    maxIndex = j;
            }
            int tmp = arr[maxIndex];
            arr[maxIndex] = arr[i];
            arr[i] = tmp;
        }
    }

    public void insertionSort(int[] arr) {
        for (int i = 1; i < arr.length; i++) {
            int insertedValue = arr[i];
            int j = i;
            while (j-- > 0) {
                if (arr[j] > insertedValue)
                    arr[j + 1] = arr[j];
            }
            arr[j + 1] = insertedValue;
        }
    }

    public void mergeSort(int[] arr) {
        mergeSort(arr, 0, arr.length);
    }

    private void mergeSort(int[] arr, int start, int end) {
        if (end - start > 1) {
            int mid = (start + end) / 2;
            mergeSort(arr, start, mid);
            mergeSort(arr, mid, end);
            merge(arr, start, mid, end);
        }
    }

    private void merge(int[] arr, int p, int q, int r) {
        int i = p, j = q, k = 0;
        int[] tmp = new int[r - p];

        while (i < q && j < r) {
            if (arr[i] <= arr[j])
                tmp[k++] = arr[i++];
            else
                tmp[k++] = arr[j++];
        }
        while (i < q)
            tmp[k++] = arr[i++];
        while (j < r)
            tmp[k++] = arr[j++];

        for (int t = p, u = 0; t < r; t++)
            arr[t] = tmp[u++];
    }

    public void quickSort(int[] arr, int policy) {
        quickSort(arr, 0, arr.length - 1, policy);
    }

    private void quickSort(int[] arr, int start, int end, int policy) {
        if (start < end) {
            int mid = partition(arr, start, end, policy);
            quickSort(arr, start, mid - 1, policy);
            quickSort(arr, mid + 1, end, policy);
        }
    }

    private int partition(int[] arr, int start, int end, int policy) {
        int p = chooseIndex(arr, start, end, policy);

        int tmp = arr[p];
        arr[p] = arr[end];
        arr[end] = tmp;

        int i = start - 1;
        for (int j = start; j <= end; j++) {
            if (arr[j] <= arr[end]) {
                i++;
                tmp = arr[i];
                arr[i] = arr[j];
                arr[j] = tmp;
            }
        }
        return i;
    }

    private int chooseIndex(int[] arr, int start, int end, int policy) {
        switch (policy) {
            case 1: {
                int p = start;
                if (arr[p] < arr[end])
                    p = end;
                if (arr[p] > arr[(start + end) / 2])
                    p = (start + end) / 2;
                return p;
            }
            case 2:
                return r.nextInt(end - start + 1) + start;
            default:
                return end;
        }
    }
}

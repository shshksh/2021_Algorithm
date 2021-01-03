package week1;

import java.util.Scanner;

public class Task2 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int length = sc.nextInt();
        int[] arr = new int[length];

        for (int i = 0; i < arr.length; i++) {
            arr[i] = sc.nextInt();
        }

        int target = sc.nextInt();

        System.out.println(getFloorOf(arr, target, 0, arr.length - 1));
        System.out.println(getCeilingOf(arr, target, 0, arr.length - 1));
    }

    public static int getFloorOf(int[] arr, int target, int start, int end) {
        if (start > end) {
            if (end < 0) {
                return -1;
            }
            return arr[end];
        }

        int mid = (start + end) / 2;

        if (arr[mid] > target) {
            return getFloorOf(arr, target, start, mid - 1);
        } else if (arr[mid] < target) {
            return getFloorOf(arr, target, mid + 1, end);
        } else {
            return arr[mid];
        }
    }

    public static int getCeilingOf(int[] arr, int target, int start, int end) {
        if (start > end) {
            if (start >= arr.length) {
                return -1;
            }
            return arr[start];
        }

        int mid = (start + end) / 2;

        if (arr[mid] > target) {
            return getCeilingOf(arr, target, start, mid - 1);
        } else if (arr[mid] < target) {
            return getCeilingOf(arr, target, mid + 1, end);
        } else {
            return arr[mid];
        }
    }
}

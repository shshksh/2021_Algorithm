package week1;

import java.util.Scanner;

public class Task1 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int length = sc.nextInt();
        int[] arr = new int[length];

        for (int i = 0; i < arr.length; i++) {
            arr[i] = sc.nextInt();
        }

        int target = sc.nextInt();

        System.out.println(countTwoSum(arr, target, 0, length - 1));
    }

    static int countTwoSum(int[] arr, int target, int start, int end) {
        if (start >= end) {
            return 0;
        }
        int sum = arr[start] + arr[end];

        if (sum == target) {
            return 1 + countTwoSum(arr, target, start + 1, end - 1);
        } else if (sum < target) {
            return countTwoSum(arr, target, start + 1, end);
        } else {
            return countTwoSum(arr, target, start, end - 1);
        }
    }
}

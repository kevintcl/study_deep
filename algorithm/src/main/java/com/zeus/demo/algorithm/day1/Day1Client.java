package com.zeus.demo.algorithm.day1;

/**
 * =======================================
 * Created by kevint on 2019-07-18.
 * =======================================
 * <p>
 * <p>
 * 题目：两数之和 II - 输入有序数组
 * <p>
 * 给定一个已按照升序排列的有序数组，找到两个数使得它们相加之和等于目标数。
 * 函数应该返回这两个下标值 index1 和 index2，其中 index1必须小于index2。
 * <p>
 * 示例：
 * 输入: numbers = [2, 7, 11, 15], target = 9
 * 输出: [1,2]
 * 解释: 2 与 7 之和等于目标数 9 。因此 index1 = 1, index2 = 2 。
 */
public class Day1Client {
    /**
     * @param args
     */
    public static void main(String[] args) {
        int[] numbers = {2, 7, 11, 15};
        int target = 13;

        int[] index = index(numbers, target);

        if (index == null) {
            System.out.println("not found");
        } else {
            System.out.println("index1=" + index[0] + ",index2=" + index[1]);
        }
    }

    private static int[] index(int[] arr, int target) {
        if (arr == null || arr.length <= 1) {
            return null;
        }

        int left = 0;
        int right = arr.length - 1;

        while (left != right) {
            int sum = arr[left] + arr[right];
            if (sum == target) {
                break;
            } else if (sum < target) {
                left++;
            } else {
                right--;
            }
        }

        if (left == right) {
            return null;
        }
        return new int[]{left, right};
    }
}

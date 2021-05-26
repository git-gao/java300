package com.java.interview.algorithm;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class TwoSum {

    public static void main(String[] args) {
        int[] nums = new int[]{2,4,5,6,11};
        System.out.println(Arrays.toString(twoSum(nums,9)));
        System.out.println(Arrays.toString(twoSumSearch(nums,9)));
        System.out.println(Arrays.toString(twoSumPoint(nums,9)));
    }

    public static int[] twoSum(int[] nums, int target) {
        Map<Integer,Integer> map = new HashMap<>();
        // 最多遍历一次，所有的数都会添加到map中
        for (int i=0;i < nums.length;i++) {
            // map 中能找到另一个数，则返回当前下标
            if (map.containsKey(target - nums[i])) {
                return new int[]{map.get(target - nums[i]), i};
            }
            // 如果没找到，则将该数添加到 map 中
            map.put(nums[i], i);
        }

        return new int[0];
    }

    public static int[] twoSumSearch(int[] nums, int target) {
        for (int i = 0; i < nums.length; i++) {
            int low = i;
            int high = nums.length - 1;
            while (low <= high) {
                int mid = (low + high) >>> 1;
                if (nums[mid] == target - nums[i]) {
                    return new int[]{i, mid};
                } else if (nums[mid] > target - nums[i]){
                    // 中间值较大，左移
                    high = mid - 1;
                } else {
                    low = mid + 1;
                }
            }
        }

        return new int[0];
    }

    public static int[] twoSumPoint(int[] nums, int target) {
        int low = 0;
        int high = nums.length - 1;
        while (low < high) {
            int sum = nums[low] + nums[high];
            if (sum == target) {
                return new int[]{low, high};
            } else if (sum < target){
                low++;
            } else {
                high--;
            }
        }

        return new int[0];
    }
}

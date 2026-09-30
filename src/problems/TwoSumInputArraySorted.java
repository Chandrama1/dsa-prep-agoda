package problems;

import java.util.HashMap;
import java.util.Map;

public class TwoSumInputArraySorted {
    // brute force - TLE
    // T: O(n^2)
    // S: O(1)
    public int[] twoSum1(int[] nums, int target) {
        for (int i = 0; i < nums.length - 1; i++) {
            int diff = target - nums[i];

            int j = i + 1;
            while (j <= nums.length - 1 && nums[j] <= diff) {
                if (nums[j] == diff) {
                    return new int[] { i + 1, j + 1 };
                }
                j++;
            }
        }
        return new int[] {};
    }

    // binary search
    // T: O(n * log n)
    // S: O(1)
    public int[] twoSum2(int[] nums, int target) {
        int n = nums.length;

        for (int i = 0; i < n; i++) {
            int num = nums[i];
            int diff = target - num;

            int l = i + 1;
            int r = nums.length - 1;

            while (l <= r) {
                int mid = (l + r) / 2;

                if (diff == nums[mid]) {
                    return new int[] { i + 1, mid + 1};
                } else if (diff < nums[mid]) {
                    r = mid - 1;
                } else {
                    l = mid + 1;
                }
            }
        }

        return new int[] {};
    }

    // hashmap
    // T: O(n)
    // S: O(n)
    public int[] twoSum3(int[] nums, int target) {
        int n = nums.length;
        Map<Integer, Integer> map = new HashMap<>();

        for (int i = 0; i < n; i++) {
            int num = nums[i];
            int diff = target - num;

            if (map.containsKey(num)) {
                return new int[] { map.get(num) + 1, i + 1 };
            }
            map.put(diff, i);
        }

        return new int[] {};
    }

    // two pointers
    // T: O(n)
    // S: O(1)
    public int[] twoSum4(int[] nums, int target) {
        int i = 0;
        int j = nums.length - 1;

        while (i < j) {
            int sum = nums[i] + nums[j];

            if (sum == target) {
                return new int[] { i + 1, j + 1};
            } else if (sum < target) {
                i++;
            } else {
                j--;
            }
        }

        return new int[] {};
    }
}

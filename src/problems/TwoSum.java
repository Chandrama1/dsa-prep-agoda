package problems;

import java.util.Arrays;
import java.util.HashMap;

public class TwoSum {

    // brute force
    // T: O(n^2)
    // S: O(1)
    public int[] twoSum1(int[] nums, int target) {
        for (int i = 0; i < nums.length; i++) {
            int n1 = nums[i];
            for (int j = i + 1; j < nums.length; j++) {
                if (n1 + nums[j] == target) {
                    return new int[]{i, j};
                }
            }
        }
        return new int[]{};
    }

    // sorting
    // T: O(n)
    // S: O(n)
    public int[] twoSum2(int[] nums, int target) {
        int n = nums.length;
        int[][] arr = new int[n][2];

        for(int i = 0; i < n; i++) {
            arr[i][0] = nums[i];
            arr[i][1] = i;
        }

        Arrays.sort(arr, (a, b) -> Integer.compare(a[0], b[0]));

        int i = 0;
        int j = nums.length - 1;

        while(i < j) {
            int sum = arr[i][0] + arr[j][0];

            if (sum == target) {
                return new int[] { arr[i][1], arr[j][1] };
            } else if (sum > target) {
                j--;
            } else {
                i++;
            }
        }
        return new int[] {};
    }

    // T: O(n)
    // S: O(n)
    // two-pass hash table
    public int[] twoSum3(int[] nums, int target) {
        HashMap<Integer, Integer> map = new HashMap();
        for (int i = 0; i < nums.length; i++) {
            int diff = target - nums[i];
            map.put(diff, i);
        }

        for (int i = 0; i < nums.length; i++) {
            if (map.containsKey(nums[i]) && map.get(nums[i]) != i) {
                return new int[]{i, map.get(nums[i])};
            }
        }
        return new int[]{};
    }

    public int[] twoSum4(int[] nums, int target) {
        HashMap<Integer, Integer> map = new HashMap();
        for (int i=0;i<nums.length; i++){
            map.put(nums[i], i);
        }

        for(int i=0; i<nums.length;i++) {
            int diff=target-nums[i];
            if (map.containsKey(diff) && map.get(diff) != i) {
                return new int[] {i,map.get(diff)};
            }
        }
        return new int[] {};
    }

    // one pass hash table
    // T: O(n)
    // S: O(n)
    public int[] twoSum5(int[] nums, int target) {
        HashMap<Integer, Integer> map = new HashMap();
        for (int i = 0; i < nums.length; i++) {
            int diff = target - nums[i];
            if (map.containsKey(nums[i])) {
                return new int[]{i, map.get(nums[i])};
            }
            map.put(diff, i);
        }
        return new int[]{};
    }
}

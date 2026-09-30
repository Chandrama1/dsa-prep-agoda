package problems;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class LongestConsecutiveSequence {
    public static int longestConsecutive(int[] nums) {
        int n = nums.length;
        if (nums.length == 0)
            return 0;

        int res = 1;
        int count = 1;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (nums[i] != nums[j] && i != j) {
                    if (nums[j] - nums[i] == 1) {
                        count++;
                        res = Math.max(res, count);
                    } else {
                        res = 1;
                    }
                    System.out.printf("%d, %d, %d\n", nums[i], nums[j], count, res);
                }
            }
        }

        return res;
    }

    // brute force
    // T: O(n^2)
    // S: O(n)
    public int longestConsecutive1(int[] nums) {
        if (nums.length == 0)
            return 0;

        int res = 0;
        Set<Integer> set = new HashSet<>();
        for (int i = 0; i < nums.length; i++) {
            set.add(nums[i]);
        }

        for (int i = 0; i < nums.length; i++) {
            int streak = 0;
            int curr = nums[i];

            while(set.contains(curr)) {
                streak++;
                curr++;
            }
            res = Math.max(res, streak);
        }
        return res;
    }

    // sorting
    // T: O(n * log n)
    // S: O(1)
    public static int longestConsecutive2(int[] nums) {
        int res = 0;
        int max = 0;

        Arrays.sort(nums);
        System.out.println(Arrays.toString(nums));
        for (int i = 0; i < nums.length - 1; i++) {
            int diff = nums[i + 1] - nums[i];
            if (diff != 0) {
                if (diff == 1) {
                    res++;
                    max = Math.max(max, res);
                } else  {
                    res = 1;
                }
            }
            System.out.println("num: " + nums[i] + ", res: " + res + ", max: " + max);
        }
        return max;
    }

    // hashset (optimised)
    // T: O(n)
    // S: O(n)
    public int longestConsecutive3(int[] nums) {
        if (nums.length == 0)
            return 0;

        int res = 0;
        Set<Integer> set = new HashSet<>();
        for (int i = 0; i < nums.length; i++) {
            set.add(nums[i]);
        }

        for (int i = 0; i < nums.length; i++) {
            if (!set.contains(nums[i] - 1)) {
                int streak = 0;
                int curr = nums[i];

                while(set.contains(curr)) {
                    streak++;
                    curr++;
                }
                res = Math.max(res, streak);
            }
        }
        return res;
    }
}

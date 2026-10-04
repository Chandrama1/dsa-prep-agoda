package problems;

public class MinimumInRotatedSortedArray {
    public int findMin1(int[] nums) {
        int min = Integer.MAX_VALUE;

        for (int num: nums) {
            min = Math.min(min, num);
        }
        return min;
    }

    // find the pivot
    public int findMin2(int[] nums) {
        int n = nums.length;
        if (nums[0] <= nums[n - 1])
            return nums[0];

        for (int i = n - 1; i >= 1; i--) {
            if (nums[i] < nums[i - 1])
                return nums[i];
        }

        return nums[0];
    }

    // binary search
    public int findMinBinary(int[] nums) {
        int n = nums.length;
        int l = 0, r = n - 1;
        int res = nums[0];

        while (l <= r) {
            if (nums[l] < nums[r]) {
                res = Math.min(res, nums[l]);
                break;
            }

            int m = l + (r - l) / 2;
            res = Math.min(res, nums[m]);

            if (nums[m] >= nums[l]) {
                l = m + 1;
            } else {
                r = m - 1;
            }
        }
        return res;
    }

    public int findMinLowerBound(int[] nums) {
        int n = nums.length;
        int l = 0, r = n - 1;

        while (l < r) {
            int m = l + (r - l) / 2;

            if (nums[m] < nums[r]) {
                r = m;
            } else {
                l = m + 1;
            }
        }

        return nums[l];
    }
}

package problems;

public class SearchInsertPosition {
    // brute force - linear search
    // T: O(n)
    // S: O(1)
    public int searchInsertLinear1(int[] nums, int target) {
        int n = nums.length;
        int i = 0;

        while (i < n && nums[i] <= target) {
            if (nums[i] == target)
                return i;
            i++;
        }

        return i;
    }

    // brute force - linear search
    // T: O(n)
    // S: O(1)
    public int searchInsertLinear2(int[] nums, int target) {
        int n = nums.length;

        for (int i = 0; i < n; i++) {
            if (nums[i] >= target) {
                return i;
            }
        }

        return n;   // all elements < target
    }

    public int searchInsertBinary1(int[] nums, int target) {
        int n = nums.length;
        int l = 0, r = n - 1;
        int res = 0;

        while (l <= r) {
            int m = l + (r - l) / 2;

            if (nums[m] == target) {
                return m;
            }
            else if (nums[m] > target){
                res = m;
                r = m - 1;
            } else {
                l = m + 1;
            }

        }

        return res;
    }

    public int searchInsertBinary2(int[] nums, int target) {
        int n = nums.length;
        int l = 0, r = n - 1;

        while (l <= r) {
            int m = l + (r - l) / 2;

            if (nums[m] == target) {
                return m;
            } else if (nums[m] > target){
                r = m - 1;
            } else {
                l = m + 1;
            }

        }

        return l;
    }

    public int searchInsertUpperBound(int[] nums, int target) {
        int n = nums.length;
        int l = 0, r = n;

        while (l < r) {
            int m = l + (r - l) / 2;

            if (nums[m] > target) {
                r = m;
            } else {
                l = m + 1;
            }
        }

        if (l > 0 && nums[l - 1] == target) {
            return l - 1;
        }

        return l;
    }

    public int searchInsertLowerBound(int[] nums, int target) {
        int n = nums.length;
        int l = 0, r = n;
        int res = 0;

        while (l < r) {
            int m = l + (r - l) / 2;

            if (nums[m] >= target) {
                r = m;
            } else {
                l = m + 1;
            }
        }

        return l;
    }
}

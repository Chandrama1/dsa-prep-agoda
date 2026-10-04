package problems;

public class SearchRotatedSortedArray {
    public int searchLinear(int[] nums, int target) {
        int n = nums.length;

        for (int i = 0; i < n; i++) {
            if (nums[i] == target)
                return i;
        }

        return -1;
    }

    public int searchBinary(int[] nums, int target) {
        int n = nums.length;
        int l = 0, r = n - 1;

        while (l < r) {
            int m = l + (r - l) / 2;

            if(nums[m] > nums[r]) {
                l = m + 1;
            } else {
                r = m;
            }
        }

        int pivot = l;

        int result = binarySearch(nums, target, 0, pivot - 1);
        if (result != -1)
            return result;

        return binarySearch(nums, target, pivot, n - 1);
    }

    public int searchBinary2Pass(int[] nums, int target) {
        int n = nums.length;
        int l = 0, r = n - 1;

        while (l < r) {
            int m = l + (r - l) / 2;

            if(nums[m] > nums[r]) {
                l = m + 1;
            } else {
                r = m;
            }
        }

        int pivot = l;

        if (target >= nums[0]) {
            l = 0;
            r = pivot > 0 ? pivot - 1 : n - 1;
        } else {
            l = pivot;
            r = n - 1;
        }

        return binarySearch(nums, target, l, r);
    }

    public int searchBinary2PassSimpler(int[] nums, int target) {
        int n = nums.length;
        int l = 0, r = n - 1;

        while (l < r) {
            int m = l + (r - l) / 2;

            if(nums[m] > nums[r]) {
                l = m + 1;
            } else {
                r = m;
            }
        }

        int pivot = l;
        l = 0;
        r = n - 1;

        // handles non-rotated array
        // handles array of size 1
        if (target >= nums[pivot] && target <= nums[r]) {
            l = pivot;
        } else {
            r = pivot - 1;
        }

        return binarySearch(nums, target, l, r);
    }

    public int searchBinary1Pass(int[] nums, int target) {
        int n = nums.length;
        int l = 0, r = n - 1;

        while (l <= r) {
            int m = l + (r - l) / 2;

            if (nums[m] == target)
                return m;

            if (nums[l] <= nums[m]) {
                if (target > nums[m] || target < nums[l])
                    l = m + 1;
                else
                    r = m - 1;
            } else {
                if (target < nums[m] || target > nums[r])
                    r = m - 1;
                else
                    l = m + 1;
            }
        }
        return -1;
    }

    public static int binarySearch(int[] nums, int target, int l, int r) {
        while (l <= r) {
            int m = l + (r - l) / 2;

            if (nums[m] == target)
                return m;

            if (nums[m] > target)
                r = m - 1;
            else
                l = m + 1;
        }
        return -1;
    }

    public static int search(int[] nums, int target) {
        int n = nums.length;
        int l = 0, r = n - 1;

        while (l <= r) {
            int m = l + (r - l) / 2;

            if (nums[m] == target)
                return m;

            if (nums[m] > target)
                r = m - 1;
            else
                l = m + 1;
        }
        return -1;
    }
}

package problems;

public class BinarySearch {
    // T: log n
    // S: O(1)
    public static int searchIterative(int[] nums, int target) {
        int n = nums.length;
        int start = 0, end = n - 1;

        while (start <= end) {
//            int mid = (start + end) / 2;
            int mid = start + (end - start) / 2;    // handle integer overflow

            if (nums[mid] == target) {
                return mid;
            }
            if (nums[mid] < target) {
                start = mid + 1;
            } else {
                end = mid - 1;
            }
        }
        return -1;
    }

    // T: log n
    // S: O(log n)
    public int searchRecursive(int[] nums, int target) {
        int n = nums.length;

        return binarySearch(nums, target, 0, n - 1);
    }

    public int binarySearch(int[] nums, int target, int start, int end) {
        if (start > end)
            return -1;

        int mid = start + ((end - start) / 2);

        if (nums[mid] == target) {
            return mid;
        }
        if (nums[mid] < target) {
            return binarySearch(nums, target, mid + 1, end);
        }

        return binarySearch(nums, target, start, mid - 1);
    }

    // T: log n
    // S: O(1)
    public static int searchUpperBound(int[] nums, int target) {
        int l = 0, r = nums.length;

        while (l < r) {
            int m = l + ((r - l) / 2);
            System.out.printf("l=%d m=%d r=%d\n", l, m, r);
            if (nums[m] > target) {
                r = m;
            } else {
                l = m + 1;
            }
        }
        return (l > 0 && nums[l - 1] == target) ? l - 1 : -1;
    }

    // T: log n
    // S: O(1)
    public int searchLowerBound(int[] nums, int target) {
        int n = nums.length;
        int l = 0, r = n;

        while (l < r) {
            int m = l + ((r - l) / 2);
            if (nums[m] >= target) {
                r = m;
            } else {
                l = m + 1;
            }
        }
        return (l < n && nums[l] == target) ? l : -1;
    }
}

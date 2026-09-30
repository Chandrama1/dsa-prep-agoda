package problems;

public class ProductOfArrayExceptSelf {

    // brute force
    // T: O(n*2)
    // S: O(1)
    public static int[] productExceptSelf1(int[] nums) {
        int n = nums.length;
        int[] res = new int[n];
        for (int i = 0; i < n; i++) {
            int prod = 1;
            for (int j = 0; j < n; j++) {
                if (i != j){
                    prod *= nums[j];
                }
            }
            res[i] = prod;
        }
        return res;
    }


    // using division
    public int[] productExceptSelf2(int[] nums) {
        int n = nums.length;
        int[] res = new int[n];
        int prod = 1;
        int zeroCount = 0;
        for (int i = 0; i < n; i++) {
            if (nums[i] == 0) {
                zeroCount++;
            } else {
                prod *= nums[i];
            }
        }

        if (zeroCount > 1) {
            return res;
        }

        for (int i = 0; i < n; i++) {
            int num = nums[i];
            if (num == 0) {
                if (zeroCount == 1) {
                    res[i] = prod;
                }
            } else if (zeroCount == 0) {
                res[i] = prod / num;
            }
        }
        return res;
    }

    // using division (better)
    // T: O(n)
    // S: O(1)
    public int[] productExceptSelf3(int[] nums) {
        int n = nums.length;
        int[] res = new int[n];
        int prod = 1;
        int zeroCount = 0;
        for (int i = 0; i < n; i++) {
            if (nums[i] == 0) {
                zeroCount++;
            } else {
                prod *= nums[i];
            }
        }

        if (zeroCount > 1) {
            return res;
        }

        for (int i = 0; i < n; i++) {
            int num = nums[i];
            if (zeroCount > 0) {
                if (nums[i] == 0) {
                    res[i] = prod;
                }
            } else {
                res[i] = prod / num;
            }
        }
        return res;
    }


    // prefix and suffix
    // T: O(n)
    // S: O(n)
    public int[] productExceptSelf4(int[] nums) {
        int n = nums.length;
        int[] res = new int[n];
        int[] pre = new int[n];
        int[] suf = new int[n];

        pre[0] = 1;
        suf[n - 1] = 1;
        for(int i = 1; i < n; i++) {
            pre[i] = pre[i - 1] * nums[i - 1];
        }

        for(int i = n - 2; i >= 0; i--) {
            suf[i] = suf[i + 1] * nums[i + 1];
        }

        for(int i = 0; i < n; i++) {
            res[i] = pre[i] * suf[i];
        }

        return res;
    }

    // prefix and suffix (optimal)
    // T: O(n)
    // S: O(1)
    public int[] productExceptSelf5(int[] nums) {
        int n = nums.length;
        int[] res = new int[n];

        int pre = 1;
        for(int i = 0; i < n; i++) {
            res[i] = pre;
            pre *= nums[i];
        }

        int suf = 1;
        for(int i = n - 1; i >= 0; i--) {
            res[i] *= suf;
            suf *= nums[i];
        }

        return res;
    }
}

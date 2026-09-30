package problems;

import java.util.*;

public class ThreeSum {
    // brute force
    // T: O(n^3 + n * log n) = O(n^3)
    // S: O(n^2) -> no. of unique triplets (worst case) nC3
    public List<List<Integer>> threeSum1(int[] nums) {
        Set<List<Integer>> set = new HashSet<>();

        // to handle duplicates
        // alternative: sort the triplets; doesn't impact time complexity
        Arrays.sort(nums);

        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                int twoSum = nums[i] + nums[j];
                for (int k = j + 1; k < nums.length; k++) {
                    if (twoSum == -nums[k])
                        set.add(Arrays.asList(nums[i], nums[j], nums[k]));
                }
            }
        }

        return new ArrayList<>(set);
    }

    // hash map - IGNORE
    // T: O(n^2 + n * log n) = O(n^2)
    // S: O(n)
    // gives duplicate triplets
    // commenting continue statements fails [0,0,0] and [0,0,0,0]
    public static List<List<Integer>> threeSum2(int[] nums) {
        List<List<Integer>> list = new ArrayList<>();
        Arrays.sort(nums);  // to handle duplicates

        int n = nums.length;
        System.out.println(Arrays.toString(nums));
        for (int i = 0; i < n; i++) {
            System.out.printf("i=%d\n", i);
            if (nums[i] > 0) break;
            if (i > 0 && nums[i] == nums[i - 1]) continue;
            Set<Integer> set = new HashSet<>();
            for (int j = i + 1; j < n; j++) {
                if (j > i + 1 && nums[j] == nums[j - 1]) continue;
                int target = -(nums[i] + nums[j]);
                System.out.printf("nums[i]=%d nums[j]=%d target=%d\n", nums[i], nums[j], target);
                System.out.println(set);
                if (set.contains(target)) {
                    list.add(Arrays.asList(nums[i], nums[j], target));
                }
                set.add(nums[j]);
            }
        }
        return new ArrayList<>(list);
    }

    // hash map - IGNORE
    // T: O(n^2)
    // S: O(n)
    public static List<List<Integer>> threeSum3(int[] nums) {
        Set<List<Integer>> uniqueTriplets = new HashSet<>();

        int n = nums.length;
        for (int i = 0; i < n; i++) {
            Set<Integer> set = new HashSet<>();

            for (int j = i + 1; j < n; j++) {
                int target = -(nums[i] + nums[j]);

                if (set.contains(target)) {
                    List<Integer> triplet = Arrays.asList(nums[i], nums[j], target);
                    Collections.sort(triplet);
                    uniqueTriplets.add(triplet);
                }
                set.add(nums[j]);
            }
        }
        return new ArrayList<>(uniqueTriplets);
    }

    // two pointer
    // T: O(n^2 + n * log n) = O(n^2)
    // S: O(1) plus sorting algo space
    public static List<List<Integer>> threeSum4(int[] nums) {
        List<List<Integer>> list = new ArrayList<>();
        Arrays.sort(nums);  // to handle duplicates

        int n = nums.length;

        for (int i = 0; i < n; i++) {
            if (nums[i] > 0) break; // sum must be >= 0
            if (i > 0 && nums[i] == nums[i - 1]) continue;  // skip duplicate i
            int target = -nums[i];

            int l = i + 1;
            int r = n - 1;

            while (l < r) {
                int sum = nums[l] + nums[r];
                if (sum == target) {
                    list.add(Arrays.asList(nums[i], nums[l], nums[r]));
                    l++;
                    r--;
                    while (l < r && nums[l] == nums[l - 1]) l++;
                } else if (sum < target) {
                    l++;
                } else {
                    r--;
                }
            }
        }

        return new ArrayList<>(list);
    }
}

package problems;

import java.util.Arrays;
import java.util.HashSet;

public class ContainsDuplicate {
    // brute force
    public boolean containsDuplicate1(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[i] == nums[j])
                    return true;
            }
        }
        return false;
    }

    // sorting
    public boolean containsDuplicate2(int[] nums) {
        Arrays.sort(nums);
        for (int i = 0; i < nums.length - 1; i++) {
            if (nums[i] == nums[i + 1])
                return true;
        }
        return false;
    }

    // hashset
    public boolean containsDuplicate3(int[] nums) {
        HashSet<Integer> set = new HashSet<>();

        for (int i = 0; i < nums.length; i++) {
            if (set.contains(nums[i]))
                return true;
            set.add(nums[i]);
        }

        return false;
    }
}

package problems;

import java.util.Arrays;

public class MergeSortedArray {
    // brute force (sorting)
    // T: O((m + n) * log(m + n))
    // S: O(1) or O(m + n) depending on the sorting algorithm
    public void merge1(int[] nums1, int m, int[] nums2, int n) {
        for (int i = 0; i < n; i++) {
            nums1[i + m] = nums2[i];
        }
        Arrays.sort(nums1);
    }

    // three pointers with extra space
    // T: O(m + n)
    // S: O(m + n)
    public static void merge2(int[] nums1, int m, int[] nums2, int n) {
        int i = 0, j = 0, k = 0;
        int[] temp = new int[m + n];

        while (k < m + n) {
            if (j >= n || (i < m && nums1[i] <= nums2[j])) {
                temp[k++] = nums1[i++];
            } else {
                temp[k++] = nums2[j++];
            }
        }

        for(int index = 0; index < k; index++) {
            nums1[index] = temp[index];
        }
    }

    // three pointers with extra space - simpler
    // T: O(m + n)
    // S: O(m + n)
    public void merge3(int[] nums1, int m, int[] nums2, int n) {
        int i = 0, j = 0, k = 0;
        int[] temp = new int[m + n];

        while (i < m && j < n) {
            if (nums1[i] <= nums2[j]) {
                temp[k++] = nums1[i++];
            } else {
                temp[k++] = nums2[j++];
            }
        }

        while (i < m) {
            temp[k++] = nums1[i++];
        }

        while (j < n) {
            temp[k++] = nums2[j++];
        }

        System.arraycopy(temp, 0, nums1, 0, k);
    }

    // three pointers with extra space
    // T: O(m + n)
    // S: O(m)
    public void merge4(int[] nums1, int m, int[] nums2, int n) {
        int i = 0, j = 0, k = 0;
        int[] temp = Arrays.copyOf(nums1, m);

        while (k < m + n) {
            if (j >= n || (i < m && temp[i] <= nums2[j])) {
                nums1[k++] = temp[i++];
            } else {
                nums1[k++] = nums2[j++];
            }
        }
    }

    // three pointers without extra space
    // T: O(m + n)
    // S: O(1)
    public void merge5(int[] nums1, int m, int[] nums2, int n) {
        int i = m - 1, j = n - 1, k = m + n - 1;

        while (k >= 0) {
            if (j < 0 || (i >= 0 && nums1[i] >= nums2[j])) {
                nums1[k--] = nums1[i--];
            } else {
                nums1[k--] = nums2[j--];
            }
        }
    }

    // three pointers without extra space - simpler
    // T: O(m + n)
    // S: O(1)
    public void merge6(int[] nums1, int m, int[] nums2, int n) {
        int i = m - 1, j = n - 1, k = m + n - 1;

        while (i >= 0 && j >= 0) {
            if (nums1[i] >= nums2[j]) {
                nums1[k--] = nums1[i--];
            } else {
                nums1[k--] = nums2[j--];
            }
        }

        while (i >= 0) {
            nums1[k--] = nums1[i--];
        }

        while (j >= 0) {
            nums1[k--] = nums2[j--];
        }
    }

    // three pointers without extra space - optimised
    // T: O(m + n)
    // S: O(1)
    public void merge7(int[] nums1, int m, int[] nums2, int n) {
        int i = m - 1, j = n - 1, k = m + n - 1;

        while (j >= 0) {
            if (i >= 0 && nums1[i] >= nums2[j]) {
                nums1[k--] = nums1[i--];
            } else {
                nums1[k--] = nums2[j--];
            }
        }
    }
}

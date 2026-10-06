package problems;

import java.util.*;

public class MinimumAbsoluteDifference {
    // brute force
    // T: O(n^2)
    // T: O(n^2 + log n) = O(n^2)
    public List<List<Integer>> minimumAbsDifference1(int[] arr) {
        int n = arr.length;
        Map<Integer, List<List<Integer>>> map = new HashMap<>();
        int minDiff = Integer.MAX_VALUE;

        Arrays.sort(arr);

        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                int diff = Math.abs(arr[i] - arr[j]);
                minDiff = Math.min(minDiff, diff);

                if (map.containsKey(diff)) {
                    List<List<Integer>> pairs = map.get(diff);
                    pairs.add(Arrays.asList(arr[i], arr[j]));
                    map.put(diff, pairs);
                } else {
                    List<List<Integer>> pairs = new ArrayList<>();
                    pairs.add(Arrays.asList(arr[i], arr[j]));
                    map.put(diff, pairs);
                }
            }
        }

        return map.get(minDiff);
    }

    // sorting
    // T: O(n * log n + n) = O(n * log n)
    // T: O(1) or O(n * log n)
    public List<List<Integer>> minimumAbsDifference2(int[] arr) {
        int n = arr.length;
        List<List<Integer>> res = new ArrayList<>();
        int minDiff = Integer.MAX_VALUE;

        Arrays.sort(arr);

        for (int i = 0; i < n - 1; i++) {
            int diff = Math.abs(arr[i] - arr[i + 1]);

            if (diff < minDiff) {
                minDiff = diff;
                res.clear();
            }

            if (diff <= minDiff)
                res.add(Arrays.asList(arr[i], arr[i + 1]));
        }
        return res;
    }

    // frequency/counting approach
    // T: O(n + r) -> r - range of arr elements
    // T: O(1)
    public List<List<Integer>> minimumAbsDifference3(int[] arr) {
        int n = arr.length;
        List<List<Integer>> res = new ArrayList<>();
        int minDiff = Integer.MAX_VALUE;

        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;

        for (int num: arr) {
            min = Math.min(min, num);
            max = Math.max(max, num);
        }

        int k = max - min + 1;
        boolean[] present = new boolean[k];

        for (int num: arr) {
            present[num - min] = true;
        }

        boolean hasPrev = false;
        int prev = 0;

        for (int i = 0; i < present.length; i++) {
            if (!present[i])
                continue;

            int num = i + min;

            if (hasPrev) {
                int diff = Math.abs(num - prev);

                if (diff < minDiff) {
                    minDiff = diff;
                    res.clear();
                }

                if (diff <= minDiff)
                    res.add(Arrays.asList(prev, num));
            }

            prev = num;
            hasPrev = true;
        }
        return res;
    }
}

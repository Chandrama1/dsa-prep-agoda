package problems;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class MergeIntervals {
    // brute force
    // T; O(n^2)
    // S: O(n)
    public int[][] merge(int[][] intervals) {
        List<int[]> result = new ArrayList<>();

        for (int[] interval: intervals) {
            int start = interval[0];
            int end = interval[1];

            Iterator<int[]> it = result.iterator();

            while (it.hasNext()) {
                int[] cur = it.next();

                // Two intervals overlap if neither ends before the other starts
                if (start <= cur[1] && cur[0] <= end) {
                    start = Math.min(start, cur[0]);
                    end = Math.max(end, cur[1]);
                    it.remove();
                }
            }

            result.add(new int[] { start, end });
        }

        return result.toArray(new int[result.size()][]);
    }

    // by sorting
    // T: O(n * log n + n) = O(n * log n)
    // S: O(n)
    public int[][] mergeBySorting(int[][] intervals) {
        Arrays.sort(intervals, (a, b) -> a[0] - b[0]);

        int n = intervals.length;
        List<int[]> list = new ArrayList<>();
        list.add(intervals[0]);

        for (int i = 1; i < n; i++) {
            int[] lastInterval = list.get(list.size() - 1);

            if (lastInterval[1] >= intervals[i][0]) {
                lastInterval[1] = Math.max(intervals[i][1], lastInterval[1]);
            } else {
                list.add(intervals[i]);
            }
        }

        return list.toArray(new int[list.size()][2]);

        // int[][] res = new int[list.size()][2];
        // for (int i = 0; i < list.size(); i++) {
        //     res[i] = list.get(i);
        // }

        // return res;
    }
}

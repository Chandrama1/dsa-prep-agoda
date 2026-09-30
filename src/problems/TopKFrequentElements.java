package problems;

import java.util.*;

public class TopKFrequentElements {
    // brute force
    // T: O(n * log n)
    // S: O(n)
    public int[] topKFrequent1(int[] nums, int k) {
        int[] res = new int[k];
        int n = nums.length;
        Map<Integer, Integer> map = new HashMap<>();

        for (int i = 0; i < n; i++) {
            int num = nums[i];
            map.put(num, map.getOrDefault(num, 0) + 1);
        }

        List<int[]> list = new ArrayList<>();
        for (Map.Entry<Integer, Integer> entry: map.entrySet()) {
            int freq = entry.getValue();
            int num = entry.getKey();
            list.add(new int[] { freq, num });
        }

        list.sort((a, b) -> b[0] - a[0]);

        for (int i = 0; i < k; i++) {
            res[i] = list.get(i)[1];
        }

        return res;
    }

    // min heap
    // T: O(n * log k)
    // S: O(n + k)
    public int[] topKFrequent2(int[] nums, int k) {
        int n = nums.length;
        Map<Integer, Integer> counts = new HashMap<>();
        int[] res = new int[k];

        for (int i = 0; i < n; i++) {
            counts.put(nums[i], counts.getOrDefault(nums[i], 0) + 1);
        }

        PriorityQueue<int[]> minHeap = new PriorityQueue<>((a, b) -> a[0] - b[0]);
        for (Map.Entry<Integer, Integer> entry: counts.entrySet()) {
            int freq = entry.getValue();
            int num = entry.getKey();
            minHeap.offer(new int[] { freq, num });

            if (minHeap.size() > k) {
                minHeap.poll();
            }
        }

        for (int i = 0; i < k; i++) {
            res[i] = minHeap.poll()[1];
        }

        return res;
    }


    // bucket sort
    // T: O(n)
    // S: O(n)
    public int[] topKFrequent3(int[] nums, int k) {
        int[] res = new int[k];
        int n = nums.length;
        Map<Integer, Integer> map = new HashMap<>();
        List<Integer>[] freqGroups = new List[n + 1];

        for (int i = 0; i < n; i++) {
            int num = nums[i];
            map.put(num, map.getOrDefault(num, 0) + 1);
        }

        for (Map.Entry<Integer, Integer> entry: map.entrySet()) {
            int freq = entry.getValue();
            int num = entry.getKey();

            freqGroups[freq].add(num);
        }

        int j = 0;
        for (int i = n; i >= 0 && j < k; i--) {
            List<Integer> group = freqGroups[i];
            for (Integer num: group) {
                if (j < k)
                    res[j++] = num;
            }
        }

        return res;
    }
}

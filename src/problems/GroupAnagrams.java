package problems;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class GroupAnagrams {
    // brute force - ignore
    public List<List<String>> groupAnagrams1(String[] strs) {
        List<List<String>> res = new ArrayList<>();
        HashMap<String, List<String>> map = new HashMap<>();

        for (int i = 0; i < strs.length; i++) {
            char[] chArr1 = strs[i].toCharArray();
            Arrays.sort(chArr1);
            String sorted1 = new String(chArr1);

            if (!map.containsKey(sorted1)) {
                List<String> list = new ArrayList<>();
                list.add(strs[i]);
                for (int j = i + 1; j < strs.length; j++) {
                    char[] chArr2 = strs[j].toCharArray();
                    Arrays.sort(chArr2);

                    if (Arrays.equals(chArr1, chArr2)) {
                        list.add(strs[j]);
                    }
                }
                map.put(sorted1, list);
            }
        }

        for(List<String> value: map.values()) {
            res.add(value);
        }
        return res;
    }

    // brute force
    public List<List<String>> groupAnagrams2(String[] strs) {
        List<List<String>> res = new ArrayList<>();
        HashMap<String, List<String>> map = new HashMap<>();

        for (int i = 0; i < strs.length; i++) {
            String str = strs[i];
            char[] chArr = str.toCharArray();
            Arrays.sort(chArr);
            String sorted = new String(chArr);

//            List<String> list = map.getOrDefault(sorted, new ArrayList<>());
//            list.add(str);
//            map.put(sorted, list);

            map.putIfAbsent(sorted, new ArrayList<>());
            map.get(sorted).add(str);
        }

        for(List<String> value: map.values()) {
            res.add(value);
        }
        return res;
    }

    // optimised
    // T: O(m * n)
    // S:
    public List<List<String>> groupAnagrams3(String[] strs) {
//        List<List<String>> res = new ArrayList<>();
        HashMap<String, List<String>> map = new HashMap<>();

        for (int i = 0; i < strs.length; i++) {
            String str = strs[i];
            int[] counts = new int[26];

            for (char ch: str.toCharArray()) {
                counts[ch - 'a']++;
            }

            String countKey = Arrays.toString(counts);
            map.putIfAbsent(countKey, new ArrayList<>());
            map.get(countKey).add(str);
        }

        // for(List<String> value: map.values()) {
        //     res.add(value);
        // }
        return new ArrayList<>(map.values());
    }
}

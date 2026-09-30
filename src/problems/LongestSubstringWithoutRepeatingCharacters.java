package problems;

import java.util.*;

public class LongestSubstringWithoutRepeatingCharacters {
    // brute force
    // T: O(n^2)
    // S: O(n)
    public int lengthOfLongestSubstring1(String s) {
        int n = s.length();
        char[] chArr = s.toCharArray();
        int res = 0;

        for (int i = 0; i < n; i++) {
            Set<Character> set = new HashSet<>();
            for (int j = i; j < n; j++) {
                if (set.contains(chArr[j]))
                    break;

                set.add(chArr[j]);
                res = Math.max(res, set.size());
            }
        }
        return res;
    }

    // sliding window
    // T: O(n)
    // S: O(m) -> m: count of unique characters
    public static int lengthOfLongestSubstring2(String s) {
        int n = s.length();
        char[] chArr = s.toCharArray();
        int res = 0;
        int l = 0, r = 0;

        Set<Character> set = new HashSet<>();

        while (r < n) {
            while (set.contains(chArr[r])) {
                set.remove(chArr[l++]);
            }
            set.add(chArr[r]);
            res = Math.max(res, set.size());
//            res = Math.max(res, r - l + 1);
            r++;
        }

        return res;
    }

    // sliding window
    // T: O(n)
    // S: O(m) -> m: count of unique characters
    public int lengthOfLongestSubstring3(String s) {
        int n = s.length();
        char[] chArr = s.toCharArray();
        int res = 0;
        int l = 0, r = 0;

        Map<Character, Integer> map = new HashMap<>();

        while (r < n) {
            if (map.containsKey(chArr[r]) && map.get(chArr[r]) >= l) {
                l = map.get(chArr[r]) + 1;
            }
            map.put(chArr[r], r);
            res = Math.max(res, r - l + 1);
            r++;
        }

        return res;
    }

    // sliding window - optimal
    // T: O(n)
    // S: O(m) -> m: count of unique characters
    public int lengthOfLongestSubstring4(String s) {
        int n = s.length();
        char[] chArr = s.toCharArray();
        int res = 0;
        int l = 0, r = 0;

        Map<Character, Integer> map = new HashMap<>();

        while (r < n) {
            if (map.containsKey(chArr[r])) {
                l = Math.max(map.get(chArr[r]) + 1, l);
            }
            map.put(chArr[r], r);
            res = Math.max(res, r - l + 1);
            r++;
        }

        return res;
    }

    // sliding window - optimal
    // T: O(n)
    // S: O(1)
    public int lengthOfLongestSubstring5(String s) {
        int n = s.length();
        char[] chArr = s.toCharArray();
        int res = 0;
        int l = 0, r = 0;

        int[] charSet = new int[256];
        Arrays.fill(charSet, -1);

        while (r < n) {
            if (charSet[chArr[r]] >= l) {
                l = charSet[chArr[r]] + 1;
            }
            charSet[chArr[r]] = r;
            res = Math.max(res, r - l + 1);
            r++;
        }

        return res;
    }
}

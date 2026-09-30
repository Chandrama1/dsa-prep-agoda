package problems;

import java.util.*;

public class LongestRepeatingCharacterReplacement {
    // brute force
    // T: O(n^2)
    // S: O(m)
    public int characterReplacement1(String s, int k) {
        int n = s.length();
        char[] chArr = s.toCharArray();
        int res = 0;

        for (int i = 0; i < n; i++) {
            Map<Character, Integer> map = new HashMap<>();
            for (int j = i; j < n; j++) {
                map.put(chArr[j], map.getOrDefault(chArr[j], 0) + 1);

                int subStrLen = j - i + 1;
                int replacements = subStrLen - Collections.max(map.values());

                if (replacements <= k) {
                    res = Math.max(res, subStrLen);
                }
            }
        }
        return res;
    }

    // brute force - preferred
    // T: O(n^2)
    // S: O(m)
    public int characterReplacement2(String s, int k) {
        int n = s.length();
        char[] chArr = s.toCharArray();
        int res = 0;

        for (int i = 0; i < n; i++) {
            Map<Character, Integer> map = new HashMap<>();
            int maxF = 0;
            for (int j = i; j < n; j++) {
                map.put(chArr[j], map.getOrDefault(chArr[j], 0) + 1);

                int subStrLen = j - i + 1;
                maxF = Math.max(maxF, map.get(chArr[j]));
                int replacements = subStrLen - maxF;

                if (replacements <= k) {
                    res = Math.max(res, subStrLen);
                }
            }
        }
        return res;
    }

    // better - sliding window
    // T: O(m * n)
    // S: O(m)
    public int characterReplacement3(String s, int k) {
        int n = s.length();
        char[] chArr = s.toCharArray();
        int res = 0;
        Set<Character> charSet = new HashSet<>();

        for (char ch: chArr) {
            charSet.add(ch);
        }

        for (char ch: charSet) {
            int l = 0, r = 0;
            int count = 0;

            while (r < n) {
                if (chArr[r] == ch) {
                    count++;
                }

                while ((r - l + 1) - count > k) {
                    if (chArr[l] == ch) {
                        count--;
                    }
                    l++;
                }

                res = Math.max(res, r - l + 1);
                r++;
            }
        }

        return res;
    }

    // optimal - sliding window
    // T: O(n)
    // S: O(m)
    public int characterReplacement4(String s, int k) {
        int n = s.length();
        char[] chArr = s.toCharArray();
        int res = 0;
        int maxF = 0;
        int l = 0, r = 0;
        Map<Character, Integer> map = new HashMap<>();

        while (r < n) {
            map.put(chArr[r], map.getOrDefault(chArr[r], 0) + 1);
            maxF = Math.max(maxF, map.get(chArr[r]));

            while (r - l + 1 - maxF > k) {
                map.put(chArr[l], map.get(chArr[l]) - 1);
                l++;
            }

            res = Math.max(res, r - l + 1);
            r++;
        }

        return res;
    }
}

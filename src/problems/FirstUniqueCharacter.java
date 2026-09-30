package problems;

import java.util.HashMap;
import java.util.Map;

public class FirstUniqueCharacter {

    // brute force
    // T: O(n^2)
    // S: O(1)
    public static int firstUniqChar1(String s) {
        for (int i = 0; i < s.length(); i++) {
            boolean isNonRepeating = true;
            char ch = s.charAt(i);

            for (int j = 0; j < s.length(); j++) {
                if (i != j && ch == s.charAt(j)) {
                    isNonRepeating = false;
                    break;
                }
            }
            if (isNonRepeating)
                return i;
        }
        return -1;
    }

    // hash map
    // T: O(n)
    // S: O(1) - max 26 characters
    public int firstUniqChar2(String s) {
        Map<Character, Integer> map = new HashMap<>();

        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            map.put(ch, map.getOrDefault(ch, 0) + 1);
        }

        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            if (map.get(ch) == 1)
                return i;
        }
        return -1;
    }

    // hash map
    // T: O(n)
    // S: O(1) - max 26 characters
    public int firstUniqChar3(String s) {
        int n = s.length();
        Map<Character, Integer> map = new HashMap<>();

        for (int i = 0; i < n; i++) {
            char ch = s.charAt(i);
            int freq = map.containsKey(ch) ? n : i;
            map.put(ch, freq);
        }

        int res = n;
        for (int value: map.values()) {
            res = Math.min(res, value)
            ;        }
        return res == n ? -1 : res;
    }

    // fixed array (faster than hashmap)
    // T: O(n)
    // S: O(1) - max 26 characters
    public int firstUniqChar4(String s) {
        int[] count = new int[26];
        int n = s.length();
        // build char count bucket : <charIndex, count>
        for (int i = 0; i < n; i++) {
            int index = s.charAt(i) - 'a';
            count[index]++;
        }

        // find the index
        for (int i = 0; i < n; i++) {
            int index = s.charAt(i) - 'a';
            if (count[index] == 1) {
                return i;
            }

        }
        return -1;
    }

    // iterative
    // T: O(26 * n)
    // S: O(1) - max 26 characters
    public int firstUniqChar5(String s) {
        int[] count = new int[26];
        int n = s.length();
        int res = n;

        for (char ch = 'a'; ch <= 'z'; ch++) {
            int index = s.indexOf(ch);
            if (index != -1 && index == s.lastIndexOf(ch)) {
                res = Math.min(res, index);
            }
        }
        return res == n ? -1 : res;
    }
}

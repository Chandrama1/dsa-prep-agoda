package problems;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class PermutationInString {

    // brute force - IGNORE
    public static boolean checkInclusion(String s1, String s2) {
        if (s2.length() < s1.length())
            return false;

        char[] s1Arr = s1.toCharArray();
        Arrays.sort(s1Arr);
        String sortedS1 = new String(s1Arr);

        int n = s2.length();

        for (int i = 0; i < n; i++) {
            StringBuilder sb = new StringBuilder();
            for (int j = i; j < n; j++) {
                sb.append(s2.charAt(j));

                String subStr = sb.toString();
                if (getSortedString(subStr).equals(sortedS1)) {
                    return true;
                }
            }
        }

        return false;
    }

    private static String getSortedString(String s) {
        char[] chArr = s.toCharArray();
        Arrays.sort(chArr);
        return new String(chArr);
    }

    // brute force
    // T: O(n^3 * log n)
    // S: O(n)
    public boolean checkInclusion1(String s1, String s2) {
        if (s2.length() < s1.length())
            return false;

        char[] s1Arr = s1.toCharArray();
        Arrays.sort(s1Arr);
        String sortedS1 = new String(s1Arr);

        int n = s2.length();

        for (int i = 0; i < n; i++) {
            for (int j = i; j < n; j++) {
                char[] s2Arr = s2.substring(i, j + 1).toCharArray();
                Arrays.sort(s2Arr);
                String sortedSubStr = new String(s2Arr);

                if (sortedSubStr.equals(sortedS1)) {
                    return true;
                }
            }
        }

        return false;
    }

    // hash map
    // T: O(m + n^2)
    // S: O(1) or O(k) -> distinct letters in s1/s2
    public boolean checkInclusion2(String s1, String s2) {
        Map<Character, Integer> count1 = new HashMap<>();
        for (char c : s1.toCharArray()) {
            count1.put(c, count1.getOrDefault(c, 0) + 1);
        }

        int need = count1.size();
        for (int i = 0; i < s2.length(); i++) {
            Map<Character, Integer> count2 = new HashMap<>();
            int cur = 0;
            for (int j = i; j < s2.length(); j++) {
                char c = s2.charAt(j);
                count2.put(c, count2.getOrDefault(c, 0) + 1);

                if (count1.getOrDefault(c, 0) < count2.get(c)) {
                    break;
                }

                if (count1.getOrDefault(c, 0) == count2.get(c)) {
                    cur++;
                }

                if (cur == need) {
                    return true;
                }
            }
        }
        return false;
    }

    // sliding window
    // T: O(26 * n)
    // S: (1)
    public static boolean checkInclusion3(String s1, String s2) {
        if (s2.length() < s1.length())
            return false;

        int[] count1 = new int[26];
        int[] count2 = new int[26];

        for (char c : s1.toCharArray()) {
            count1[c - 'a']++;
        }

        int l = 0, r = 0;
        int m = s1.length();
        int n = s2.length();

        while (r < n) {

            while (r - l + 1 <= m) {
                count2[s2.charAt(r) - 'a']++;
                r++;
            }

            if (Arrays.equals(count1, count2)) {
                return true;
            }

            count2[s2.charAt(l) - 'a']--;
            l++;
        }

        return false;
    }

    // sliding window - optimal
    // T: O(n)
    // S: (1)
    public static boolean checkInclusion4(String s1, String s2) {
        if (s2.length() < s1.length())
            return false;

        int m = s1.length();
        int n = s2.length();

        int[] count1 = new int[26];
        int[] count2 = new int[26];

        for (int i = 0; i < m; i++) {
            count1[s1.charAt(i) - 'a']++;
            count2[s2.charAt(i) - 'a']++;
        }

        int l = 0, r = m;
        int matches = 0;

        for (int i = 0; i < 26; i++) {
            if (count1[i] == count2[i]) {
                matches++;
            }
        }

        while (r < n) {
            if (matches == 26) {
                return true;
            }

            int rIdx = s2.charAt(r) - 'a';
            count2[rIdx]++;

            if (count1[rIdx] == count2[rIdx]) {
                matches++;
            } else if (count1[rIdx] + 1 == count2[rIdx]) {
                matches--;
            }

            int lIdx = s2.charAt(l) - 'a';
            count2[lIdx]--;

            if (count1[lIdx] == count2[lIdx]) {
                matches++;
            } else if (count1[lIdx] - 1 == count2[lIdx]) {
                matches--;
            }

            r++; l++;
        }

        return matches == 26;
    }
}

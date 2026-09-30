package problems;

import java.util.Arrays;
import java.util.HashMap;

public class ValidAnagram {

    // brute force
    public boolean isAnagram1(String s, String t) {
        if (s.length() != t.length())
            return false;

        char[] ch1 = s.toCharArray();
        char[] ch2 = t.toCharArray();

        Arrays.sort(ch1);
        Arrays.sort(ch2);

        return Arrays.equals(ch1, ch2);
    }

    // hashmap
    public boolean isAnagram2(String s, String t) {
        if (s.length() != t.length())
            return false;

        HashMap<Character,Integer> map1=new HashMap<>();
        HashMap<Character,Integer> map2=new HashMap<>();

        for(int i = 0; i < s.length(); i++) {
            char ch1 = s.charAt(i);
            char ch2 = t.charAt(i);
            map1.put(ch1, map1.getOrDefault(ch1, 0) + 1);
            map2.put(ch2, map2.getOrDefault(ch2, 0) + 1);
        }

        return map1.equals(map2);
    }

    // hashtable
    public boolean isAnagram3(String s, String t) {
        if (s.length() != t.length())
            return false;

        int[] table = new int[26];

        for(int i = 0; i < s.length(); i++) {
            char ch1 = s.charAt(i);
            char ch2 = t.charAt(i);

            table[ch1 - 'a']++;
            table[ch2 - 'a']--;
        }

        for (int i = 0; i < 26; i++) {
            if (table[i] != 0)
                return false;
        }

        return true;
    }
}

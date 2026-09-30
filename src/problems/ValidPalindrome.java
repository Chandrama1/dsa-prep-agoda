package problems;

public class ValidPalindrome {
    // reverse string
    // T: O(n)
    // S: O(n)
    public boolean isPalindrome1(String s) {
        StringBuilder sb = new StringBuilder();
        for (char ch: s.toCharArray()) {
            if (Character.isLetterOrDigit(ch)) {
                sb.append(Character.toLowerCase(ch));
            }
        }
        return sb.toString().equals(sb.reverse().toString());
    }

    // two pointer (less time)
    // T: O(n)
    // S: O(1)
    public boolean isPalindrome2(String s) {
        int n = s.length();
        int start = 0;
        int end = n - 1;

        while(start < end) {
            char ch1 = s.charAt(start);
            char ch2 = s.charAt(end);

            if (Character.toLowerCase(ch1) == Character.toLowerCase(ch2)) {
                start++;
                end--;
            } else if (!Character.isLetterOrDigit(ch1))
                start++;
            else if (!Character.isLetterOrDigit(ch2))
                end--;
            else {
                return false;
            }
        }
        return true;
    }

    // two pointer (ignore)
    // T: O(n)
    // S: O(1)
    public boolean isPalindrome3(String s) {
        int n = s.length();
        int start = 0;
        int end = n - 1;

        while(start < end) {
            while (start < end && !Character.isLetterOrDigit(s.charAt(start)))
                start++;
            while (end > start && !Character.isLetterOrDigit(s.charAt(end)))
                end--;

            if (Character.toLowerCase(s.charAt(start)) == Character.toLowerCase(s.charAt(end))) {
                start++;
                end--;
            } else {
                return false;
            }
        }
        return true;
    }
}

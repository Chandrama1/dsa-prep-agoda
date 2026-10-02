package problems;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class ValidParentheses {
    // brute force
    // T: O(n^2)
    // S: O(n)
    public boolean isValid1(String s) {
        if (s.length() % 2 == 1) {
            return false;
        }

        while (s.contains("()") || s.contains("[]") || s.contains("{}")) {
            s.replace("()", "");
            s.replace("[]", "");
            s.replace("{}", "");
        }

        return s.isEmpty();
    }

    // stack
    // T: O(n)
    // S: O(n)
    public boolean isValid12(String s) {
        if (s.length() % 2 == 1) {
            return false;
        }

        Stack<Character> stack = new Stack<>();

        Map<Character, Character> complement = new HashMap<>();
        complement.put('(', ')');
        complement.put('[', ']');
        complement.put('{', '}');

        for (char ch: s.toCharArray()) {
            if (ch == ')' || ch == ']' || ch == '}') {
                if (stack.isEmpty() || stack.pop() != ch) {
                    return false;
                }
            }

            if (ch == '(' || ch == '[' || ch == '{') {
                stack.push(complement.get(ch));
            }
        }

        return stack.isEmpty();
    }

    // stack
    // T: O(n)
    // S: O(n)
    public boolean isValid3(String s) {
        if (s.length() % 2 != 0) {
            return false;
        }

        Stack<Character> stack = new Stack<>();

        for (char ch : s.toCharArray()) {
            if (ch == '(') {
                stack.push(')');
            } else if (ch == '[') {
                stack.push(']');
            } else if (ch == '{') {
                stack.push('}');
            } else {
                if (stack.isEmpty() || stack.pop() != ch) {
                    return false;
                }
            }
        }

        return stack.isEmpty();
    }
}

package problems.evalrpn;

import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;
import java.util.Stack;

public class EvaluateReversePolishNotation {
    // brute force
    // T: O(n^2)
    // S: O(n)
    public int evalRPN(String[] tokens) {
        List<String> tokenList = new ArrayList<>(Arrays.asList(tokens));

        while (tokenList.size() > 1) {
            for (int i = 0; i < tokenList.size(); i++) {
                String token = tokenList.get(i);
                int res;

                if ("+-*/".contains(token)) {
                    int num1 = Integer.parseInt(tokenList.get(i - 2));
                    int num2 = Integer.parseInt(tokenList.get(i - 1));

                    res = switch (token) {
                        case "+" -> num1 + num2;
                        case "-" -> num1 - num2;
                        case "*" -> num1 * num2;
                        default -> num1 / num2;
                    };

                    tokenList.set(i, String.valueOf(res));
                    tokenList.remove(i - 1);
                    tokenList.remove(i - 2);

                    break;
                }
            }
        }

        return Integer.parseInt(tokenList.get(0));
    }

    // recursive
    // T: O(n)
    // S: O(n)
    public static int evalRPNRecursive1(String[] tokens) {
        if (tokens.length == 0) {
            return 0;
        }

        List<String> tokenList = new ArrayList<>(Arrays.asList(tokens));
        return eval(tokenList, 0);
    }

    public static int eval(List<String> tokenList, int i) {
        if (tokenList.size() == 1) {
            return Integer.parseInt(tokenList.get(0));
        }

        String token = tokenList.get(i);

        if ("+-*/".contains(token)) {
            int num1 = Integer.parseInt(tokenList.get(i - 2));
            int num2 = Integer.parseInt(tokenList.get(i - 1));

            int res = switch (token) {
                case "+" -> num1 + num2;
                case "-" -> num1 - num2;
                case "*" -> num1 * num2;
                default -> num1 / num2;
            };

            tokenList.set(i, String.valueOf(res));
            tokenList.remove(i - 1);
            tokenList.remove(i - 2);

            return eval(tokenList, i - 2);
        }
        return eval(tokenList, i + 1);
    }

    // stack - optimal
    // T: O(n)
    // S: O(n)
    public int evalRPNStack(String[] tokens) {
        if (tokens.length == 0) {
            return 0;
        }

        Stack<Integer> tokenStack = new Stack<>();

        for (String token: tokens) {
            if ("+-*/".contains(token)) {
                int num2 = tokenStack.pop();
                int num1 = tokenStack.pop();

                int res = switch (token) {
                    case "+" -> num1 + num2;
                    case "-" -> num1 - num2;
                    case "*" -> num1 * num2;
                    default -> num1 / num2;
                };

                tokenStack.push(res);
            } else {
                tokenStack.push(Integer.parseInt(token));
            }
        }

        return tokenStack.pop();
    }
}

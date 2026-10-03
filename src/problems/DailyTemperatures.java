package problems;

import java.util.Stack;

public class DailyTemperatures {
    // brute force
    // T: O(n^2)
    // S: O(1)
    public int[] dailyTemperatures1(int[] temperatures) {
        int n = temperatures.length;

        int[] answer = new int[n];

        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                if (temperatures[j] > temperatures[i]) {
                    answer[i] = j - i;
                    break;
                }
            }
        }
        return answer;
    }

    // stack
    // T: O(n)
    // S: O(n)
    public int[] dailyTemperatures2(int[] temperatures) {
        int n = temperatures.length;

        int[] answer = new int[n];

        Stack<int[]> stack = new Stack<>();

        for (int i = 0; i < n; i++) {
            int temp = temperatures[i];
            while (!stack.isEmpty() && stack.peek()[0] < temp) {
                int[] lastTemp = stack.pop();
                answer[lastTemp[1]] = i - lastTemp[1];
            }
            stack.push(new int[] { temp, i });
        }
        return answer;
    }

    public int[] dailyTemperatures3(int[] temperatures) {
        int n = temperatures.length;

        int[] answer = new int[n];

        Stack<Integer> stack = new Stack<>();

        for (int i = 0; i < n; i++) {
            int temp = temperatures[i];
            while (!stack.isEmpty() && temperatures[stack.peek()] < temp) {
                int lastTempIdx = stack.pop();
                answer[lastTempIdx] = i - lastTempIdx;
            }
            stack.push(i);
        }
        return answer;
    }

    // dynamic programming
    // T: O(n)
    // S: O(1)
    public int[] dailyTemperatures4(int[] temperatures) {
        int n = temperatures.length;

        int[] answer = new int[n];

        for (int i = n - 2; i >= 0; i--) {
            int j = i + 1;

            while (j < n && temperatures[j] <= temperatures[i]) {
                if (answer[j] == 0) {
                    j = n;
                    break;
                }
                j += answer[j]; // assign index of next warmer temperature
            }

            if (j < n) {
                answer[i] = j - i;
            }
        }

        return answer;
    }
}

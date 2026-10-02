package problems.minstack;

import java.util.Stack;

class MinStack3 {

    private Stack<Long> stack;
    private long min;

    public MinStack3() {
        stack = new Stack<>();
    }

    public void push(int value) {
        if (stack.isEmpty()) {
            min = value;
            stack.push(0L);
        } else {
            stack.push(value - min);
            min = Math.min(min, value);
        }
    }

    public void pop() {
        if (stack.isEmpty()) return;

        long val = stack.pop();

        if (val < 0) {
            min = min - val;
        }
    }

    public int top() {
        long val = stack.peek();

        if (val > 0) {
            return (int) (min + val);
        } else {
            return (int) min;
        }
    }

    public int getMin() {
        return (int) min;
    }
}

package problems.minstack;

import java.util.Stack;

class MinStack1 {

    private Stack<Integer> stack;

    public MinStack1() {
        stack = new Stack<>();
    }

    public void push(int value) {
        stack.push(value);
    }

    public void pop() {
        stack.pop();
    }

    public int top() {
        return stack.peek();
    }

    public int getMin() {
        int min = Integer.MAX_VALUE;

        for (int i = 0; i < stack.size(); i++) {
            min = Math.min(min, stack.get(i));
        }

        return min;
    }

    public int getMin1() {
        Stack<Integer> tmp = new Stack<>();
        int mini = stack.peek();

        while (!stack.isEmpty()) {
            mini = Math.min(mini, stack.peek());
            tmp.push(stack.pop());
        }

        while (!tmp.isEmpty()) {
            stack.push(tmp.pop());
        }

        return mini;
    }
}

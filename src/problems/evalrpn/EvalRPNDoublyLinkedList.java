package problems.evalrpn;

public class EvalRPNDoublyLinkedList {
    public class Node {
        String val;
        Node prev;
        Node next;

        public Node(String val, Node prev, Node next) {
            this.val = val;
            this.prev = prev;
            this.next = next;
        }
    }

    // T: O(n)
    // S: O(n)
    public int evalRPN(String[] tokens) {

        Node head = new Node(tokens[0], null, null);
        Node temp = head;

        for (int i = 1; i < tokens.length; i++) {
            temp.next = new Node(tokens[i], temp, null);
            temp = temp.next;
        }

        int res = 0;

        while (head != null) {
            if ("+-*/".contains(head.val)) {
                int num1 = Integer.parseInt(head.prev.prev.val);
                int num2 = Integer.parseInt(head.prev.val);

                res = switch(head.val) {
                    case "+" -> num1 + num2;
                    case "-" -> num1 - num2;
                    case "*" -> num1 * num2;
                    default -> num1 / num2;
                };

                head.val = String.valueOf(res);
                head.prev = head.prev.prev.prev;

                // num1 is the first element
                if (head.prev != null) {
                    head.prev.next = head;
                }
            }

            res = Integer.parseInt(head.val);
            head = head.next;
        }
        return res;
    }
}

package problems;

public class ChildWhoHasBallAfterKSeconds {

    // brute force
    // T: O(n)
    public int numberOfChild(int n, int k) {
        int time = 1;
        int direction = 1;
        int j = 0;

        while (time <= k) {

            if (j == 0) {
                direction = 1;
                j++;
            } else if (j == n - 1) {
                direction = 0;
                j--;
            } else if (direction == 1) {
                j++;
            } else {
                j--;
            }
            time++;
        }
        return j;
    }

    // optimised
    // T: O(1)
    public int numberOfChild2(int n, int k) {
        int trips = k / (n - 1);
        int rem = k % (n - 1);

        if (trips % 2 == 0) {
            return rem;
        }
        return (n - 1) - rem;
    }
}

package problems;

public class KokoEatingBananas {
    // brute force
    // T: O(m * n), m = size of largest pile
    // S: O(1)
    public static int minEatingSpeed1(int[] piles, int h) {
        int k = 1;

        while (true) {
            long hoursTaken = 0;
            for (int pile : piles) {
//                hoursTaken += (int) Math.ceil((double) pile / k);
                hoursTaken += (pile + k - 1) / k;
            }
            if (hoursTaken <= h)
                return k;

            k++;
        }
    }

    // T: O(n * log m), m = size of largest pile
    // S: O(1)
    public static int minEatingSpeedBinary(int[] piles, int h) {
        int maxPile = 0;

        for (int pile: piles) {
            maxPile = Math.max(maxPile, pile);
        }

        int l = 1, r = maxPile;
        int res = 1;

        while (l <= r) {
            int k = l + (r - l) / 2;

            long hoursTaken = 0;
            for (int pile: piles) {
                hoursTaken += (pile + (long) k - 1) / k;
            }

            if (hoursTaken <= h) {
                res = k;
                r = k - 1;
            } else {
                l = k + 1;
            }
        }

        return res;
    }
}

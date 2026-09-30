package problems;

public class BuySellStock {
    // brute force
    // T: O(n^2)
    // S: O(1)
    public int maxProfit1(int[] prices) {
        int max = 0;
        int n = prices.length;

        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                max = Math.max(prices[j] - prices[i], max);
            }
        }

        return max;
    }

    // two pointer - sliding window
    // T: O(n)
    // S: O(1)
    public static int maxProfit2(int[] prices) {
        int n = prices.length;
        int maxP = 0;

        int l = 0, r = 1;
        while (r < n) {
            int profit = prices[r] - prices[l];

            if (profit < 0) {
                l = r;
            } else {
                maxP = Math.max(maxP, profit);
            }
            r++;
        }

        return maxP;
    }

    // dynamic programming
    // T: O(n)
    // S: O(1)
    public int maxProfit3(int[] prices) {
        int n = prices.length;
        int maxP = 0;
        int minBuy = prices[0];

        for (int sell: prices) {
            maxP = Math.max(sell - minBuy, maxP);
            minBuy = Math.min(sell, minBuy);
        }

        return maxP;
    }
}

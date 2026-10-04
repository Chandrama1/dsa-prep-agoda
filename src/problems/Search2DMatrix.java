package problems;

public class Search2DMatrix {
    public boolean searchMatrix(int[][] matrix, int target) {
        int m = matrix.length;
        int n = matrix[0].length;

        for (int i = 0; i < m; i++) {
            for (int j = 0; i < n; j++) {
                if (matrix[i][j] == target)
                    return true;
            }
        }

        return false;
    }

    // staircase
    // T: O(m + n)
    public boolean searchMatrixStaircase(int[][] matrix, int target) {
        int m = matrix.length;
        int n = matrix[0].length;

        int r = 0, c = n - 1;

        while (r < m && c >= 0) {
            int num = matrix[r][c];

            if (num == target)
                return true;

            if (num > target)
                c--;
            else
                r++;
        }

        return false;
    }

    // binary search - brute force
    // T: O(m * log n)
    public boolean searchMatrixBinary1(int[][] matrix, int target) {
        int m = matrix.length;
        int n = matrix[0].length;

        for (int i = 0; i < m; i++) {
            boolean rowRes = binarySearch(matrix[i], target);
            if (rowRes)
                return true;
        }

        return false;
    }

    public boolean binarySearch(int[] nums, int target) {
        int l = 0;
        int r = nums.length - 1;

        while (l <= r) {
            int m = l + (r - l) / 2;

            if (nums[m] == target)
                return true;

            if (nums[m] > target)
                r = m - 1;
            else
                l = m + 1;
        }
        return false;
    }

    // binary search
    // T: O(log m + log n) = O(log (m * n))
    public boolean searchMatrixBinary2(int[][] matrix, int target) {
        int m = matrix.length;
        int n = matrix[0].length;

        int top = 0, bottom = m - 1;

        while (top <= bottom) {
            int mid = top + (bottom - top) / 2;

            if (target > matrix[mid][n - 1])    // target > last element of mid row
                top = mid + 1;                  // move down
            else if (target < matrix[mid][0])   // target < first element of mid row
                bottom = mid - 1;
            else
                break;                          // target in mid row
        }

        if (!(top <= bottom))   // target not found in any row; break doesn't run
            return false;

        int row = top + (bottom - top) / 2;

        int l = 0, r = n - 1;

        while (l <= r) {
            int midCol = l + (r - l) / 2;

            if (matrix[row][midCol] == target)
                return true;

            if (matrix[row][midCol] > target)
                r = midCol - 1;
            else
                l = midCol + 1;
        }

        return false;
    }

    // binary search - one pass (Optimal)
    // T: O(log(m * n))
    public boolean searchMatrixBinaryOnePass(int[][] matrix, int target) {
        int m = matrix.length;
        int n = matrix[0].length;

        int l = 0, r = (m * n) - 1;

        while (l <= r) {
            int mid = l + (r - l) / 2;
            int num = getNum(matrix, mid, n);

            if (num == target)
                return true;

            if (num > target)
                r = mid - 1;
            else
                l = mid + 1;
        }

        return false;
    }

    public int getNum(int[][] matrix, int k, int n) {
        int r = k / n;
        int c = k % n;

        return matrix[r][c];
    }
}

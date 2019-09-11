public class Solution7 {

    private static int[][] compute(int[][] a, int[][] b) {
        int[][] res = new int[2][2];
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 2; j++) {
                for (int k = 0; k < 2; k++) {
                    res[i][j] += a[i][k] * b[k][j];
                }
            }
        }
        return res;
    }

    public int Fibonacci(int n) {
        if (n == 0) {
            return 0;
        }else if(n == 1 || n == 2){
            return 1;
        }
        n -= 2;
        int[][] val = new int[][]{{1, 1}, {1, 0}};
        int[][] res = new int[][]{{1, 0}, {0, 1}};
        while (n > 0) {
            if ((n & 1) == 1) {
                res = compute(res, val);
            }
            n >>= 1;
            val = compute(val, val);
        }
        return res[0][0] + res[0][1];
    }

    public static void main(String[] args) {
        new Solution7().Fibonacci(3);
    }
}
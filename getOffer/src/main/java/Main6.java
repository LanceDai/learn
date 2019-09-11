import java.util.Arrays;
import java.util.Scanner;

import static java.lang.Math.abs;
import static java.lang.Math.max;

public class Main6 {
    static double[] li;
    static int n, m;
    static double EPS = Math.pow(10, -4);

    static boolean check(double ans) {
        int res = 0;
        for (int i = n - 1; i >= 0; --i) {
            res += Math.floor(li[i] / ans);
            if (res >= m) {
                return true;
            }
        }
        return res >= m;
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        n = input.nextInt();
        m = input.nextInt();
        li = new double[n];
        for (int i = 0; i < n; ++i) {
            li[i] = input.nextDouble();
        }
        Arrays.sort(li);
        double start = 0;
        double end = li[n - 1];
        double mid;
        double ans = 0;
        while (abs(start - end) > EPS) {
            mid = (start + end) / 2;
            if (check(mid)) {
                start = mid;
                ans = max(ans, mid);
            } else {
                end = mid;
            }
        }
        System.out.printf("%.2f\n", ans);
    }
}
import java.util.Arrays;
import java.util.Scanner;

public class Main7 {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int T;
        T = input.nextInt();
        while (T-- > 0) {
            int n;
            n = input.nextInt();
            int[] a = new int[n];
            int[] b = new int[n];
            for (int i = 0; i < n; i++) {
                a[i] = input.nextInt();
            }
            Arrays.fill(b, 1);
            int flag = 1, pre, next;
            while (flag != 0) {
                flag = 0;
                for (int i = 0; i < n; ++i) {
                    pre = i - 1 < 0 ? n - 1 : i - 1;
                    next = i + 1 >= n ? 0 : i + 1;
                    if (a[i] > a[pre] && a[i] > a[next]) {
                        if (b[i] != Math.max(b[pre], b[next]) + 1) {
                            b[i] = Math.max(b[pre], b[next]) + 1;
                            flag++;
                        }
                    } else if (a[i] > a[pre] && a[i] <= a[next]) {
                        if (b[i] != b[pre] + 1) {
                            b[i] = b[pre] + 1;
                            flag++;
                        }
                    } else if (a[i] <= a[pre] && a[i] > a[next]) {
                        if (b[i] != b[next] + 1) {
                            b[i] = b[next] + 1;
                            flag++;
                        }
                    }
                }
            }
            long sum = 0;
            for (int i = 0; i < n; i++) {
                sum += b[i];
            }
            System.out.println(sum);
        }
    }
}
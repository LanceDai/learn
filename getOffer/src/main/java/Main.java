import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Main {

/** 请完成下面这个函数，实现题目要求的功能 **/
    /**
     * 当然，你也可以不按照这个模板来作答，完全按照自己的想法来 ^-^
     **/
    static String calculate(int m, int k) {
        long[] f = new long[m + 5];
        long[] f2 = new long[m + 5];
        long x = 0;
        StringBuffer res = new StringBuffer();
        f[1] = 2;
        f[2] = 3;
        f[3] = 4;
        f2[1] = 2;
        f2[2] = 3;
        for (int i = 4; i <= m; ++i) {
            f[i] = f[i - 2] + f[i - 3];
        }
        for (int i = 3; i <= m; ++i) {
            f2[i] = f2[i - 1] + f2[i - 2];
            if(f2[i] > m){
                x = i - 1;
                break;
            }
        }
        //System.out.println(f[m]);

        PriorityQueue<Long> q = new PriorityQueue<>();
        long count = 0, tmp = f[m];
        while (tmp > 0) {
            count++;
            tmp /= 10;
        }
        tmp = 1;
        while (count > 1) {
            tmp *= 10;
            count--;
        }
        tmp -= 1;
        //System.out.println("tmp = " + tmp);
        long newVal, ans = 0;
        count = 0;
        for (int i = m; i >= 0; --i) {
            count++;
            newVal = Long.valueOf(new StringBuffer(String.valueOf(f[i])).reverse().toString());
            if (q.size() < k) {
                //System.out.println("newVal = " + newVal);
                q.add(newVal);
            } else if (newVal > q.peek()) {
                if (q.size() >= k) {
                    q.poll();
                }
                //System.out.println("newVal = " + newVal);
                q.add(newVal);
            }
            if(q.peek() == newVal){
                ans = i;
            }
        }
        if(count < k){
            for (int i = (int) (m - count); i >= m - k; --i){
                newVal = Long.valueOf(new StringBuffer(String.valueOf(f[i])).reverse().toString());
                if (q.size() < k) {
                    q.add(newVal);
                } else if (newVal > q.peek()) {
                    if (q.size() >= k) {
                        q.poll();
                    }
                    q.add(newVal);
                }
                if(q.peek() == newVal){
                    ans = i;
                }
            }
        }
        //System.out.println(q.peek());
        res.append(f[m]).append(",");
        res.append(2019 + x).append(",");
        res.append(ans);
        return res.toString();
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String[] line = in.nextLine().split(",");
        int m = Integer.valueOf(line[0]);
        int k = Integer.valueOf(line[1]);
//        int m = 15, k = 2;
        System.out.println(calculate(m, k));

    }
}
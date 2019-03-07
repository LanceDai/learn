import java.util.ArrayList;
import java.util.BitSet;
import java.util.concurrent.*;

public class Solution {

    public static void main(String[] args) {
        int[] nums = new int[]{-2, 1, -2, 3, 10, -4, 7, 2, 5, -2};
        int start, end, sum, ansStart, ansEnd, ans;
        start = sum = ansEnd = ansStart = 0;
        ans = -1;
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
            while (sum < 0) {
                sum -= nums[start];
                start++;
            }
            end = i;
            if(sum > ans){
                ansStart = start;
                ansEnd = end;
                ans = sum;
            }
        }
        for (int i = ansStart; i <= ansEnd ; i++) {
            System.out.print(nums[i] + " --- ");
        }
//        LinkedBlockingQueue
        ArrayBlockingQueue arrayBlockingQueue;
//        new ThreadPoolExecutor();
//        ThreadPoolExecutor.CallerRunsPolicy
//        ThreadFactory

    }
}

// -2,1,-2,3,10,-4,7,2,5,-2
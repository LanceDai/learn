import java.util.ArrayList;
import java.util.Stack;

public class Solution {
    public boolean IsPopOrder(int[] pushA, int[] popA) {
        Stack<Integer> stack = new Stack<Integer>();
        for (int popIndex = 0, pushIndex = 0; popIndex < popA.length; popIndex++) {
            while ((stack.isEmpty() || stack.peek() != popA[popIndex]) && pushIndex < pushA.length) {
                stack.push(pushA[pushIndex++]);
            }
            if (pushIndex > pushA.length) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        System.out.println(
                new Solution().IsPopOrder(
                        new int[]{1, 2, 3, 4, 5},
                        new int[]{4, 3, 5, 1, 2}));
    }

//    1,2,3,4,5 4,5,3,2,1
//            4,3,5,1,2
}
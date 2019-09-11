public class Solution8 {
    public int JumpFloorII(int target) {
        return 1 << (target - 1);
    }

    public static void main(String[] args) {
        new Solution8().JumpFloorII(2);
    }
}
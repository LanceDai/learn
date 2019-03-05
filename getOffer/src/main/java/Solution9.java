public class Solution9 {
    public int RectCover(int target) {
        if (target <= 0) {
            return 0;
        }
        else if(target == 1 || target == 2) {
            return target;
        }
        return (RectCover(target - 1) + RectCover(target - 2));
    }
}
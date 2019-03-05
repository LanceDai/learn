public class Solution1 {
    public boolean Find(int target, int[][] array) {
        int row = array.length - 1, column = 0;
        while (row >= 0 && column < array[row].length){
            if(array[row][column] == target){
                return true;
            }else if(array[row][column] < target){
                column++;
            }else {
                row--;
            }
        }
        return false;
    }
}
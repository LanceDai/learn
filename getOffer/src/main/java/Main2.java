public class Main2 {
    public static void main(String[] args) {
//        Scanner input = new Scanner(System.in);
//        String str = input.next();
        String str = "abbaad";
        int maxLen = 0;
        StringBuilder stringBuilder;
        for (int i = 0; i < str.length(); i++) {
            for (int j = 1; j < str.length() - i; j++) {
                int count = 0;
                while (str.charAt(i + count) == str.charAt(j - count)){
                    count++;
                    if((j & 1) == 1 && i + count + 1 == j - count){
                        if(j > maxLen){
                            stringBuilder = new StringBuilder(str.substring(i, i + j));
                            maxLen = j;
                        }
                    }
                }
            }
        }
    }
}

class TTT{
    interface T1{
        int a = 1;
    }
    interface T2 {
        int a = 2;
    }

    static abstract class T3{
        int a = 3;
    }

    static class sub extends T3 implements T1{
        public static void main(String[] args) {
        }
    }
}
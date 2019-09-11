import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Scanner;

public class Main3 {

    static class Node implements Comparable<Node> {

        char index;
        long val;

        public Node(char index, long val) {
            this.index = index;
            this.val = val;
        }

        @Override
        public int compareTo(Node o) {
            return this.val > o.val ? -1 : 1;
        }
    }

    public static void main(String[] args) {
        int n;
        Scanner input = new Scanner(System.in);
        n = input.nextInt();
        String[] strings = new String[n + 5];
        for (int i = 0; i < n; i++) {
            strings[i] = input.next();
        }
        int[] first =new int[10];
        HashMap<Character, Node> vals = new HashMap<>();
        for (int i = 0; i < n; i++) {
            long res = 1;
            first[strings[i].charAt(0) - 'A'] = 1;
            for (int j = strings[i].length() - 1; j >= 0; --j) {
                char c = strings[i].charAt(j);
                if(vals.get(c) != null){
                    vals.get(c).val += res;
                }else{
                    vals.put(c, new Node(c, res));
                }
                res *= 10;
            }
        }
        ArrayList<Node> res = new ArrayList<>(vals.values());
        Collections.sort(res);
        int[] ans = new int[10];
        int count = 9;

        for (Node re : res) {
            ans[re.index - 'A'] = count--;
        }
        int index = res.get(res.size() - 1).index - 'A';
        if(ans[index] == 0 && first[index] == 1){
            for (int i = res.size() - 1; i > 0 ; --i) {
                index = res.get(i).index - 'A';
                if(first[index] != 1){
                    ans[index] = 0;
                    break;
                }
                ans[res.get(i).index - 'A'] = ans[res.get(i - 1).index - 'A'];
            }
        }
        long ansRes = 0;
        for (int i = 0; i < n; i++) {
            long temp = 0;
            for (int j = 0; j < strings[i].length(); j++) {
                temp = temp * 10 + ans[strings[i].charAt(j) - 'A'];
            }
            ansRes += temp;
        }
        System.out.println(ansRes);
    }
}
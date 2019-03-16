import java.util.LinkedList;
import java.util.Scanner;

public class Main5 {

    public static void main(String[] args) {
        int n;
        Scanner input = new Scanner(System.in);
        n = input.nextInt();
        String[] begin = new String[n + 5];
        StringBuilder[] end = new StringBuilder[n + 5];
        for (int i = 0; i < n; i++) {
            begin[i] = input.next();
            LinkedList<Character> queue = new LinkedList<>();
            int flag = 0;
            end[i] = new StringBuilder();
            for (int j = 0; j < begin[i].length(); j++) {
                if (j != 0 && begin[i].charAt(j) != begin[i].charAt(j - 1)) {
                    flag++;
                }
                queue.add(begin[i].charAt(j));
                //出现三个一样的数字
                if (queue.size() == 3 && flag == 0) {
                    queue.pollLast();
                    //回退到只剩两个
                } else if (queue.size() == 3 && flag == 2) {
                    while (queue.size() > 1) {
                        end[i].append(queue.pollFirst());
                    }
                    flag = 0;
                } else if (queue.size() == 4 && flag == 1) {
                    queue.pollLast();
                } else if (queue.size() == 4 && flag == 2) {
                    while (queue.size() > 1) {
                        end[i].append(queue.pollFirst());
                    }
                    flag = 0;
                } else if (queue.size() == 2 && flag == 1) {
                    while (queue.size() > 1) {
                        end[i].append(queue.pollFirst());
                    }
                    flag = 0;
                }
            }
            while (!queue.isEmpty()) {
                end[i].append(queue.pollFirst());
            }
        }

        for (int i = 0; i < n; i++) {
            System.out.println(end[i].toString());
        }
    }
}
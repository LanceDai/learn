import java.util.Scanner;

public class Main4 {

    public static void main(String[] args) {
        int n;
        Scanner input = new Scanner(System.in);
        n = input.nextInt();
        n = 1024 - n;
        int n1, n2, n3;
        n1 = n / 64;
        n %= 64;
        n2 = n / 16;
        n %= 16;
        n3 = n / 4;
        n %= 4;
        System.out.println(n1 + n2 + n3 + n);
    }
}
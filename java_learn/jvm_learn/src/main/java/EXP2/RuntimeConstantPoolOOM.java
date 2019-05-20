package EXP2;

public class RuntimeConstantPoolOOM {

    public static void main(String[] args) {
        String str1 = new StringBuilder("中国").append("钓鱼岛").toString();
        System.out.println(str1.intern() == str1);

        String str2 = new StringBuilder("ja").append("vvvvvva").toString();
        System.out.println(str2.intern() == str2);
    }
}


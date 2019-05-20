/**
 * @author LanceDai
 * @date 2019/1/16 15:06
 * @description *
 */
public class tt {
    public static void main(String[] args) {
//        String str1 = "计算机" + "软件";
//        System.out.println(str1.intern() == str1);
//
//        String str2 = "ja" + "va";
//        System.out.println(str2.intern() == str2);

//        String str3 = new String("计算机") + new String("软件");
//        System.out.println(str3.intern() == str3);
//
//        String str4 = new String("ja") + new String("va");
//        System.out.println(str4.intern() == str4);

        String str1 = "aaa";
        String str2 = "bbb";
        String str3 = "aaabbb";
        String str4 = str1 + str2;
        String str5 = "aaa" + "bbb";
        System.out.println(str3 == str4);
        System.out.println(str3 == str4.intern());
        System.out.println(str3 == str5);
    }
}

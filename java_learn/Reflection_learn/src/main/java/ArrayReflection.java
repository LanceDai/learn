import java.lang.reflect.Array;
import java.util.Arrays;


public class ArrayReflection {

    public static void main(String[] args) {
        int[] a1 = new int[]{1, 2, 3};
        int[] a2 = new int[4];
        int[][] a3 = new int[2][3];
        String[] a4 = new String[]{"a", "b", "c"};
        System.out.println(a1.getClass() == a2.getClass());
        //编译器能识别出以下两句是有问题的,从而拒绝编译
        //System.out.println(a1.getClass() == a3.getClass());
        //System.out.println(a1.getClass() == a3.getClass());
        System.out.println(a1.getClass().getName());//输出：“[I”，“[”表示数组，“I”表示整数
        System.out.println(a1.getClass().getSuperclass().getName());//java.lang.Object
        System.out.println(a4.getClass().getSuperclass().getName());//java.lang.Object

        //a1、a2、a3、a4都可以赋值给Object类型的变量
        Object aObj1 = a1;
        aObj1 = a2;
        aObj1 = a3;
        aObj1 = a4;

        //以下这句代码不能编译通过
        //Object[] aObj2 = a1;
        //那为何以下代码又是正确的？
        Object[] aObj2 = a3;
        aObj2 = a4;

        //分析：
        //a1不能赋值给aObj2的原因是：因为a1元素类型为int，是原始数据类型，int[]不能转换为Object[]
        //a3能赋值给aObj4，因为a3的元素类型为int[]，是对象，能转换为Object
        //a4的元素类型为String也是对象，所以能被转换为Object[]

        System.out.println(a1);  //输出：[I@c17164
        System.out.println(a4);  //输出：[Ljava.lang.String;@1fb8ee3

        //请对比以下两句代码的输出,为何一个能输出数组的内容，另一个不行？
        System.out.println(Arrays.asList(a1));//输出：[[I@c17164],
        System.out.println(Arrays.asList(a4));//输出：[a, b, c]，

        //原因：List<int>无法转换为Object
        //原因：List<String>可以转换为Object

        //以下两句代码展示字符串数组与字符串是完全不同的两个东西
        printObject(a4);
        printObject("xyz");
    }

    /**
     * 利用反射打印数组的内容
     *
     * @param obj
     */
    private static void printObject(Object obj) {
        Class clazz = obj.getClass();
        if (clazz.isArray()) {
            int len = Array.getLength(obj);
            for (int i = 0; i < len; i++) {
                System.out.println(Array.get(obj, i));
            }
        } else {
            System.out.println(obj);
        }

    }


}

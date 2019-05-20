package EXP7;

/**
 * @author LanceDai
 * @date 2019/1/16 15:06
 * @description *
 * 被动使用类字段演示一：
 * 通过子类引用父类的静态字段，不会导致子类初始化
 **/
class SuperClass {

    static {
        System.out.println("SuperClass init!");
    }

    static int value = 123;
}

class SubClass extends SuperClass {

    static {
        System.out.println("SubClass init!");
    }
}

/**
 * 非主动使用类字段演示
 **/
public class NotInitialization {

    public static void main(String[] args) {
        System.out.println(SubClass.value);
    }

}



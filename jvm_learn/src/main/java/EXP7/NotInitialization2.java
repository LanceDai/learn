package EXP7;

/**
 * @author LanceDai
 * @date 2019/1/16 15:06
 * @description *
 * 被动使用类字段演示二：
 * 通过数组定义来引用类，不会触发此类的初始化
 **/
public class NotInitialization2 {

    public static void main(String[] args) {
        SuperClass[] sca = new SuperClass[10];
    }

}


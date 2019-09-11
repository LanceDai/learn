/**
 * @author LanceDai
 * @date 2019/1/13 10:47
 * @description 利用内部静态类的特性
 * 加载一个类时，其内部类不会同时被加载。
 * 一个类被加载，当且仅当其某个静态成员（静态域、构造器、静态方法等）被调用时发生。
 */
public class Singleton_4 {
    private static class Holder{
        private final static Singleton_4 INSTANCE = new Singleton_4();
    }
    public static Singleton_4 getInstance(){
        return Holder.INSTANCE;
    }
}

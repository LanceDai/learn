/**
 * @author LanceDai
 * @date 2019/1/13 10:47
 * @description 饿汉式
 */
public class Singleton_5 {
    private static Singleton_5 uniqueInstance = new Singleton_5();
    public static Singleton_5 getInstance(){
        return uniqueInstance;
    }
}

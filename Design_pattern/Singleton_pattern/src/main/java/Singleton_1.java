/**
 * @author LanceDai
 * @date 2019/1/13 10:47
 * @description 懒汉式
 */
public class Singleton_1 {
    private static Singleton_1 uniqueInstance;
    public static Singleton_1 getInstance(){
        if (uniqueInstance == null){
            uniqueInstance = new Singleton_1();
        }
        return uniqueInstance;
    }
}

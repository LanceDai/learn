/**
 * @author LanceDai
 * @date 2019/1/13 10:47
 * @description 双重检查，解决效率问题
 */
public class Singleton_3 {
    private static Singleton_3 uniqueInstance;

    public static Singleton_3 getInstance() {
        if (uniqueInstance == null) {
            synchronized (Singleton_3.class) {
                if (uniqueInstance == null) {
                    uniqueInstance = new Singleton_3();
                }
            }
        }
        return uniqueInstance;
    }
}

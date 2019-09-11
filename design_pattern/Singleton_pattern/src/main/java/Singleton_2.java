/**
 * @author LanceDai
 * @date 2019/1/13 10:47
 * @description 单纯的单例模式会造成线程不安全问题，
 * 方法锁可以轻易的解决这个问题，但会造成性能的急剧下降，
 * 因为对于单例实例化后，是不需要同步的
 */
public class Singleton_2 {
    private static Singleton_2 uniqueInstance;
    public static synchronized Singleton_2 getInstance(){
        if (uniqueInstance == null){
            uniqueInstance = new Singleton_2();
        }
        return uniqueInstance;
    }
}

/**
 * @author LanceDai
 * @date 2019/1/13 10:47
 * @description 枚举类型实现单例
 */
public enum  Singleton_6 {
    //唯一实例
    INSTANCE;
    private Object object;
    public static Object getInstance(){
        return INSTANCE.object;
    }
}

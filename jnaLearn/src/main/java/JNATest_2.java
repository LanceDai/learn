import com.sun.jna.Library;
import com.sun.jna.Native;

/**
 * @author LanceDai
 * @date 2019/4/11 17:59
 * @description *
 */
public class JNATest_2 {
    public interface Clibrary extends Library {
        //加载libhello.so链接库
        Clibrary INSTANTCE = (Clibrary) Native.load("sayhello", Clibrary.class);
        //此方法为链接库中的方法

        int sayHello();
    }

    public static void main(String[] args) {
        Clibrary.INSTANTCE.sayHello();
    }
}

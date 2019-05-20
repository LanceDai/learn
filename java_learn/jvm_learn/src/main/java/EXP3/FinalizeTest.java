package EXP3;

/**
 * @author LanceDai
 * @date 2019/1/13 11:15
 * @description *
 * finalize可以被调用几次
 */
public class FinalizeTest {

    public static FinalizeTest SAVE_HOOK = null;

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
        System.out.println("finalize mehtod executed!");
    }

    public static void main(String[] args) throws Throwable {
        SAVE_HOOK = new FinalizeTest();
        SAVE_HOOK.finalize();
        Thread.sleep(500);
        SAVE_HOOK.finalize();
    }
}



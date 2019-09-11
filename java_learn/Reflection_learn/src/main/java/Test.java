import java.lang.reflect.Field;

/**
 * @author LanceDai
 * @date 2018/11/20 18:35
 * @description *
 */
public class Test {
    private final int a = 1;
    private final Integer aa = 1;

    public static void main(String[] args) {
        Test tmp = new Test();
        Class c = Test.class;
        Field[] fields = c.getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);
            System.out.println("field = " + field);
            try {
                System.out.println("field = " + field.get(tmp));
                field.set(tmp, 2);
                System.out.println("field = " + field.get(tmp));
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
    }
}

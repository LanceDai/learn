import java.lang.reflect.Constructor;


public class ReflectionBasic {


    public static void main(String[] args) throws SecurityException, ClassNotFoundException {


        try {
            Constructor constructor = String.class.getConstructor(StringBuffer.class);
        } catch (NoSuchMethodException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

}

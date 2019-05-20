import java.util.*;

public class ClassInfoDemo {
    public static void main(String[] args) {
        try {
            Class<?> c = Class.forName("java.util.ArrayList");
            Package p = c.getPackage();
            System.out.println(p.getName());
        }
        catch(ClassNotFoundException e) {
            System.out.println("找不到指定类");
        }
    }

}
 
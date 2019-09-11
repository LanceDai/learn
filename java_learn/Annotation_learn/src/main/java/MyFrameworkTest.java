import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class MyFrameworkTest {

    public static void main(String[] args)
            throws IllegalAccessException, IllegalArgumentException,
            InvocationTargetException {

        MyFrameworkClass obj = new MyFrameworkClass();
        Annotation[] annotations = null;
        //提取所有的方法
        for (Method mtd : MyFrameworkClass.class.getMethods()) {
            annotations = mtd.getAnnotations();
            //检测是否方法附加有Annotation
            if (annotations.length > 0) {
                System.out.println("方法" + mtd.getName() + "拥有的Annotation::");
                for (Annotation ann : annotations) {
                    System.out.println(ann.toString());
                }
                //检测附加的注解是不是@MyFrameworkAnnotation
                if (mtd.isAnnotationPresent(MyFrameworkAnnotation.class)) {
                    System.out.println("\n使用反射运行有@MyAnnotation注解的方法:");
                    mtd.invoke(obj);
                }
                System.out.println("---------------------");
            }
        }
    }
}

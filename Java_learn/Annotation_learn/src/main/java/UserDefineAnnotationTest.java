@MyTestAnnotation(color = "red", value = "Hello", arrayAttribute = {4, 5,
        6}, annotationAttr = @MyMetaAnnotation("abc"))
// @MyTestAnnotation("Hello")
public class UserDefineAnnotationTest {

    public static void main(String[] args) {
        if (UserDefineAnnotationTest.class.isAnnotationPresent(MyTestAnnotation.class)) {
            MyTestAnnotation annotation = (MyTestAnnotation) UserDefineAnnotationTest.class
                    .getAnnotation(MyTestAnnotation.class);
            System.out.println(annotation.color());
            System.out.println(annotation.value());
            for (int value : annotation.arrayAttribute())
                System.out.print(value + ",");
            System.out.println();
            MyMetaAnnotation metaAnnotation = annotation.annotationAttr();
            System.out.println(metaAnnotation.value());
        }

    }

}

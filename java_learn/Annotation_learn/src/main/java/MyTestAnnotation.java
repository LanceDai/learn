import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

//元注解
@Retention(RetentionPolicy.RUNTIME) //指明注解生存的时间
@Target({ElementType.METHOD, ElementType.TYPE})   //指明注解适用的场合
public @interface MyTestAnnotation {
    String color() default "blue";

    String value();

    int[] arrayAttribute() default {3, 4, 5};

    MyMetaAnnotation annotationAttr() default @MyMetaAnnotation("元注解类型的属性");
}



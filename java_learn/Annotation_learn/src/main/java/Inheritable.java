import java.lang.annotation.*;

//定义一个具有“继承特性”的注解
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Inherited
public @interface Inheritable {

}

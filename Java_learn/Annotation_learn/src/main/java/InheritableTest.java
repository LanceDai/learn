@Inheritable
class Base {
}

//因为基类附加了有“继承特性”的注解，所以子类自动也拥有此注解
class Child extends Base {
}

public class InheritableTest {
    public static void main(String[] args) {
        //应该输出true
        System.out.println(Child.class.isAnnotationPresent(Inheritable.class));
    }
}


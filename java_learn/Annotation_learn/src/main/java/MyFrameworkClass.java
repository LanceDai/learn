
//此类有三个方法，两个附加了注解，另一个没有
//用于展示在运行时可以通过读取注解信息，确定是否应该调用特定的方法
public class MyFrameworkClass {
    @MyFrameworkAnnotation
    public void runTest() {
        System.out.println("MyFrameworkClass::runTest");
    }

    @Deprecated
    public void print() {
        System.out.println("MyFrameworkClass::print己被废弃");
    }

    public void info() {
        System.out.println("MyFrameworkClass::info");
    }

}


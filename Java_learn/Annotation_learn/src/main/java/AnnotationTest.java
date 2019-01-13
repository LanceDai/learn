
public class AnnotationTest {

    // 加注解@SuppressWarnings之后，所有"己被废弃"的情况，在编译时不再有警告信息
    @SuppressWarnings("deprecation")
    public static void main(String[] args) {
        //JDK文档中指出，runFinalizersOnExit()己被废弃，因此如果使用了此方法，在编译时会有警告信息
        //除非显式添加了@SuppressWarnings注解，才不会报告警告信息
        System.runFinalizersOnExit(true);
        // @Deprecated使此方法成为被“废弃”的,虽然仍然可用，但IDE会加上删除线给出提示
        AnnotationTest.saySomething("此方法己被废弃");

    }

    //声明此方法己被废弃
    @Deprecated
    public static void saySomething(String info) {
        System.out.println(info);
    }

}

class MyClass {
    //@Override要求子类必须覆盖基类的方法，因此，如果参数类型为MyClass，
    //将不能通过编译
    //因为基类Object的equals方法，其参数类型是Object

    //@Override
    public boolean equals(MyClass obj) {
        return super.equals(obj);
    }
}

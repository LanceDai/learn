import java.util.HashSet;

public class InheritsTree {

    public static void main(String[] args) {

        printInheritsTreeOf(new HashSet());

    }

    //打印出任何一个对象的所有基类
    private static void printInheritsTreeOf(Object o) {
        if (o == null) {
            return;
        }
        travelInheritsTree(o.getClass());
    }

    private static void travelInheritsTree(Class<?> classTreeLeaf) {
        if (classTreeLeaf == null) {
            return;
        }
        System.out.println(classTreeLeaf.getName());
        Class<?> parent = classTreeLeaf.getSuperclass();
        if (parent != null) {
            travelInheritsTree(parent);
        }

    }

}

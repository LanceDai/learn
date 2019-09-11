
public class ClassDemo {
    public static void main(String[] args) {
        showClassInfo("test".getClass());
        System.out.println();
        showClassInfo(int.class);
        System.out.println();
        int[] arr = new int[10];
        showClassInfo(arr.getClass());
    }

    // 显示指定类型的信息
    private static void showClassInfo(Class<?> myClass) {
        System.out.println("类名称：" + myClass.getName());
        System.out.println("是否为接口：" + myClass.isInterface());
        System.out.println("是否为基本类型：" + myClass.isPrimitive());
        System.out.println("是否为数组对象：" + myClass.isArray());
        Class<?> parent = myClass.getSuperclass();
        if (parent != null)
            System.out.println("父类名称：" + parent.getName());
    }
}


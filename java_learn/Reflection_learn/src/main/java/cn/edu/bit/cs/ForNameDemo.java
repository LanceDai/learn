package cn.edu.bit.cs;

public class ForNameDemo {
    public static void main(String[] args) {
        try {
            //使用Class.forName方法：
            Class<?> c = Class.forName("cn.edu.bit.cs.ForNameDemo");
            System.out.println("类名称：" + c.getName());
            System.out.println("是否为接口：" +  c.isInterface());
            System.out.println("是否为基本类型：" + c.isPrimitive());
            System.out.println("是否为数组：" + c.isArray());
            System.out.println("父类：" + c.getSuperclass().getName());
        }
        catch(ClassNotFoundException e) {
            System.out.println("找不到指定的类");
        }
    }
} 


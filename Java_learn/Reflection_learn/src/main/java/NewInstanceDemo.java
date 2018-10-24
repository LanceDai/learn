
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

public class NewInstanceDemo {
    public static void main(String[] args) {
        invokeConstructorNoArgu();
        invokeConstructorWithArgu();
    }

    //调用无参构造函数创建对象
    private static void invokeConstructorNoArgu() {
        try {
            Class<?> c = Class.forName("java.util.ArrayList");
            List list = (List) c.newInstance(); // 直接调用Class对象的newInstance方法创建对象

            for (int i = 0; i < 5; i++) {
                list.add("element " + i);
            }

            for (Object o : list.toArray()) {
                System.out.println(o);
            }
        } catch (ClassNotFoundException e) {
            System.out.println("找不到指定的类");
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    //调用有参构造函数创建对象
    private static void invokeConstructorWithArgu() {
        try {
            Class<?> c = Class.forName("Student");

            // 取得对应参数列的构造函数 ,仅适用于JDK1.5及以上版本
            Constructor<?> constructor =
                    c.getConstructor(String.class, int.class);

            // 指定实参
            Object[] argObjs = new Object[2];
            argObjs[0] = "jxl";
            argObjs[1] = 90;
            //创建对象

            Object obj = constructor.newInstance("jxl", 90);
            // 检查结果
            System.out.println(obj);

        } catch (ClassNotFoundException e) {
            System.out.println("找不到类");
        } catch (SecurityException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            System.out.println("没有所指定的方法");
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }
}

//Student类中定义了一个接收两个参数的构造方法
class Student {
    private String name;
    private int score;

    public Student() {
        name = "N/A";
    }

    public Student(String name, int score) {
        this.name = name;
        this.score = score;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public String getName() {
        return name;
    }

    public int getScore() {
        return score;
    }

    public String toString() {
        return name + ":" + score;
    }
}
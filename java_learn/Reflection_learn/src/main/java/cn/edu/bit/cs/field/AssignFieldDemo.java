package cn.edu.bit.cs.field;

import java.lang.reflect.Field;

class TestField {
    public int testInt;
    public String testString;

    public String toString() {
        return testInt + ":" + testString;
    }

    private int privateValue=0;
}

public class AssignFieldDemo {
    public static void main(String[] args) {
        try {
            Class<?> c = Class.forName("cn.edu.bit.cs.field.TestField");
            Object targetObj = c.newInstance();

            Field testInt = c.getField("testInt");
            //注意testInt引用的是一个Field对象，它并不直接关联targetObject对象。
            //所以以下setInt方法的第一个参数要指明需要设置字段值的那个对象
            testInt.setInt(targetObj, 99);

            Field testString = c.getField("testString");
            testString.set(targetObj, "jxl");

            System.out.println(targetObj);

            //访问类的私有成员
            Field testPrivate=c.getDeclaredField("privateValue");
            testPrivate.setAccessible(true);  //允许访问私有成员
            testPrivate.set(targetObj, 100);
            System.out.println(testPrivate.get(targetObj));


        } catch(ArrayIndexOutOfBoundsException e) {
            System.out.println("没有指定类");
        } catch (ClassNotFoundException e) {
            System.out.println("找不到指定的类");
        } catch (SecurityException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            System.out.println("找不到指定的域成员");
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }
}

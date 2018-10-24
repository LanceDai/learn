import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class Test {

    public static Map getObjectFields(Object o) throws IllegalAccessException {
        Class<?> objectClass = o.getClass();
        Field[] fields = objectClass.getDeclaredFields();
        Map<String, Object> fieldMap = new LinkedHashMap<String, Object>();
        for (Field field : fields) {
            field.setAccessible(true);//取消语言访问检查，即可以访问非public修饰的成员变量
//            System.out.println(field.getName() + " " + field.get(o));
            fieldMap.put(field.getName(), field.get(o));
        }
        return fieldMap;

    }

    public static void main(String[] args) throws IllegalAccessException {
        Test1 t = new Test1();
        Map map = getObjectFields(t);
        System.out.println("map = " + map);
        Test2 t1 = new Test2();
        map = getObjectFields(t1);
        System.out.println("map = " + map);
    }
}

class Test1 {
    private int i = 1;
    private int i1 = 2;
    private int i2 = 3;
    private int i3 = 4;
    private int i4 = 5;
}


class Test2 {
    private Integer i = 1;
    private String i1 = "Hello";
    private Test1 i2 = new Test1();
    private Map i3 = new HashMap();
    private int i4 = 5;
}

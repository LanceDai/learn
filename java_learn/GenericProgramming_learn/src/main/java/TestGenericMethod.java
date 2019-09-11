
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;


public class TestGenericMethod {
    //声明一个泛型方法，该泛型方法中带一个T形参，
    private static <T> void fromArrayToCollection(T[] a, Collection<T> c) {
        Collections.addAll(c, a);
    }

    public static void main(String[] args) {
        Object[] oa = new Object[100];
        Collection<Object> co = new ArrayList<Object>();
        //下面代码中T代表Object类型
        fromArrayToCollection(oa, co);
        System.out.println("co = " + co.size());
        String[] sa = new String[100];
        Collection<String> cs = new ArrayList<String>();
        //下面代码中T代表String类型
        fromArrayToCollection(sa, cs);
        System.out.println("cs = " + cs.size());
        //下面代码中T代表Object类型
        fromArrayToCollection(sa, co);
        System.out.println("co = " + co.size());
        Integer[] ia = new Integer[100];
        Float[] fa = new Float[100];
        Number[] na = new Number[100];
        Collection<Number> cn = new ArrayList<Number>();
        //下面代码中T代表Number类型
        fromArrayToCollection(ia, cn);
        System.out.println("cn = " + cn.size());
        //下面代码中T代表Number类型
        fromArrayToCollection(fa, cn);
        System.out.println("cn = " + cn.size());
        //下面代码中T代表Number类型
        fromArrayToCollection(na, cn);
        System.out.println("cn = " + cn.size());
        ///下面代码中T代表String类型
        fromArrayToCollection(na, co);
        System.out.println("co = " + co.size());
        //下面代码中T代表String类型，但na是一个Number数组，
        //因为Number既不是String类型，也不是它的子类，所以出现编译错误
        //fromArrayToCollection(na, cs);
    }
}

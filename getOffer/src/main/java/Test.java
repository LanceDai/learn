import javax.script.SimpleBindings;
import javax.swing.plaf.TableHeaderUI;
import java.util.*;
import java.util.concurrent.*;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;
import java.util.concurrent.locks.ReentrantReadWriteLock;

class Test {
    private static final int SIZE = 100;

    public static void main(String[] args) {
        String str = "abc";
        System.out.println("str = " + str);
        LinkedHashMap linkedHashMap = new LinkedHashMap(SIZE) {
            @Override
            protected boolean removeEldestEntry(Map.Entry eldest) {
                return (size() + 1 > SIZE);
            }
        };
        AbstractQueuedSynchronizer abstractQueuedSynchronizer;
        HashMap hashMap;
        ConcurrentHashMap concurrentHashMap;
        String string;
        TreeMap treeMap = new TreeMap();
        SortedMap sortedMap;
        Object o = Singleton.SINGLETON.getInstance();
//        Integer.hashCode();
//        Long.hashCode();
//        Double.hashCode();
//        Short.hashCode();
//        Character.hashCode();
//        Byte.hashCode();
//        Boolean.hashCode();
//        ThreadLocal
        ConcurrentHashMap concurrentHashMap1;
        AbstractQueuedSynchronizer a;
        ReentrantReadWriteLock reentrantReadWriteLock;
        ThreadFactory threadFactory;
        ThreadLocal<Object> threadLocal = new ThreadLocal<>();
        ReentrantReadWriteLock lock;
        ConcurrentSkipListSet concurrentSkipListSet;
        LinkedBlockingQueue linkedBlockingQueue = new LinkedBlockingQueue(1000);
        ArrayBlockingQueue arrayBlockingQueue = new ArrayBlockingQueue(1000);
        Object o1;
//        o1.wait();
        Callable callable;
        ArrayBlockingQueue arrayBlockingQueue1;
        ArrayList arrayList;
        FutureTask<Boolean> futureTask = new FutureTask<>(() -> {
            System.out.println("Hello");
            return true;
        });
    }
}

 enum Singleton {
    SINGLETON;
    Object o;

    Singleton() {
        this.o = new Object();
    }

    Object getInstance() {
        return this.o;
    }
}

abstract interface tt extends Cloneable, Enumeration{

}

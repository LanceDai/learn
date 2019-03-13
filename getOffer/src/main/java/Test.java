import javax.script.SimpleBindings;
import javax.swing.plaf.TableHeaderUI;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;

class Test {
    private static final int SIZE = 100;

    public static void main(String[] args) {
        String str = new String("abc");
        System.out.println("str = " + str);
        LinkedHashMap linkedHashMap = new LinkedHashMap(SIZE){
            @Override
            protected boolean removeEldestEntry(Map.Entry eldest) {
                return (size() + 1 > SIZE);
            }
        };
        AbstractQueuedSynchronizer
    }
}

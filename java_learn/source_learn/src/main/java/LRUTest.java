import java.util.LinkedHashMap;
import java.util.Map;

public class LRUTest {
    public static void main(String[] args) {
        int size = 100;
        Map map = new LinkedHashMap(size, .75f, true) {
            @Override
            protected boolean removeEldestEntry(Map.Entry eldest) {
                return size() > size;
            }
        };
    }
}

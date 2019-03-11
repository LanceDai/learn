import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

class Test {
    public static void main(String[] args) {
        String str = new String("abc");
        System.out.println("str = " + str);
        LinkedHashMap linkedHashMap = new LinkedHashMap(){
            @Override
            protected boolean removeEldestEntry(Map.Entry eldest) {
                return true;
            }
        };
    }
}

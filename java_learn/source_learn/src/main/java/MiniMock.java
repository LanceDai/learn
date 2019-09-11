import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.ConcurrentHashMap;

@Configuration
public class MiniMock {

    public static <T> T mock(Class<T> type) {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(type);
        enhancer.setCallback((MethodInterceptor) (obj, method, args, proxy) -> {
            // 根据入参匹配container的备选
            String key = obj.getClass().getName() + "_" + method.getName();
            Object value = RetValueContainer.match(key);
            if (null == value) {
                RetValueContainer.setMatcherKey(key);
            }
            return value;
        });
        return type.cast(enhancer.create());
    }

    public static <T> MiniStubbing when(T call) {
        return new MiniStubbing();
    }
}

/**
 * stubbing类
 */
class MiniStubbing {

    public MiniStubbing thenReturn(Object result) {
        if (RetValueContainer.getKey() != null) {
            RetValueContainer.add(RetValueContainer.getKey(), result);
        } else {
            throw new UnsupportedOperationException();
        }
        return this;
    }
}

/**
 * 缓存stubbing的返回值
 */
class RetValueContainer {

    private static String matcherKey = null;
    private static final ConcurrentHashMap<String, Object> candidateMap = new ConcurrentHashMap<>();

    public static void setMatcherKey(String matcherKey) {
        RetValueContainer.matcherKey = matcherKey;
    }

    public static String getKey() {
        return matcherKey;
    }

    public static void add(String key, Object value) {
        candidateMap.put(key, value);
    }

    public static Object match(String hashCode) {
        return candidateMap.get(hashCode);
    }
}

class Target {
    private String a = "default";

    public String getA() {
        return a;
    }

    public void setA(String a) {
        this.a = a;
    }

    public String getXXX(String x) {
        return x + x + x;
    }
}
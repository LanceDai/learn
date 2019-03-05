package test;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;

import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

public class GuavaTest {
    public static void main(String[] args) {
        CacheBuilder.newBuilder().build(new CacheLoader<Long, AtomicLong>() {
            @Override
            public AtomicLong load(Long aLong) throws Exception {
                return new AtomicLong(0);
            }
        });
    }
}


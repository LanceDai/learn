package cn.lancedai.learn.redis.test;

import lombok.extern.slf4j.Slf4j;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import redis.clients.jedis.Jedis;


@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class RedisTest {
    @Value("${spring.redis.host}")
    private String host;
    @Value("${spring.redis.port}")
    private int port;

    @Before
    public void init() {
        log.info("----- start redis test ----");
        log.info("host = " + host);
        log.info("port = " + port);
    }


    @After
    public void finish() {
        log.info("---- test is finish ----");
    }

    @Test
    public void redisTest() {
        Jedis jedis = new Jedis(host, port);
        jedis.set("1", "2");
        log.info(jedis.get("1"));
    }
}

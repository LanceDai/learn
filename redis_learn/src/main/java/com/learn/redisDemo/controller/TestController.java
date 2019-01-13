package com.learn.redisDemo.controller;

import com.learn.redisDemo.bean.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.Serializable;

@RestController
public class TestController {

    private final RedisTemplate<Serializable, Object> redisTemplate;

    @Autowired
    public TestController(RedisTemplate<Serializable, Object> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    @RequestMapping("/set")
    public String setPOJO() {

        User user = new User();
        user.setAge("18");
        user.setGender("男");
        user.setNickname("cherish");
        user.setPassword("123456");
        user.setUsername("admin");
        redisTemplate.opsForValue().set("user1", user);
        return "存储对象";
    }

    @RequestMapping("/get")
    public Object getPOJO() {

        return redisTemplate.opsForValue().get("user1");
    }

}

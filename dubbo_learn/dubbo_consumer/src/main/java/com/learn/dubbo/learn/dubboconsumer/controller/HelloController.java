package com.learn.dubbo.learn.dubboconsumer.controller;

import com.learn.dubbo.learn.dubbointerface.HelloService;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author LanceDai
 * @date 2019/2/18 15:42
 * @description *
 */
@RestController
@Slf4j
public class HelloController {
    @Reference
    private HelloService helloService;

    @GetMapping(value = "/hello", produces = "text/html")
    public String hello() {
        String hello = helloService.sayHello("world");
        log.info(helloService.sayHello("LanceDai"));
        return hello;
    }
}

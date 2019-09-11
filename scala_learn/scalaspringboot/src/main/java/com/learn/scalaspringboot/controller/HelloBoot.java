package com.learn.scalaspringboot.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author LanceDai
 * @date 2019/4/12 17:12
 * @description *
 */
@RestController
public class HelloBoot {
    @RequestMapping(value = "/helloJava", method = RequestMethod.GET)
    public String sayHello() {
        return "Hello";
    }
}

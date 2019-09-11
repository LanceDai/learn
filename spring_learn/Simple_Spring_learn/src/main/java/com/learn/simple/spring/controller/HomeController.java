package com.learn.simple.spring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author LanceDai
 * @date 2019/3/20 10:40
 * @description *
 */
@Controller
public class HomeController {
    @RequestMapping("/")
    public String home(){
        return "index";
    }
}

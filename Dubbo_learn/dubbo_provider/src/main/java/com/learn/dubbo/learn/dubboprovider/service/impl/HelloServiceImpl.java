package com.learn.dubbo.learn.dubboprovider.service.impl;

import com.learn.dubbo.learn.dubbointerface.HelloService;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.stereotype.Component;

/**
 * @author LanceDai
 * @date 2019/2/18 15:37
 * @description *
 */
@Service(interfaceClass = HelloService.class)
@Component
public class HelloServiceImpl implements HelloService {
    @Override
    public String sayHello(String name) {
        return "Hello " + name;
    }
}

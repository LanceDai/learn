package com.learn.simple.spring;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author LanceDai
 * @date 2019/2/20 13:53
 * @description spring容器启动类
 */
@Slf4j
public class StartSpring {

    public static void main(String[] args) {
        // 用我们的配置文件来启动一个 ApplicationContext
        ClassPathXmlApplicationContext context =
                new ClassPathXmlApplicationContext("classpath:application.xml");
        System.out.println("context 启动成功");
//        // 从 context 中取出我们的 Bean，而不是用 new MessageServiceImpl() 这种方式
//        MessageService messageService = context.getBean(MessageService.class);
//        // 这句将输出: hello world
//        System.out.println(messageService.getMessage());
//        float a = 1.0f;
//        float b = 1.0f;
//        float c= b;
//        System.out.println(c == a);
        log.info("容器关闭");
        context.close();
    }
}

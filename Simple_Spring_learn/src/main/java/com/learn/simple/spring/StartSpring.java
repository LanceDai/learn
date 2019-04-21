package com.learn.simple.spring;

import com.learn.simple.spring.service.MessageService;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.aop.aspectj.AspectJAfterAdvice;
import org.springframework.aop.framework.ProxyFactoryBean;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.servlet.ServletContainerInitializer;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author LanceDai
 * @date 2019/2/20 13:53
 * @description spring容器启动类
 */
@Slf4j
@Aspect
public class StartSpring {

    public static void main(String[] args) {
        // 用我们的配置文件来启动一个 ApplicationContext
        ClassPathXmlApplicationContext context =
                new ClassPathXmlApplicationContext("classpath:application.xml");
        System.out.println("context 启动成功");
//        // 从 context 中取出我们的 Bean，而不是用 new MessageServiceImpl() 这种方式
        MessageService messageService = context.getBean(MessageService.class);
//        service.MessageService messageService = context.getBean(service.MessageService.class);
//        // 这句将输出: hello world
//        System.out.println(messageService.getMessage());
//        float a = 1.0f;
//        float b = 1.0f;
//        float c= b;
//        System.out.println(c == a);
        log.info("容器关闭");
        context.close();
        ConcurrentHashMap concurrentHashMap;
        ReentrantLock reentrantLock = new ReentrantLock();
        AbstractQueuedSynchronizer abstractQueuedSynchronizer;
        AspectJAfterAdvice aspectJAfterAdvice;
        ProxyFactoryBean proxyFactoryBean;
        ServletContainerInitializer servletContainerInitializer;
    }
}

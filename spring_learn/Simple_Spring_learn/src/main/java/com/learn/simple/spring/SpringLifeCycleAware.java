package com.learn.simple.spring;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class SpringLifeCycleAware implements ApplicationContextAware, BeanNameAware, BeanFactoryAware{

    private ApplicationContext applicationContext;

    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
        log.info("SpringLifeCycleAware start");
    }

    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        log.info("beanFactory = " + beanFactory);
    }

    public void setBeanName(String name) {
        log.info("beanName is " + name);
    }
}

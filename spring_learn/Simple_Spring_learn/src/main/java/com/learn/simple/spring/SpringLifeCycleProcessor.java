package com.learn.simple.spring;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

/**
 * @author lance
 */
@Slf4j
@Component
public class SpringLifeCycleProcessor implements BeanPostProcessor {

    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {

        if ("annotationBean".equals(beanName)) {
            log.info("SpringLifeCycleProcessor start beanName={}", beanName);
        }
        log.info(beanName + " is plus plus begin");
        return bean;
    }

    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        if ("annotationBean".equals(beanName)) {
            log.info("SpringLifeCycleProcessor end beanName={}", beanName);
        }
        log.info(beanName + " is plus plus after");
        return bean;
    }
}

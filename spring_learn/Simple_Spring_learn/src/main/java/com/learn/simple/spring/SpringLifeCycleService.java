package com.learn.simple.spring;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class SpringLifeCycleService implements InitializingBean, DisposableBean {
    public void afterPropertiesSet() throws Exception {
        log.info("SpringLifeCycleService start");
    }

    public void destroy() throws Exception {
        log.info("SpringLifeCycleService destroy");
    }
}
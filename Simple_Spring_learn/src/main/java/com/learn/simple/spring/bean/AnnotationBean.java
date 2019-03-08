package com.learn.simple.spring.bean;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

import javax.annotation.PreDestroy;
import java.util.Arrays;

@Component
@Slf4j
public class AnnotationBean implements InitializingBean {

//    @PostConstruct
    public void start(){
        log.info("AnnotationBean start");
    }

    @PreDestroy
    public void destroy(){
        log.info("AnnotationBean destroy");
    }

    public void afterPropertiesSet() throws Exception {
        System.out.println(Arrays.toString(this.getClass().getAnnotations()));
    }
}


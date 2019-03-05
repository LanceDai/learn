package com.learn.simple.spring.bean;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

@Component
@Slf4j
public class AnnotationBean {

    @PostConstruct
    public void start(){
        log.info("AnnotationBean start");
    }

    @PreDestroy
    public void destroy(){
        log.info("AnnotationBean destroy");
    }
}


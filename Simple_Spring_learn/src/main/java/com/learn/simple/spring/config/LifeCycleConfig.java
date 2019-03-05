package com.learn.simple.spring.config;

import com.learn.simple.spring.bean.SpringLifeCycle;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LifeCycleConfig {


    @Bean(initMethod = "start", destroyMethod = "destroy")
    public SpringLifeCycle springLifeCycle(){

        return new SpringLifeCycle();
    }
}

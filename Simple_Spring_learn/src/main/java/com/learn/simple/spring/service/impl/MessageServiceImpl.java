package com.learn.simple.spring.service.impl;

import com.learn.simple.spring.service.MessageService;
import org.springframework.stereotype.Service;

/**
 * @author LanceDai
 * @date 2019/2/20 13:57
 * @description *
 */
@Service
public class MessageServiceImpl implements MessageService {
    public String getMessage() {
        return "Hello World";
    }
}

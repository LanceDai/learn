package com.learn.bean;

import lombok.Data;

import java.util.Date;

/**
 * @author LanceDai
 * @date 2018/11/22 15:14
 * @description *
 */
@Data
public class Message {
    //id
    private Long id;
    //消息
    private String msg;
    //时间戳
    private Date sendTime;
}

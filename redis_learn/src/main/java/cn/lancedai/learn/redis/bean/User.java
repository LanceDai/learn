package cn.lancedai.learn.redis.bean;

import lombok.Data;

import java.io.Serializable;

@Data
public class User implements Serializable {

    private String username;
    private String password;
    private String nickname;
    private String age;
    private String gender;
}
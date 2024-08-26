package com.example.demo.model;

import lombok.Data;

import java.util.Date;

@Data
public class UserInfo {
    private Integer id;
    private String password;
    private String userName;
    private String githubUrl;
    private Byte deleteFlag;
    private Date createTime;
    private Date updateTime;
}

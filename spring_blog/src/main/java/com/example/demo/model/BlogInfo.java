package com.example.demo.model;

import com.example.demo.utils.DateUtils;
import lombok.Data;

import java.util.Date;

@Data
public class BlogInfo {
    private Integer id;
    private String title;
    private String content;
    private Integer userId;
    private Integer deleteFlag;
    private Date createTime;
    private Integer loginUser;

    public String getCreateTime() {
        return DateUtils.format(createTime);
    }
}

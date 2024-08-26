package com.example.demo.service;

import com.example.demo.mapper.BlogInfoMapper;
import com.example.demo.mapper.UserInfoMapper;
import com.example.demo.model.BlogInfo;
import com.example.demo.model.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserInfoMapper userInfoMapper;
    @Autowired
    private BlogInfoMapper blogInfoMapper;

    //通过username获取改用户的所有信息
    public UserInfo getUserInfo(String username) {
        return userInfoMapper.selectByName(username);
    }

    public UserInfo selectById(Integer userId) {
        return userInfoMapper.selectById(userId);
    }

    public UserInfo getUserInfoByBlogId(Integer blogId) {
        UserInfo userInfo = null;
        BlogInfo blogInfo = blogInfoMapper.selectById(blogId);
        if(blogInfo != null && blogInfo.getUserId()>0){
            userInfo =userInfoMapper.selectById(blogInfo.getUserId());
            return userInfo;
        }
        return null;
    }
}

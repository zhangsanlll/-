package com.example.demo.mapper;

import com.example.demo.model.UserInfo;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserInfoMapper {
    //根据用户名查询用户信息
    @Select("select id,user_name,password,github_url,delete_flag,create_time from user where user_name = #{name}")
    UserInfo selectByName(String name);
    //根据id查询用户信息
    @Select("select id,user_name,password,github_url,delete_flag,create_time from user where id = #{id}")
    UserInfo selectById(Integer id);

}

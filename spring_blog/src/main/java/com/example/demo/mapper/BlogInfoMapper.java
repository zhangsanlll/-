package com.example.demo.mapper;

import com.example.demo.model.BlogInfo;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface BlogInfoMapper {

    //获取所有博客
    @Select("select id,title,content,user_id,delete_flag,create_time,update_time from blog where delete_flag = 0 order by create_time desc")
    List<BlogInfo> selectAllBlog();

    //根据博客id查询博客信息（返回的是博客）
    @Select("select id,title,content,user_id,delete_flag,create_time,update_time from blog where delete_flag = 0 and id = #{id}")
    BlogInfo selectById(Integer id);

    //根据博客id修改博客信息（包括删除，将deleteFlag改为1）
    //使用xml写

    int updateBlog(BlogInfo blogInfo);

    //插入新的博客
    @Insert("insert into blog (title,content,user_id)values(#{title},#{content},#{userId})")
    int insertBlog(BlogInfo blogInfo);
}

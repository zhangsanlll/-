package com.example.demo.service;

import com.example.demo.mapper.BlogInfoMapper;
import com.example.demo.model.BlogInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BlogService {
    @Autowired
    private BlogInfoMapper blogInfoMapper;

    //查看所有博客列表
    public List<BlogInfo> getBlogList() {
        return blogInfoMapper.selectAllBlog();
    }

    //根据博客id动态获取博客页详情
    public BlogInfo getBlogDetails(Integer BlogId) {
        return blogInfoMapper.selectById(BlogId);
    }

    public int insertBlog(BlogInfo blogInfo) {

        return blogInfoMapper.insertBlog(blogInfo);
    }

    public int updateBlog(BlogInfo blogInfo) {
        return blogInfoMapper.updateBlog(blogInfo);
    }
}

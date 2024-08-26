package com.example.demo.mapper;

import com.example.demo.model.BlogInfo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class BlogInfoMapperTest {

    @Autowired
    private BlogInfoMapper blogInfoMapper;
    @Test
    void selectAllBlog() {
        System.out.println(blogInfoMapper.selectAllBlog());
    }

    @Test
    void selectById() {
        System.out.println(blogInfoMapper.selectById(2));
    }

    @Test
    void updateById() {
        BlogInfo blogInfo = new BlogInfo();
        blogInfo.setId(4);
        blogInfo.setTitle("333");
        blogInfo.setContent("333");
        blogInfo.setDeleteFlag(0);
        blogInfoMapper.updateBlog(blogInfo);

    }

    @Test
    void insertBlog() {
        BlogInfo blogInfo = new BlogInfo();
        //blogInfo.setId(4);
        blogInfo.setTitle("111");
        blogInfo.setContent("llllll");
        blogInfo.setUserId(4);
        blogInfoMapper.insertBlog(blogInfo);
    }
}
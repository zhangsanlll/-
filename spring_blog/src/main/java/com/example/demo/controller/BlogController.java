package com.example.demo.controller;

import com.example.demo.constant.Constants;
import com.example.demo.jwt.JWTUtils;
import com.example.demo.model.BlogInfo;
import com.example.demo.model.Result;
import com.example.demo.service.BlogService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j

@RestController
@RequestMapping("/blog")
public class BlogController {

    @Autowired
    private BlogService blogService;

    /*实现博客列表,返回的是所有博客
    * */
    @RequestMapping("/getList")
    //返回的是列表，但会被统一数据返回结果，就会包装成result
    public List<BlogInfo> getList(){
        return blogService.getBlogList();
    }

    /*
    * 根据博客id动态获取博客页详情
    * */
    @RequestMapping("/getBlogDetails")
    public BlogInfo getBlogDetails(Integer blogId,HttpServletRequest request){

        BlogInfo blogInfo = blogService.getBlogDetails(blogId);
        //获取当前登录用户的id
        String token = request.getHeader(Constants.REQUEST_USER_TOKEN);
        Integer loginUserId = JWTUtils.getUserIdFromToken(token);
        if(loginUserId == null || blogInfo.getUserId() == loginUserId){
            blogInfo.setLoginUser(1);
        }
        return blogInfo;
    }

    /*
    实现发布博客
    * */
    @RequestMapping("/addBlog")
    public Result addBlog(String title, String content, HttpServletRequest request){
        //获取当前登录用户id
        String JwtToken = request.getHeader(Constants.REQUEST_USER_TOKEN);
        Integer loginUserId = JWTUtils.getUserIdFromToken(JwtToken);
        if(loginUserId == null || loginUserId < 1){
            return Result.fail(-1,"用户未登录");
        }
        BlogInfo blogInfo = new BlogInfo();
        blogInfo.setUserId(loginUserId);
        blogInfo.setTitle(title);
        blogInfo.setContent(content);
        blogService.insertBlog(blogInfo);
        return Result.success(true);
    }

    /*
    处理删除/修改逻辑
    * */

    @RequestMapping("/update")
    public Result update(@RequestBody BlogInfo blogInfo){
        int result = blogService.updateBlog(blogInfo);
        log.info("result:{}",result);
        if( result == 1){
            return Result.success(true);
        }
        return Result.fail(-1,"未编辑成功");
    }

    @RequestMapping("/delete")
    public boolean delete(Integer blogId){
        BlogInfo blogInfo = new BlogInfo();
        blogInfo.setId(blogId);
        blogInfo.setDeleteFlag(1);
        blogService.updateBlog(blogInfo);
        return true;
    }
}

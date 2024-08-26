package com.example.demo.interceptor;

import com.example.demo.jwt.JWTUtils;
import io.jsonwebtoken.Claims;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Slf4j
@Component
public class LoginInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //从header中获取token
        System.out.println(request.getRequestURI());
        String token = request.getHeader("user_token");
        log.info("从header中获取token：{}",token);
        //验证用户token
        Claims claims = JWTUtils.parseJwt(token);
        log.info("获取claims：{}",claims);
        if(claims != null){
            log.info("token验证成功，放行");
            return true;
        }
        response.setStatus(401);
        return false;
    }
}

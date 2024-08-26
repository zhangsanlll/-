package com.example.demo.config;

import com.example.demo.model.Result;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

//统一返回结果,重写两个方法，一个判断是否执行下面的方法，一个执行该方法
@ControllerAdvice
public class ResponseAdvice implements ResponseBodyAdvice {
    @Override
    public boolean supports(MethodParameter returnType, Class converterType) {
        return true;
    }


    @SneakyThrows//writeValueAsString抛出的异常，用注解去解决
    @Override
    public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType, Class selectedConverterType, ServerHttpRequest request, ServerHttpResponse response) {
        //判断返回的数据格式是否正确，如果是String类型的话需要转换一下格式
        if(body instanceof Result){
            return body;
        }
        if(body instanceof String){
            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.writeValueAsString(Result.success(body));
        }
        return Result.success(body);
    }
}

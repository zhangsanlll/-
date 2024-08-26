package com.example.demo.config;

import com.example.demo.model.Result;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

//统一异常处理，可以根据不同的异常去打印不同的日志，更好的判断错误出处
@ControllerAdvice
public class ExceptionAdvice {
    @ExceptionHandler(Exception.class)
    public Result exceptionAdvice(Exception e){
        e.printStackTrace();
        return Result.fail(-1,e.getMessage());
    }
}

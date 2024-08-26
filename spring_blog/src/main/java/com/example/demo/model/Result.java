package com.example.demo.model;

import com.example.demo.constant.Enums;
import lombok.Data;

@Data
public class Result {
    private int code;//业务码
    private String msg;//错误信息
    private Object data;//接口响应的数据
    /*
    * 业务执行成功时返回的方法
    * */
    public static Result success(Object data){
        Result result = new Result();
        //将状态码设置成成功状态
        result.setCode(Enums.RESULT_CODE_SUCCESS);
        //成功，返回空字符串
        result.setMsg("");
        result.setData(data);
        return result;
    }

    /*
    业务执行失败时返回的方法
    * */
    public static Result fail(int code,String msg){
        Result result = new Result();
        //将状态码设置成失败状态
        result.setCode(Enums.RESULT_CODE_FAIL);
        //失败，返回空错误信息
        result.setMsg(msg);
        result.setData("");
        return result;
    }

    /*
    * 用户未登录时返回的方法
    * */
    public static Result unlogin(int code,String msg,Object data){
        Result result = new Result();
        //将状态码设置成成功状态
        result.setCode(Enums.RESULT_CODE_UNLOGIN);
        result.setMsg("用户未登录");
        result.setData(data);
        return result;
    }
}

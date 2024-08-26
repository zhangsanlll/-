package com.example.demo.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtParserBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.io.Encoders;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;

import javax.crypto.SecretKey;
import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Slf4j

public class JWTUtils {
    //过期毫秒时长 30min
    public static final long Exception = 30 * 60 * 1000;
    //密钥
    private static final String secretString = "ywwsCL8MYtkzo2422Rhg5NkxPILGtzVsiNjNL5YcCfs=";
    //生成安全密钥
    private static final SecretKey KEY = Keys.hmacShaKeyFor(Decoders.BASE64.decode(secretString));


    /*生成JWT
     * */
    public static String  genJwt(Map<String,Object> claim){
        //自定义信息
        String token = Jwts.builder().
                setClaims(claim).//自定义内容，负载
                        setIssuedAt(new Date()).//设置签发时间
                        setExpiration(new Date(System.currentTimeMillis()+Exception))//设置过期时间
                .signWith(KEY)//签名算法
                .compact();
        return token;
    }
    /*校验token,将token传进来进行校验
     * */
    public static Claims parseJwt(String token){
        if(!StringUtils.hasLength(token)){
            return null;//校验失败
        }
        //创建解析器，设置签名密钥，JWT的api，直接调用即可
        //KEY每次生成的都不一样，所以需要注意是不是一样的密钥
        JwtParserBuilder jwtParserBuilder = Jwts.parserBuilder().setSigningKey(KEY);
        Claims claims = null;
        try{
            //解析token
            claims = jwtParserBuilder.build().parseClaimsJws(token).getBody();
        }catch (Exception e){
            //签名验证失败
            log.error("解析token错误：{}",token);
            return null;
        }
        return claims;
    }

    /*
    从token中获取用户ID
    * */
    public static Integer getUserIdFromToken(String jwtToken){
        //Claims用于处理和验证令牌的声明
        Claims claims = JWTUtils.parseJwt(jwtToken);
        if(claims != null){
            Map<String ,Object> userInfo = new HashMap<>(claims);
            return (Integer) userInfo.get("id");
        }
        return null;
    }

}

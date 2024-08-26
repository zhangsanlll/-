package com.example.demo.jwtutils;


import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtParserBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.io.Encoder;
import io.jsonwebtoken.io.Encoders;
import io.jsonwebtoken.security.Keys;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.crypto.SecretKey;
import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@SpringBootTest
public class JWTUtilsTest {
    //过期毫秒时长 30min
    public static final long Exception = 30 * 60 * 1000;
    //密钥
    private static final String secretString = "ywwsCL8MYtkzo2422Rhg5NkxPILGtzVsiNjNL5YcCfs=";
    //生成安全密钥
    private static final SecretKey KEY = Keys.hmacShaKeyFor(Decoders.BASE64.decode(secretString));

    /*
     *生成密钥
     */
    @Test
    public void genKey(){
        Key key = Keys.secretKeyFor(SignatureAlgorithm.HS256);
        String secretString = Encoders.BASE64.encode(key.getEncoded());
        System.out.println(secretString);
    }

    /*生成JWT
    * */
    @Test
    public void genJwt(){
        //自定义信息
        Map<String,Object> claim = new HashMap<>();
        claim.put("id",1);
        claim.put("username","zhangsan");
        String token = Jwts.builder().
                setClaims(claim).//自定义内容，负载
                setIssuedAt(new Date()).//设置签发时间
                setExpiration(new Date(System.currentTimeMillis()+Exception))//设置过期时间
                .signWith(KEY)//签名算法
                .compact();
        System.out.println(token);
    }
    /*校验token
    * */
    @Test
    public void parseJwt(){
        String token = "eyJhbGciOiJIUzI1NiJ9.eyJpZCI6MSwidXNlcm5hbWUiOiJ6aGFuZ3Nhb" +
                "iIsImlhdCI6MTcyMjgzMDMwMSwiZXhwIjoxNzIyODMyMTAxfQ.kM6L4KzOJyN5UfwytRd9bMuggvWyWPBRlXjWGqUZ2Ac";
        //创建解析器，设置签名密钥，JWT的api，直接调用即可
        //KEY每次生成的都不一样，所以需要注意是不是一样的密钥
        JwtParserBuilder jwtParserBuilder = Jwts.parserBuilder().setSigningKey(KEY);
        //解析token
        Claims claims = jwtParserBuilder.build().parseClaimsJws(token).getBody();
        System.out.println(claims);
    }

}

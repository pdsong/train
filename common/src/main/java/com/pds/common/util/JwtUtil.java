package com.pds.common.util;

import cn.hutool.core.date.DateField;
import cn.hutool.core.date.DateTime;
import cn.hutool.crypto.GlobalBouncyCastleProvider;
import cn.hutool.json.JSONObject;
import cn.hutool.jwt.JWT;
import cn.hutool.jwt.JWTPayload;
import cn.hutool.jwt.JWTUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

public class JwtUtil {

    private final static Logger LOG = LoggerFactory.getLogger(JwtUtil.class);


    /**
     * 盐值很重要 不能泄露 每个项目都不同 放置到配置文件中
     */
    private static  final String key="9999";

    public static String createToken(Long id,String mobile){
        DateTime now= DateTime.now();
        DateTime expTime=now.offsetNew(DateField.HOUR,24);
        Map<String,Object> payload=new HashMap<>();
        //签发时间
        payload.put(JWTPayload.ISSUED_AT,now);
        //过期时间
        payload.put(JWTPayload.EXPIRES_AT,expTime);
        //生效时间
        payload.put(JWTPayload.NOT_BEFORE,now);
        //内容
        payload.put("id",id);
        payload.put("mobile",mobile);
        String token=JWTUtil.createToken(payload,key.getBytes());
        LOG.info("生成token->{}",token);
        return token;
    }

    public static boolean validate(String token) {
        LOG.info("开始JWT token校验,token->{}", token);
        GlobalBouncyCastleProvider.setUseBouncyCastle(false);
        JWT jwt = JWTUtil.parseToken(token).setKey(key.getBytes());
        // validate包含了verify
        boolean validate = jwt.validate(0);
        LOG.info("JWT token校验结果->{}", validate);
        return validate;
    }

    public static JSONObject getJSONObject(String token) {
        GlobalBouncyCastleProvider.setUseBouncyCastle(false);
        JWT jwt = JWTUtil.parseToken(token).setKey(key.getBytes());
        JSONObject payloads = jwt.getPayloads();
        payloads.remove(JWTPayload.ISSUED_AT);
        payloads.remove(JWTPayload.EXPIRES_AT);
        payloads.remove(JWTPayload.NOT_BEFORE);
        LOG.info("根据token获取原始内容-> {}", payloads);
        return payloads;
    }

    public static void main(String[] args) {
        String token=createToken(1111333311L,"2222444422");
        validate(token);
        getJSONObject(token);
    }
}

package com.herman.security.app.validate.code.impl;

import com.herman.security.core.enums.ValidateCodeType;
import com.herman.security.core.exception.ValidateCodeException;
import com.herman.security.core.validate.code.base.ValidateCode;
import com.herman.security.core.validate.code.base.ValidateCodeRepository;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.ServletWebRequest;

import java.util.concurrent.TimeUnit;

/**
 * app验证码保存类
 *
 * @author hsh
 * @create 2018-12-01 16:39
 **/
@Component
public class RedisValidateCodeRepository implements ValidateCodeRepository{

    @Autowired
    private RedisTemplate<Object,Object>  redisTemplate;

    @Override
    public void save(ServletWebRequest request, ValidateCode code, ValidateCodeType codeType) {
        redisTemplate.opsForValue().set(buildKey(request,codeType),code,30, TimeUnit.MINUTES);
    }

    @Override
    public ValidateCode get(ServletWebRequest request, ValidateCodeType validateCodeType) {
        Object value = redisTemplate.opsForValue().get(buildKey(request, validateCodeType));
        if(value==null){
            return null;
        }
        return (ValidateCode) value;
    }

    @Override
    public void remove(ServletWebRequest request, ValidateCodeType codeType) {
        redisTemplate.delete(buildKey(request,codeType));
    }

    private String buildKey(ServletWebRequest request, ValidateCodeType codeType) {
        String deviceId = request.getHeader("deviceId");
        if(StringUtils.isBlank(deviceId)){
            throw new ValidateCodeException("请在请求头中携带deviceId");
        }
        return "code:"+codeType.toString().toLowerCase()+":"+deviceId;
    }
}

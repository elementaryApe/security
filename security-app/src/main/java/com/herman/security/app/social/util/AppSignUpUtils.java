package com.herman.security.app.social.util;

import com.herman.security.app.exception.AppSecretException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.oauth2.common.exceptions.UnapprovedClientAuthenticationException;
import org.springframework.social.connect.*;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.WebRequest;

import java.util.concurrent.TimeUnit;

/**
 * app注册工具类
 * app环境下替换providerSignInUtils，避免由于没有session导致读不到社交用户信息的问题
 *
 * @author hsh
 * @create 2018-12-13 17:30
 **/
@Component
public class AppSignUpUtils {

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private ConnectionFactoryLocator connectionFactoryLocator;

    @Autowired
    private UsersConnectionRepository usersConnectionRepository;

    /**
     * 保存第三方用户信息到redis中
     *
     * @param request
     * @param connectionData
     */
    public void saveConnectionDate(WebRequest request, ConnectionData connectionData) {
        redisTemplate.opsForValue().set(getKey(request), connectionData, 10, TimeUnit.MINUTES);
    }

    /**
     * 注册第三方用户信息
     *
     * @param request
     * @param userId
     */
    public void doPostSignUp(WebRequest request, String userId) {
        String key = getKey(request);
        if (!redisTemplate.hasKey(key)) {
            throw new AppSecretException("无法找到缓存的用户社交账号信息");
        }
        ConnectionData connectionData = (ConnectionData) redisTemplate.opsForValue().get(key);
        Connection<?> connection = connectionFactoryLocator.getConnectionFactory(connectionData.getProviderId()).createConnection(connectionData);
        ConnectionRepository connectionRepository = usersConnectionRepository.createConnectionRepository(userId);
        connectionRepository.addConnection(connection);
        redisTemplate.delete(key);
        //保证注册成功即登录
        String header = request.getHeader("Authorization");
        if (header == null || !header.startsWith("Basic ")) {
            throw new UnapprovedClientAuthenticationException("请求头中缺失client信息");
        }
    }

    private String getKey(WebRequest request) {
        String deviceId = request.getHeader("deviceId");
        if (StringUtils.isBlank(deviceId)) {
            throw new AppSecretException("设备id参数不能为空");
        }
        return "herman:security:social.connect." + deviceId;
    }

}

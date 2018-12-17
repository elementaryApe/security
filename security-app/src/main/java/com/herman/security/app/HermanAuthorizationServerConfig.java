package com.herman.security.app;

import com.herman.security.core.properties.SecurityProperties;
import org.apache.commons.lang3.ArrayUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.oauth2.config.annotation.builders.InMemoryClientDetailsServiceBuilder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.security.oauth2.provider.token.TokenEnhancerChain;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 认证配置
 *
 * @author hsh
 * @create 2018-11-30 17:08
 **/
@Configuration
@EnableAuthorizationServer
public class HermanAuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private SecurityProperties securityProperties;

    @Autowired
    private TokenStore tokenStore;

    @Autowired(required = false)
    private JwtAccessTokenConverter jwtAccessTokenConverter;

    @Autowired(required = false)
    private TokenEnhancer jwtTokenEnhancer;


    /**
     * Token端点配置token存储策略、authenticationManager、userDetailsService
     */
    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        endpoints.tokenStore(tokenStore)
                .authenticationManager(authenticationManager)
                .userDetailsService(userDetailsService);
        if (jwtAccessTokenConverter != null && jwtTokenEnhancer != null) {
            //通过token链将增强器（转换成jwt、加额外信息）关联起来
            TokenEnhancerChain tokenEnhancerChain = new TokenEnhancerChain();
            List<TokenEnhancer> enhancers = new ArrayList<>();
            enhancers.add(jwtTokenEnhancer);
            enhancers.add(jwtAccessTokenConverter);
            tokenEnhancerChain.setTokenEnhancers(enhancers);
            //链路信息写人tokenEnhancer
            endpoints.tokenEnhancer(tokenEnhancerChain).accessTokenConverter(jwtAccessTokenConverter);
        }
    }

    /**
     * 客户端应用配置
     */
    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        InMemoryClientDetailsServiceBuilder builder = clients.inMemory();//client信息存在内存中,对应的还有存在数据库中
        if (ArrayUtils.isNotEmpty(securityProperties.getOauth2().getClients())) {
            Arrays.stream(securityProperties.getOauth2().getClients()).forEach(client -> {
                builder.withClient(client.getClientId())
                        .secret(client.getClientSecret())
                        .accessTokenValiditySeconds(client.getAccessTokenValidateSeconds())
                        .authorizedGrantTypes("password", "refresh_token", "authorization_code")//四种认证模式中的哪些
                        .refreshTokenValiditySeconds(2592000)//刷新令牌时间
                        .scopes("all");
            });
        }
    }


}

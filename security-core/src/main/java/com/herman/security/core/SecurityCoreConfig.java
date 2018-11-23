package com.herman.security.core;

import com.herman.security.core.properties.SecurityProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @author hsh
 * @create 2018-11-17 14:14
 **/
@Configuration
//配置配置文件读取器生效
@EnableConfigurationProperties(SecurityProperties.class)
public class SecurityCoreConfig {
}

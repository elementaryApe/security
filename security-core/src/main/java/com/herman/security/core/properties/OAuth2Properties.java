package com.herman.security.core.properties;

/**
 * 认证配置
 * @author hsh
 * @create 2018-12-14 14:43
 **/
public class OAuth2Properties {

    private String jwtSigningKey="herman";

    private OAuth2ClientProperties[] clients = {};

    public String getJwtSigningKey() {
        return jwtSigningKey;
    }

    public void setJwtSigningKey(String jwtSigningKey) {
        this.jwtSigningKey = jwtSigningKey;
    }

    public OAuth2ClientProperties[] getClients() {
        return clients;
    }

    public void setClients(OAuth2ClientProperties[] clients) {
        this.clients = clients;
    }
}

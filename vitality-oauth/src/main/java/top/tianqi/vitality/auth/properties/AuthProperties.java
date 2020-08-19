package top.tianqi.vitality.auth.properties;

import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;

/**
 * auth信息配置类
 *
 * @Author wkh
 * @Date 2020/8/18 11:14
 */
@SpringBootConfiguration
@PropertySource(value = "classpath:vitality-auth.properties")
@ConfigurationProperties(value = "vitality.auth")
public class AuthProperties {

    /** 认证服务器可以根据多种Client来发放对应的令牌*/
    private ClientsProperties[] clients = {};
    /** access_token,token默认有效时间*/
    private int accessTokenValiditySeconds = 60 * 60 * 24;
    /** refresh_token,token刷新后有效时间*/
    private int refreshTokenValiditySeconds = 60 * 60 * 24 * 7;
    /** 验证码配置类*/
    private ValidateCodeProperties code = new ValidateCodeProperties();
    /** 免认证路径*/
    private String anonUrl;

    public ClientsProperties[] getClients() {
        return clients;
    }

    public void setClients(ClientsProperties[] clients) {
        this.clients = clients;
    }

    public int getAccessTokenValiditySeconds() {
        return accessTokenValiditySeconds;
    }

    public void setAccessTokenValiditySeconds(int accessTokenValiditySeconds) {
        this.accessTokenValiditySeconds = accessTokenValiditySeconds;
    }

    public int getRefreshTokenValiditySeconds() {
        return refreshTokenValiditySeconds;
    }

    public void setRefreshTokenValiditySeconds(int refreshTokenValiditySeconds) {
        this.refreshTokenValiditySeconds = refreshTokenValiditySeconds;
    }

    public ValidateCodeProperties getCode() {
        return code;
    }

    public void setCode(ValidateCodeProperties code) {
        this.code = code;
    }

    public String getAnonUrl() {
        return anonUrl;
    }

    public void setAnonUrl(String anonUrl) {
        this.anonUrl = anonUrl;
    }
}

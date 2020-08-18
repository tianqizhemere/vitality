package top.tianqi.vitality.auth.properties;

/**
 * 认证令牌配置
 *
 * @Author wkh
 * @Date 2020/8/18 11:10
 */
public class ClientsProperties {
    /** client_id*/
    private String client;
    /** client_secret*/
    private String secret;
    /** 当前令牌支持的认证类型*/
    private String grantType = "password,authorization_code,refresh_token";
    /** 认证范围*/
    private String scope = "all";

    public String getClient() {
        return client;
    }

    public void setClient(String client) {
        this.client = client;
    }

    public String getSecret() {
        return secret;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }

    public String getGrantType() {
        return grantType;
    }

    public void setGrantType(String grantType) {
        this.grantType = grantType;
    }

    public String getScope() {
        return scope;
    }

    public void setScope(String scope) {
        this.scope = scope;
    }
}

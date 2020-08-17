package top.tianqi.vitality.auth.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;

/**
 * OauthService 配置
 * @Author wkh
 * @Date 2020/8/10 11:43
 */
@Configuration
public class OauthConfig extends AuthorizationServerConfigurerAdapter {

    /** 用户认证管理类 */
    @Autowired
    private AuthenticationManager authenticationManager;
    /** */
    @Autowired
    private UserDetailsService userDetailsService;

    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients.inMemory() // 使用in-memory存储
                .withClient("malldemo") // client_id
                .secret("pgDBd99tOX8d") // client_secret 秘钥
                .authorizedGrantTypes("authorization_code", "refresh_token", "implicit", "password", "client_credentials") // 该client允许的授权类型,认证模式
                .scopes("webmall"); // 允许授权的范围
    }

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        endpoints
                .authenticationManager(authenticationManager)
                .userDetailsService(userDetailsService);
    }
}

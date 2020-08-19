package top.tianqi.vitality.auth.config;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.builders.InMemoryClientDetailsServiceBuilder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.redis.RedisTokenStore;
import top.tianqi.vitality.auth.properties.AuthProperties;
import top.tianqi.vitality.auth.properties.ClientsProperties;
import top.tianqi.vitality.auth.service.impl.AuthUserDetailServiceImpl;
import top.tianqi.vitality.auth.translator.AuthWebResponseExceptionTranslator;

import javax.annotation.Resource;

/**
 * 认证服务器安全配置类
 *
 * @Author wkh
 * @Date 2020/8/17 15:17
 */
@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfigure extends AuthorizationServerConfigurerAdapter {

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private RedisConnectionFactory redisConnectionFactory;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private AuthWebResponseExceptionTranslator authWebResponseExceptionTranslator;
    @Resource(name = "authUserDetailService")
    private AuthUserDetailServiceImpl userDetailService;
    @Resource(name = "authProperties")
    private AuthProperties authProperties;


    /**
     * <p>客户端从认证服务器获取令牌的时候，必须使用client_id为tianqi，
     * client_secret为123456的标识来获取;</p>
     * <p>client_id支持password模式获取令牌，并且可以通过refresh_token来获取新的令牌;</p>
     * <p>在获取client_id为tianqi的令牌的时候，scope只能指定为all，否则将获取失败;</p>
     *
     * @param clients
     * @throws Exception
     */
    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        ClientsProperties[] clientsArr = authProperties.getClients();
        InMemoryClientDetailsServiceBuilder builder = clients.inMemory();
        if (ArrayUtils.isNotEmpty(clientsArr)) {
            for (ClientsProperties client : clientsArr) {
                if (StringUtils.isEmpty(client.getClient())) {
                    throw new Exception("client不能为空");
                }
                if (StringUtils.isEmpty(client.getSecret())) {
                    throw new Exception("secret不能为空");
                }
                String[] grantTypes = StringUtils.splitByWholeSeparatorPreserveAllTokens(client.getGrantType(), ",");
                builder.withClient(client.getClient())
                        .secret(passwordEncoder.encode(client.getSecret()))
                        .authorizedGrantTypes(grantTypes)
                        .scopes(client.getScope());
            }
        }
    }

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) {
        endpoints.tokenStore(tokenStore())
                    .userDetailsService(userDetailService)
                .authenticationManager(authenticationManager)
                .tokenServices(defaultTokenServices())
                .exceptionTranslator(authWebResponseExceptionTranslator);
    }

    /** 将认证服务器生成的令牌存储到Redis中 */
    @Bean
    public TokenStore tokenStore() {
        return new RedisTokenStore(redisConnectionFactory);
    }

    /**
     * 令牌的存储配置
     * @return
     */
    @Primary
    @Bean
    public DefaultTokenServices defaultTokenServices() {
        DefaultTokenServices tokenServices = new DefaultTokenServices();
        // 设置令牌存储策略
        tokenServices.setTokenStore(tokenStore());
        // 开启刷新令牌的支持
        tokenServices.setSupportRefreshToken(true);
        // 令牌的有效时间
        tokenServices.setAccessTokenValiditySeconds(authProperties.getAccessTokenValiditySeconds());
        // 刷新令牌有效时间
        tokenServices.setRefreshTokenValiditySeconds(authProperties.getRefreshTokenValiditySeconds());
        return tokenServices;
    }
}

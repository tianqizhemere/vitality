package top.tianqi.vitality.auth.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import top.tianqi.vitality.auth.properties.AuthProperties;

import javax.annotation.Resource;

/**
 * 资源服务配置类
 * <p>处理非/oauth/开头的请求，其主要用于资源的保护，
 * 客户端只能通过OAuth2协议发放的令牌来从资源服务器中获取受保护的资源</p>
 *
 * @Author wkh
 * @Date 2020/8/17 15:04
 */
@Configuration
@EnableResourceServer
public class ResourceServerConfigure extends ResourceServerConfigurerAdapter {

    @Resource(name = "authProperties")
    private AuthProperties authProperties;

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .requestMatchers().antMatchers("/**")
                .and()
                .authorizeRequests()
                .antMatchers(authProperties.getAnonUrl()).permitAll()
                .antMatchers("/**").authenticated();
    }
}

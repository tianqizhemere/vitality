package top.tianqi.vitality.upload.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;

/**
 * 资源认证类
 * 配置表示所有访问vitality-server-upload的请求都需要认证，只有通过认证服务器发放的令牌才能进行访问。
 *
 * @Author wkh
 * @Date 2020/8/18 14:38
 */
@Configuration
@EnableResourceServer
public class ServerUploadResourceServerConfigure extends ResourceServerConfigurerAdapter {

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .requestMatchers().antMatchers("/**")
                .and()
                .authorizeRequests()
                .antMatchers("/**").authenticated();
    }
}

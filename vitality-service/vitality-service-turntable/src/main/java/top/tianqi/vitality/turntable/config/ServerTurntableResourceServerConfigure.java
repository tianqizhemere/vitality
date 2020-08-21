package top.tianqi.vitality.turntable.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import top.tianqi.vitality.handler.VitalityAccessDeniedHandler;
import top.tianqi.vitality.handler.AuthExceptionEntryPoint;

import javax.annotation.Resource;

/**
 * turntable资源配置类
 *
 * @Author wkh
 * @Date 2020/8/18 10:58
 */
@Configuration
@EnableResourceServer
public class ServerTurntableResourceServerConfigure extends ResourceServerConfigurerAdapter {

    @Resource(name = "accessDeniedHandler")
    private VitalityAccessDeniedHandler accessDeniedHandler;
    @Resource(name = "authExceptionEntryPoint")
    private AuthExceptionEntryPoint authExceptionEntryPoint;

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .requestMatchers().antMatchers("/**")
                .and()
                .authorizeRequests()
                .antMatchers("/**").authenticated();
    }


    @Override
    public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
        resources.accessDeniedHandler(accessDeniedHandler)
                .authenticationEntryPoint(authExceptionEntryPoint);
    }
}

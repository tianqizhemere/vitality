package top.tianqi.vitality.auth.config;

import org.springframework.context.annotation.Bean;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import top.tianqi.vitality.auth.filter.ValidateCodeFilter;
import top.tianqi.vitality.auth.service.impl.AuthUserDetailServiceImpl;

import javax.annotation.Resource;

/**
 * 安全配置类
 * <p>用于处理和令牌相关的请求</p>
 *
 * @Author wkh
 * @Date 2020/8/17 14:58
 */
@Order(2)
@EnableWebSecurity
public class SecurityConfigure extends WebSecurityConfigurerAdapter {

    @Resource(name = "authUserDetailService")
    private AuthUserDetailServiceImpl userDetailService;

    @Resource(name = "validateCodeFilter")
    private ValidateCodeFilter validateCodeFilter;

    /**
     * 用于密码加密校验
     *
     * @return
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.addFilterBefore(validateCodeFilter, UsernamePasswordAuthenticationFilter.class)
                .requestMatchers()
                // 安全配置类只对/oauth/开头的请求有效。
                    .antMatchers("/oauth/**")
                .and()
                    .authorizeRequests()
                    .antMatchers("/oauth/**").authenticated()
                .and()
                    .csrf().disable();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailService).passwordEncoder(passwordEncoder());
    }

}

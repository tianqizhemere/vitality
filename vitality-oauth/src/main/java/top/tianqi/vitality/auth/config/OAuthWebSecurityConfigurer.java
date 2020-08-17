package top.tianqi.vitality.auth.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * Oauth 安全配置
 * @Author wkh
 * @Date 2020/8/10 11:52
 */
@Configuration
public class OAuthWebSecurityConfigurer extends WebSecurityConfigurerAdapter {

    /**
     * 实现 configure(HttpSecurity http)方法，
     * 该方法用于描述安全防护策略
     * @param http
     * @throws Exception
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http
                .authorizeRequests()
                //所有请求均需要验证通过后才能访问
                .anyRequest().authenticated()
                .and()
                .httpBasic()
                .and()
                .csrf().disable();
    }

    /**
     * 创建一个密码加密器 Bean，用于密码校验。
     * @return PasswordEncoder
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * 如果鉴权服务器需要提供 password 访问模式，
     * 则需要实现该方法
     * @return
     * @throws Exception
     */
    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
}

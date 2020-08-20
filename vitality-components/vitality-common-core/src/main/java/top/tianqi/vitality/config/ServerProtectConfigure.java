package top.tianqi.vitality.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import top.tianqi.vitality.interceptor.ServerProtectInterceptor;

import javax.annotation.Resource;

/**
 * @author tianQi
 * @create 2020-08-20
 */
public class ServerProtectConfigure implements WebMvcConfigurer {

    @Resource(name = "serverProtectInterceptor")
    private HandlerInterceptor interceptor;

    @Bean(name = "serverProtectInterceptor")
    public HandlerInterceptor serverProtectInterceptor() {
        return new ServerProtectInterceptor();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(interceptor);
    }

    @Bean
    @ConditionalOnMissingBean(value = PasswordEncoder.class)
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}

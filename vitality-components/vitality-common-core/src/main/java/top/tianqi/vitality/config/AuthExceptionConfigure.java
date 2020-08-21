package top.tianqi.vitality.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import top.tianqi.vitality.handler.AuthExceptionEntryPoint;
import top.tianqi.vitality.handler.VitalityAccessDeniedHandler;

/**
 * 通过配置类将服务器异常处理类注册到IOC容器中
 *
 * @Author wkh
 * @Date 2020/8/21 9:59
 */
public class AuthExceptionConfigure {

    @Bean
    @ConditionalOnMissingBean(name = "accessDeniedHandler")
    public VitalityAccessDeniedHandler accessDeniedHandler(){
        return new VitalityAccessDeniedHandler();
    }

    /**
     * 当微服务系统的Spring IOC容器中没有名称为accessDeniedHandler的Bean的时候，
     * 就将AuthExceptionEntryPoint注册为一个Bean。
     * @return
     */
    @Bean
    @ConditionalOnMissingBean(name = "authExceptionEntryPoint")
    public AuthExceptionEntryPoint authExceptionEntryPoint(){
        return new AuthExceptionEntryPoint();
    }
}

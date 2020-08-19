package top.tianqi.vitality.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;


/**
 * 配置跨域
 * @author wkh
 * @Date 2020/06/28
 */
@Configuration
public class GatewayCorsConfig {

    /**
     * 跨域支持
     */
    @Bean
    public CorsFilter corsFilter() {
        final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        final CorsConfiguration corsConfiguration = new CorsConfiguration();
        // 允许cookie跨域
        corsConfiguration.setAllowCredentials(true);
        // 请求头部允许携带任何内容
        corsConfiguration.addAllowedHeader(CorsConfiguration.ALL);
        // 允许任何来源
        corsConfiguration.addAllowedOrigin(CorsConfiguration.ALL);
        // 允许任何HTTP方法
        corsConfiguration.addAllowedMethod(CorsConfiguration.ALL);
        source.registerCorsConfiguration("/**", corsConfiguration);
        return new CorsFilter(source);
    }
}

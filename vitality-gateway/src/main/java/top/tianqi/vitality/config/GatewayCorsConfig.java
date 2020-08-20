package top.tianqi.vitality.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.reactive.CorsWebFilter;
import org.springframework.web.cors.reactive.UrlBasedCorsConfigurationSource;
import org.springframework.web.util.pattern.PathPatternParser;


/**
 * 配置跨域
 * @author wkh
 * @Date 2020/06/28
 */
@Configuration
public class GatewayCorsConfig {

    /**
     * 跨域设置
     * @return
     */
    @Bean
    public CorsWebFilter corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource(new PathPatternParser());
        CorsConfiguration cors = new CorsConfiguration();
        // 允许cookie跨域
        cors.setAllowCredentials(true);
        // 请求头部允许携带任何内容
        cors.addAllowedOrigin(CorsConfiguration.ALL);
        // 允许任何来源
        cors.addAllowedHeader(CorsConfiguration.ALL);
         // 允许任何HTTP方法
        cors.addAllowedMethod(CorsConfiguration.ALL);
        source.registerCorsConfiguration("/**", cors);
        return new CorsWebFilter(source);
    }
}

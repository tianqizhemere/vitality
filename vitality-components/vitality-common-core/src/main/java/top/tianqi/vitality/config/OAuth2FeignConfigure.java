package top.tianqi.vitality.config;

import feign.RequestInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpHeaders;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationDetails;
import top.tianqi.vitality.entity.VitalityConstant;
import top.tianqi.vitality.tools.utils.Base64Utils;

/**
 * 拦截Feign请求，手动往请求头上加入令牌
 *
 * @Author wkh
 * @Date 2020/8/18 13:51
 */
public class OAuth2FeignConfigure  {

    @Bean
    public RequestInterceptor oauth2FeignRequestInterceptor() {
        return requestTemplate -> {
            // 添加 Gateway Token
            String gatewayToken = new String(Base64Utils.encode(VitalityConstant.GATEWAY_TOKEN_VALUE.getBytes()));
            requestTemplate.header(VitalityConstant.GATEWAY_TOKEN_HEADER, gatewayToken);

            Object details = SecurityContextHolder.getContext().getAuthentication().getDetails();
            if (details instanceof OAuth2AuthenticationDetails) {
                String authorizationToken = ((OAuth2AuthenticationDetails) details).getTokenValue();
                requestTemplate.header(HttpHeaders.AUTHORIZATION, "bearer " + authorizationToken);
            }
        };
    }
}

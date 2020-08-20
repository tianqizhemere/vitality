package top.tianqi.vitality.properties;

import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;

/**
 * 定义禁止客户端访问资源
 *
 * @Author wkh
 * @Date 2020/8/20 11:29
 */
@SpringBootConfiguration
@PropertySource(value = "classpath:vitality-gateway.properties")
@ConfigurationProperties(value = "vitality.gateway")
public class VitalityGatewayProperties {

    /** 禁止外部访问的 URI，以逗号分隔*/
    private String forbidRequestUri;

    public String getForbidRequestUri() {
        return forbidRequestUri;
    }

    public void setForbidRequestUri(String forbidRequestUri) {
        this.forbidRequestUri = forbidRequestUri;
    }
}

package top.tianqi.vitality.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

/**
 * 自定义配置项
 * @Author wkh
 * @Date 2020/8/11 17:00
 */
@Configuration
public class GatewayConfig {

    public static String NACOS_DATA_ID;
    public static String NACOS_GROUP_ID;
    public static String NACOS_SERVER_ADDR;
    public static String NACOS_NAMESPACE;

    @Value("${nacos.config.data-id}")
    public void setNacosDataId(String dataId) {
        NACOS_DATA_ID = dataId;
    }

    @Value("${nacos.config.group}")
    public void setNacosGroupId(String group) {
        NACOS_GROUP_ID = group;
    }

    @Value("${nacos.config.server-addr}")
    public void setNacosServerAddr(String nacosServerAddr) {
        NACOS_SERVER_ADDR = nacosServerAddr;
    }

    @Value("${nacos.config.namespace}")
    public void setNacosNamespace(String nacosNamespace) {
        NACOS_NAMESPACE = nacosNamespace;
    }
}

package top.tianqi.vitality.auth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

/**
 * EnableAuthorizationServer 配置授权服务
 * @Author wkh
 * @Date 2020/8/10 13:43
 */
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
public class OauthApplication {
    public static void main(String[] args) {
        SpringApplication.run(OauthApplication.class, args);
    }
}

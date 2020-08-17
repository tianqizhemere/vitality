package top.tianqi.vitality.auth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;

/**
 * EnableAuthorizationServer 配置授权服务
 * @Author wkh
 * @Date 2020/8/10 13:43
 */
@SpringBootApplication
@EnableAuthorizationServer
public class OauthApplication {
    public static void main(String[] args) {
        SpringApplication.run(OauthApplication.class, args);
    }
}

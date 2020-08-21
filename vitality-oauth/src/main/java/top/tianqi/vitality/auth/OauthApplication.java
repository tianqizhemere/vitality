package top.tianqi.vitality.auth;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import top.tianqi.vitality.annotation.EnableCloudApplication;
import top.tianqi.vitality.annotation.EnableLettuceRedis;

/**
 * 授权服务
 *
 * @Author wkh
 * @Date 2020/8/10 13:43
 */
@MapperScan(value = "top.tianqi.vitality.auth.mapper")
@EnableLettuceRedis
@EnableDiscoveryClient
@EnableCloudApplication
@SpringBootApplication
public class OauthApplication {
    public static void main(String[] args) {
        SpringApplication.run(OauthApplication.class, args);
    }
}

package top.tianqi.vitality;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.util.AntPathMatcher;

/**
 * @Author wkh
 * @Date 2020/6/30 16:06
 */
@EnableDiscoveryClient
@SpringBootApplication
public class FamilyGatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(FamilyGatewayApplication.class, args);
    }

    /**
     * URLs字符串匹配
     * @return AntPathMatcher
     */
    @Bean
    public AntPathMatcher antPathMatcher() {
        return new AntPathMatcher();
    }
}

package top.tianqi.vitality;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @Author wkh
 * @Date 2020/6/30 16:06
 */
@SpringBootApplication
@EnableDiscoveryClient
public class FamilyGatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(FamilyGatewayApplication.class, args);
    }
}

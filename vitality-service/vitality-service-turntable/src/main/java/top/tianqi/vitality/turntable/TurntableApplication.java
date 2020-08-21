package top.tianqi.vitality.turntable;

import com.ulisesbocchio.jasyptspringboot.annotation.EnableEncryptableProperties;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import top.tianqi.vitality.annotation.EnableCloudApplication;

@EnableEncryptableProperties
@MapperScan(basePackages = {"top.tianqi.vitality.turntable.mapper"})
@EnableDiscoveryClient
@EnableFeignClients
@EnableCloudApplication
@SpringBootApplication
public class TurntableApplication {

    public static void main(String[] args) {
        SpringApplication.run(TurntableApplication.class, args);
    }

}

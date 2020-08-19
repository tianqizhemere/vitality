package top.tianqi.vitality.queue;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @Author wkh
 * @Date 2020/8/6 17:05
 */
@MapperScan(basePackages = "top.tianqi.vitality.queue.dao")
@SpringBootApplication(scanBasePackages = {"top.tianqi.vitality.tools.queue"})
public class QueueApplication {

    public static void main(String[] args) {
        SpringApplication.run(QueueApplication.class, args);
    }
}

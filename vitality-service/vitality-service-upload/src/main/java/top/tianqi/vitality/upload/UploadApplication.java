package top.tianqi.vitality.upload;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.util.unit.DataSize;
import top.tianqi.vitality.tools.upload.RemoteUploadConfig;

import javax.servlet.MultipartConfigElement;

/**
 * @Author wkh
 * @Date 2020/7/30 15:12
 */
@EnableConfigurationProperties(RemoteUploadConfig.class)
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class UploadApplication {
    public static void main(String[] args) {
        SpringApplication.run(UploadApplication.class, args);
    }


    /**
      * 配置上传文件大小的配置
      * @return
      */
    @Bean
    public MultipartConfigElement multipartConfigElement() {
        MultipartConfigFactory factory = new MultipartConfigFactory();
        //  单个数据大小
        factory.setMaxFileSize(DataSize.ofBytes(1024 * 1024 * 5));
        /// 总上传数据大小
        factory.setMaxRequestSize(DataSize.ofBytes(1024 * 1024 * 50));
        return factory.createMultipartConfig();
    }
}

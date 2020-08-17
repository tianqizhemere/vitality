package top.tianqi.vitality.tools.upload;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * 文件上传路径配置
 * @Author wkh
 * @Date 2020/7/30 15:02
 */
@Configuration
@Component
@PropertySource(value =  {"classpath:application-attach.properties"})
@ConfigurationProperties(prefix = "remote")
public class RemoteUploadConfig {

    /** 上传路径 */
    private String uploadPath;
    /** 文件上传路径 */
    private String uploadPathFile;
    /** 图片上传路径 */
    private String uploadPathImage;

    public String getUploadPathFile() {
        return uploadPathFile;
    }

    public void setUploadPathFile(String uploadPathFile) {
        this.uploadPathFile = uploadPathFile;
    }

    public String getUploadPathImage() {
        return uploadPathImage;
    }

    public void setUploadPathImage(String uploadPathImage) {
        this.uploadPathImage = uploadPathImage;
    }

    public String getUploadPath() {
        return uploadPath;
    }

    public void setUploadPath(String uploadPath) {
        this.uploadPath = uploadPath;
    }
}

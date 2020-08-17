package top.tianqi.vitality.upload.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * 文件上传服务层接口
 * @Author wkh
 * @Date 2020/7/30 15:48
 */
public interface UploadService {
    /**
     * 上传文件
     * @param multipartFile
     */
    String uploadFile(MultipartFile multipartFile) throws IOException;
}

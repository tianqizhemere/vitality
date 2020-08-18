package top.tianqi.vitality.upload.fegin;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import top.tianqi.vitality.entity.Result;
import top.tianqi.vitality.entity.VitalityServerConstant;
import top.tianqi.vitality.upload.fegin.impl.UploadFeignClientImpl;

import javax.servlet.http.HttpServletRequest;

/**
 * 文件上传微服务feign
 *
 * @Author wkh
 * @Date 2020/8/18 14:03
 */
@FeignClient(value = VitalityServerConstant.VITALITY_SERVER_UPLOAD, fallback = UploadFeignClientImpl.class)
public interface UploadFeignClient {

    /** 文件下载 */
    @PostMapping(value = "/uploadFile")
    Result uploadFile(HttpServletRequest request, @RequestParam(value = "file") MultipartFile multipartFile);
}

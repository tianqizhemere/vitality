package top.tianqi.vitality.upload.controller;


import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import top.tianqi.vitality.tools.utils.Result;
import top.tianqi.vitality.tools.utils.ResultStatusCode;
import top.tianqi.vitality.tools.utils.StatusCode;
import top.tianqi.vitality.upload.service.UploadService;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * 文件上传controller
 * @Author wkh
 * @Date 2020/7/30 15:11
 */
@RestController(value = "uploadController")
@RequestMapping(value = "/upload")
public class UploadController {

    @Resource(name = "uploadServiceImpl")
    private UploadService uploadService;

    @PostMapping(value = "/uploadFile")
    public Result uploadFile(HttpServletRequest request, @RequestParam(value = "file") MultipartFile multipartFile) throws IOException {
        if (multipartFile.isEmpty()) {
            return new Result(true, ResultStatusCode.DataNotExistException.getCode(), ResultStatusCode.DataNotExistException.getMsg());
        }
        if (multipartFile.getSize() > 1024 * 1024 * 5) {
            return new Result(true, ResultStatusCode.FILE_TOO_LARGE.getCode(), ResultStatusCode.FILE_TOO_LARGE.getMsg());
        }
        String json = uploadService.uploadFile(multipartFile);
        return new Result(true, ResultStatusCode.Success.getCode(), ResultStatusCode.Success.getMsg(), json);
    }


    @PostMapping(value = "/uploadImage")
    public Result uploadImage(){

        return new Result(true, StatusCode.OK, ResultStatusCode.Success.getMsg());
    }


}

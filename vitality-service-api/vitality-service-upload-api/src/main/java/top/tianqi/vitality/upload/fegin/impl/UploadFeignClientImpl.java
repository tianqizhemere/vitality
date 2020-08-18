package top.tianqi.vitality.upload.fegin.impl;

import feign.hystrix.FallbackFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import top.tianqi.vitality.entity.Result;
import top.tianqi.vitality.tools.utils.ResultStatusCode;
import top.tianqi.vitality.upload.fegin.UploadFeignClient;

/**
 * @Author wkh
 * @Date 2020/8/18 14:19
 */
@Component
public class UploadFeignClientImpl implements FallbackFactory<UploadFeignClient> {

    private static Logger logger = LoggerFactory.getLogger(UploadFeignClientImpl.class);

    @Override
    public UploadFeignClient create(Throwable throwable) {
        return (request, multipartFile) -> {
            logger.error("调用vitality-server-upload服务出错", throwable);
            return new Result<>(false, ResultStatusCode.RemoteCall.getCode(), ResultStatusCode.RemoteCall.getMsg());
        };
    }
}

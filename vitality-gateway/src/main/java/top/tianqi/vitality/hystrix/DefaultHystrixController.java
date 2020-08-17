package top.tianqi.vitality.hystrix;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.tianqi.vitality.tools.utils.LogUtil;
import top.tianqi.vitality.entity.Result;
import top.tianqi.vitality.tools.utils.ResultStatusCode;

import java.util.Date;

/**
 * 熔断器controller
 * @author wkh
 * @Date 2020/7/1
 */
@RestController
public class DefaultHystrixController {

    /**
     * 触发熔断器
     * @return 响应请求体
     */
    @RequestMapping("/fallback")
    public Result<String> fallback(){
        LogUtil.error(new Date() + "，触发熔断");
        return new Result<>(false, ResultStatusCode.BAD_REQUEST.getCode(), ResultStatusCode.BAD_REQUEST.getMsg());
    }
}
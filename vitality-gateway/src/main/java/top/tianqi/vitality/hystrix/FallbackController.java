package top.tianqi.vitality.hystrix;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import top.tianqi.vitality.entity.Result;

/**
 * 熔断器controller
 *
 * @author wkh
 * @Date 2020/7/1
 */
@RestController
public class FallbackController {

    /**
     * 触发熔断器
     * @return 响应请求体
     */
    @RequestMapping("/fallback/{name}")
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public Result<String> fallback(@PathVariable(name = "name") String name){
        String response = String.format("访问%s超时或者服务不可用", name);
        return new Result<>(false, HttpStatus.INTERNAL_SERVER_ERROR.value(), response);
    }
}
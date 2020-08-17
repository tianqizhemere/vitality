package top.tianqi.vitality.handler;

import org.springframework.boot.autoconfigure.web.ErrorProperties;
import org.springframework.boot.autoconfigure.web.ResourceProperties;
import org.springframework.boot.autoconfigure.web.reactive.error.DefaultErrorWebExceptionHandler;
import org.springframework.boot.web.reactive.error.ErrorAttributes;
import org.springframework.cloud.gateway.support.NotFoundException;
import org.springframework.context.ApplicationContext;
import org.springframework.http.HttpStatus;
import org.springframework.web.reactive.function.server.*;
import org.springframework.web.server.ResponseStatusException;
import top.tianqi.vitality.tools.exception.UnauthorizedException;

import java.util.HashMap;
import java.util.Map;

/**
 * 统一异常处理
 * <p>通过跟踪异常信息的抛出，找到对应的源码，自定义一些处理逻辑来匹配业务的需求</p>
 * <p>gateway使用WebFlux形式作为底层框架，而不是Servlet容器，所以常规的异常处理无法使用
 翻阅源码</p>
 *
 * @Author wkh
 * @Date 2020/8/12 16:56
 */
public class JsonExceptionHandler extends DefaultErrorWebExceptionHandler {

    /** 状态码Key */
    private static final String STATUS_CODE_KEY = "code";


    public JsonExceptionHandler(ErrorAttributes errorAttributes,
                                ResourceProperties resourceProperties,
                                ErrorProperties errorProperties,
                                ApplicationContext applicationContext) {
        super(errorAttributes, resourceProperties, errorProperties, applicationContext);
    }

    @Override
    protected Map<String, Object> getErrorAttributes(ServerRequest request, boolean includeStackTrace) {
        int code = HttpStatus.UNAUTHORIZED.value();
        // 这里其实可以根据异常类型进行定制化逻辑
        Throwable error = super.getError(request);
        String body;
        if (error instanceof NotFoundException) {
            code = HttpStatus.NOT_FOUND.value();
            body = "Service Not Found";
        } else if (error instanceof ResponseStatusException || error instanceof UnauthorizedException) {
            ResponseStatusException responseStatusException = (ResponseStatusException) error;
            code = responseStatusException.getStatus().value();
            body = responseStatusException.getMessage();
        } else {
            code = HttpStatus.INTERNAL_SERVER_ERROR.value();
            body = "Internal Server Error";
        }
        Map<String, Object> errorAttributes = new HashMap<>(8);
        errorAttributes.put("message", body);
        errorAttributes.put(STATUS_CODE_KEY, code);
        errorAttributes.put("method", request.methodName());
        errorAttributes.put("path", request.path());
        return errorAttributes;
    }

    /**
     * 指定响应处理方法为JSON处理的方法
     *
     * @param errorAttributes
     * @return
     */
    @Override
    protected RouterFunction<ServerResponse> getRoutingFunction(ErrorAttributes errorAttributes) {
        return RouterFunctions.route(RequestPredicates.all(), this::renderErrorResponse);
    }

    /**
     * HTTP响应状态码的封装
     * 根据STATUS_CODE_KEY获取对应的HttpStatus
     * */
    @Override
    protected HttpStatus getHttpStatus(Map<String, Object> errorAttributes) {
        int statusCode = (int)errorAttributes.get(STATUS_CODE_KEY);
        return HttpStatus.valueOf(statusCode);
    }
}

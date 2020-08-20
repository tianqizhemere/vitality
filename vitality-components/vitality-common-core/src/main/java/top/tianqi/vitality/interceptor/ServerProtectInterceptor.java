package top.tianqi.vitality.interceptor;

import org.apache.commons.lang3.StringUtils;
import org.springframework.http.MediaType;
import org.springframework.web.servlet.HandlerInterceptor;
import top.tianqi.vitality.entity.Result;
import top.tianqi.vitality.entity.VitalityConstant;
import top.tianqi.vitality.tools.utils.Base64Utils;
import top.tianqi.vitality.tools.utils.JsonUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author tianQi
 * @create 2020-08-20
 */
public class ServerProtectInterceptor implements HandlerInterceptor {

    /**
     * 实现了HandlerInterceptor的preHandle方法，该拦截器可以拦截所有Web请求
     * @param request
     * @param response
     * @param handler
     * @return
     * @throws IOException
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws IOException {
        // 从请求头中获取 Gateway Token
        String token = request.getHeader(VitalityConstant.GATEWAY_TOKEN_HEADER);
        String gatewayToken = new String(Base64Utils.encode(VitalityConstant.GATEWAY_TOKEN_VALUE.getBytes()));
        // 校验 Gateway Token的正确性
        if (StringUtils.equals(gatewayToken, token)) {
            return true;
        } else {
            Result<String> result = new Result<>(HttpServletResponse.SC_FORBIDDEN, "请通过网关获取资源");
            response.setContentType(MediaType.APPLICATION_JSON_VALUE);
            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
            response.getWriter().write(JsonUtil.toJsonString(result));
            return false;
        }
    }
}

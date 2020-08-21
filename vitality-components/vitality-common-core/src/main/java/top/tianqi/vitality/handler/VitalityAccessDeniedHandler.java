package top.tianqi.vitality.handler;

import org.springframework.http.MediaType;
import org.springframework.security.access.AccessDeniedException;
import top.tianqi.vitality.tools.utils.JsonUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 处理资源服务器异常
 * <p>处理用户无权限访问的403异常</p>
 *
 * @Author wkh
 * @Date 2020/8/21 9:55
 */
public class VitalityAccessDeniedHandler implements org.springframework.security.web.access.AccessDeniedHandler {

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException e) throws IOException, ServletException {
        response.setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);
        response.setStatus(HttpServletResponse.SC_FORBIDDEN);
        response.getOutputStream().write(JsonUtil.toJsonString("没有权限访问该资源").getBytes());
    }
}

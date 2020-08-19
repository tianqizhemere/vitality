package top.tianqi.vitality.auth.filter;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpMethod;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import top.tianqi.vitality.auth.service.ValidateCodeService;
import top.tianqi.vitality.entity.Result;
import top.tianqi.vitality.exception.ValidateCodeException;
import top.tianqi.vitality.tools.utils.JsonUtil;

import javax.annotation.Resource;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 验证码过滤器
 * <p>用于拦截请求并校验验证码的正确性</p>
 *
 * @Author wkh
 * @Date 2020/8/19 16:44
 */
@Component
public class ValidateCodeFilter extends OncePerRequestFilter {

    private static Logger log = LoggerFactory.getLogger(ValidateCodeFilter.class);

    @Resource(name = "validateCodeServiceImpl")
    private ValidateCodeService validateCodeService;

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {
        // 拦截请求路径为/oauth/token且为post请求
        RequestMatcher matcher = new AntPathRequestMatcher("/oauth/token", HttpMethod.POST.toString());
        if (matcher.matches(httpServletRequest)
                && StringUtils.equalsIgnoreCase(httpServletRequest.getParameter("grant_type"), "password")) {
            try {
                validateCode(httpServletRequest);
                filterChain.doFilter(httpServletRequest, httpServletResponse);
            } catch (ValidateCodeException e) {
                Result<String> result = new Result<>(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, e.getMessage());
                httpServletResponse.getOutputStream().write(JsonUtil.toJsonString(result).getBytes());
                log.error(e.getMessage(), e);
            }
        } else {
            filterChain.doFilter(httpServletRequest, httpServletResponse);
        }
    }

    /**
     * 校验验证码
     *
     * @param httpServletRequest HttpServletRequest
     * @throws ValidateCodeException
     */
    private void validateCode(HttpServletRequest httpServletRequest) throws ValidateCodeException {
        String code = httpServletRequest.getParameter("code");
        String key = httpServletRequest.getParameter("key");
        validateCodeService.checkCode(key, code);
    }
}

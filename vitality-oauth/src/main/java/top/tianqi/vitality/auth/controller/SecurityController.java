package top.tianqi.vitality.auth.controller;

import org.apache.commons.lang3.StringUtils;
import org.springframework.security.oauth2.provider.token.ConsumerTokenServices;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.tianqi.vitality.auth.service.ValidateCodeService;
import top.tianqi.vitality.entity.Result;
import top.tianqi.vitality.exception.AuthException;
import top.tianqi.vitality.exception.ValidateCodeException;
import top.tianqi.vitality.tools.utils.ResultStatusCode;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.Principal;

/**
 * 权限controller
 *
 * @Author wkh
 * @Date 2020/8/17 15:48
 */
@RestController(value = "authController")
@RequestMapping(value = "/auth")
public class SecurityController {

    @Resource(name = "consumerTokenServices")
    private ConsumerTokenServices consumerTokenServices;
    @Resource(name = "validateCodeServiceImpl")
    private ValidateCodeService validateCodeService;

    @GetMapping("/test")
    public String testOauth() {
        return "oauth";
    }

    /**
     * 获取当前登录用户
     *
     * @param principal
     * @return
     */
    @GetMapping(value = "/user")
    public Principal currentUser(Principal principal) {
        return principal;
    }

    /**
     * 注销当前token
     *
     * @param request
     * @return
     */
    @DeleteMapping(value = "/signOut")
    public Result signOut(HttpServletRequest request) throws AuthException {
        String authorization = request.getHeader("Authorization");
        String token = StringUtils.replace(authorization, "bearer ", "");
        if (!consumerTokenServices.revokeToken(token)) {
            throw new AuthException("退出登录失败");
        }
        return new Result<>(ResultStatusCode.Success.getCode(), ResultStatusCode.Success.getMsg());
    }

    /**
     * 生成验证码
     *
     * @param request HttpServletRequest
     * @param response HttpServletResponse
     * @return
     * @throws ValidateCodeException
     * @throws IOException
     */
    @GetMapping(value = "/captcha")
    public Result createCaptcha(HttpServletRequest request, HttpServletResponse response) throws ValidateCodeException, IOException {
        validateCodeService.create(request, response);
        return new Result();
    }

}

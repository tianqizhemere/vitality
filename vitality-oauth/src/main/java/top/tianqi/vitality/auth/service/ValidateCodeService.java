package top.tianqi.vitality.auth.service;

import top.tianqi.vitality.exception.ValidateCodeException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 验证码服务层接口
 *
 * @Author wkh
 * @Date 2020/8/19 16:03
 */
public interface ValidateCodeService {

    /**
     * 生成验证码
     *
     * @param request HttpServletRequest
     * @param response HttpServletResponse
     */
    void create(HttpServletRequest request, HttpServletResponse response) throws ValidateCodeException, IOException;

    /**
     * 校验验证码
     *
     * @param key 前端携带key值
     * @param value 待校验值
     */
    void checkCode(String key, String value) throws ValidateCodeException;
}

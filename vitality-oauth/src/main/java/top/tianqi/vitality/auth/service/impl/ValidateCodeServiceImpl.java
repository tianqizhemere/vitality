package top.tianqi.vitality.auth.service.impl;

import com.wf.captcha.GifCaptcha;
import com.wf.captcha.SpecCaptcha;
import com.wf.captcha.base.Captcha;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import top.tianqi.vitality.auth.properties.AuthProperties;
import top.tianqi.vitality.auth.properties.ValidateCodeProperties;
import top.tianqi.vitality.auth.service.ValidateCodeService;
import top.tianqi.vitality.entity.VitalityConstant;
import top.tianqi.vitality.exception.ValidateCodeException;
import top.tianqi.vitality.service.RedisService;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 验证码服务层实现
 *
 * @Author wkh
 * @Date 2020/8/19 16:04
 */
@Service(value = "validateCodeServiceImpl")
public class ValidateCodeServiceImpl implements ValidateCodeService {

    @Resource(name = "redisService")
    private RedisService redisService;
    @Resource(name = "authProperties")
    private AuthProperties authProperties;

    @Override
    public void create(HttpServletRequest request, HttpServletResponse response) throws ValidateCodeException, IOException {
        // 获取验证码
        String key = request.getParameter("key");
        if (StringUtils.isBlank(key)){
            throw new ValidateCodeException("验证码key不能为空");
        }
        ValidateCodeProperties code = authProperties.getCode();
        setHeader(response, code.getType());
        Captcha captcha = createCaptcha(code);
        // 验证码字符保存到了Redis中，有效时间为配置文件定义的120秒，并将验证码图片以流的形式返回给客户端
        redisService.set(VitalityConstant.CODE_PREFIX + key, StringUtils.lowerCase(captcha.text()), code.getTime());
        captcha.out(response.getOutputStream());
    }

    @Override
    public void checkCode(String key, String value) throws ValidateCodeException {
        Object codeValue = redisService.get(VitalityConstant.CODE_PREFIX + key);
        if (StringUtils.isBlank(value)) {
            throw new ValidateCodeException("验证码为空");
        }
        if (codeValue == null) {
            throw new ValidateCodeException("验证码已过期");
        }
        if (!StringUtils.equalsAnyIgnoreCase(value, String.valueOf(codeValue))){
            throw new ValidateCodeException("验证码不正确");
        }
    }

    /**
     * 通过验证码配置文件ValidateCodeProperties生成相应的验证码
     *
     * @param code
     * @return
     */
    private Captcha createCaptcha(ValidateCodeProperties code) {
        Captcha captcha = null;
        if (StringUtils.equalsIgnoreCase(code.getType(), VitalityConstant.GIF)) {
            captcha = new GifCaptcha(code.getWidth(), code.getHeight(), code.getLength());
        } else {
            captcha = new SpecCaptcha(code.getWidth(), code.getHeight(), code.getLength());
        }
        captcha.setCharType(code.getCharType());
        return captcha;
    }

    /**
     * 设置响应头
     * 生成验证码图片后将其返回到客户端，根据不同的验证码格式设置不同的响应头；
     *
     * @param response
     * @param type
     */
    private void setHeader(HttpServletResponse response, String type) {
        if (StringUtils.equalsIgnoreCase(type, VitalityConstant.GIF)) {
            response.setContentType(MediaType.IMAGE_GIF_VALUE);
        } else {
            response.setContentType(MediaType.IMAGE_PNG_VALUE);
        }
        response.setHeader(HttpHeaders.PRAGMA, "No-cache");
        response.setHeader(HttpHeaders.CACHE_CONTROL, "no-cache");
        response.setDateHeader(HttpHeaders.EXPIRES, 0L);
    }
}

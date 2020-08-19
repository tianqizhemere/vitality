package top.tianqi.vitality.auth.translator;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.common.exceptions.InvalidGrantException;
import org.springframework.security.oauth2.common.exceptions.UnsupportedGrantTypeException;
import org.springframework.security.oauth2.provider.error.WebResponseExceptionTranslator;
import org.springframework.stereotype.Component;
import top.tianqi.vitality.entity.Result;

/**
 * 授权服务异常处理器
 * <p>覆盖默认的认证异常响应</p>
 *
 * @Author wkh
 * @Date 2020/8/18 11:39
 */
@Component
public class AuthWebResponseExceptionTranslator implements WebResponseExceptionTranslator  {

    private static Logger logger = LoggerFactory.getLogger(AuthWebResponseExceptionTranslator.class);

    @Override
    public ResponseEntity translate(Exception e) throws Exception {
        ResponseEntity.BodyBuilder status = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR);
        String message = "认证失败";
        Result<String> result = new Result<>(HttpStatus.INTERNAL_SERVER_ERROR.value(), message);
        logger.error(message, e);
        if (e instanceof UnsupportedGrantTypeException) {
            message = "不支持的认证类型";
            result.setMessage(message);
            return status.body(result);
        }
        if (e instanceof InvalidGrantException) {
            if (StringUtils.equalsAnyIgnoreCase(e.getMessage(), "Invalid refresh token")) {
                message = "refresh token无效";
                result.setMessage(message);
                return status.body(result);
            }
            if (StringUtils.equalsAnyIgnoreCase(e.getMessage(), "User account is locked")) {
                message = "账户已锁定，请联系管理员！";
                result.setMessage(message);
                return status.body(result);
            }
            message = "用户名或密码错误";
            result.setMessage(message);
            return status.body(result);
        }
        return status.body(result);
    }
}

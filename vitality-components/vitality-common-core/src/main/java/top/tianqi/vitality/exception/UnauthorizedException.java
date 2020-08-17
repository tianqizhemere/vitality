package top.tianqi.vitality.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

/**
 * 未授权异常
 *
 * @Author wkh
 * @Date 2020/8/12 17:05
 */
public class UnauthorizedException extends ResponseStatusException {

    public UnauthorizedException() {
        super(HttpStatus.UNAUTHORIZED);
    }

    public UnauthorizedException(String message) {
        super(HttpStatus.UNAUTHORIZED,message);
    }

}

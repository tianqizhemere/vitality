package top.tianqi.vitality.exception;

/**
 * 权限异常
 * <p>自定义异常</p>
 *
 * @Author wkh
 * @Date 2020/8/17 16:02
 */
public class AuthException extends Exception {

    private static final long serialVersionUID = -2857897893328038149L;

    public AuthException(String message) {
        super(message);
    }
}

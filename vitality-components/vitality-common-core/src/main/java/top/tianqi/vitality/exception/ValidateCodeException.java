package top.tianqi.vitality.exception;

/**
 * 验证码异常
 * <p>自定义异常</p>
 *
 * @Author wkh
 * @Date 2020/8/19 15:58
 */
public class ValidateCodeException extends Exception {

    private static final long serialVersionUID = -8192584788242932191L;

    public ValidateCodeException(String msg) {
        super(msg);
    }
}

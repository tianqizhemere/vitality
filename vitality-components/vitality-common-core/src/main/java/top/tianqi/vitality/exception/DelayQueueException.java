package top.tianqi.vitality.exception;

/**
 * 延时队列异常
 * <p>自定义异常类</p>
 *
 * @Author wkh
 * @Date 2020/8/6 17:28
 */
public class DelayQueueException extends RuntimeException {

    public DelayQueueException(String message) {
        super(message);
    }
}

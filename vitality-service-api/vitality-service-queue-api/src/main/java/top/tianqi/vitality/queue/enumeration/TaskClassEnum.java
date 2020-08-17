package top.tianqi.vitality.queue.enumeration;

/**
 * 任务类字节码标识枚举类
 * @Author wkh
 * @Date 2020/8/7 10:53
 */
public enum TaskClassEnum {
    /** 订单任务类 */
    ORDER_TASK(1);


    /** 编码*/
    private int code;

    public int getCode() {
        return code;
    }

    TaskClassEnum(int code) {
        this.code = code;
    }
}

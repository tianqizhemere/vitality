package top.tianqi.vitality.queue.enumeration;

/**
 * 执行状态枚举类
 * @Author wkh
 * @Date 2020/8/6 17:31
 */
public enum RunTypeEnum {
    /** 已执行*/
    EXECUTED(1),
    /** 未执行*/
    NOT_EXECUTED(0);
    private int code;

    public int getCode() {
        return code;
    }

    RunTypeEnum(int code) {}
}

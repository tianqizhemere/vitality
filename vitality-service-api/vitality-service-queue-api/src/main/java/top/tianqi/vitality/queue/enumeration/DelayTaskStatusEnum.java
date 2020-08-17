package top.tianqi.vitality.queue.enumeration;

/**
 * 延时队列状态
 * @Author wkh
 * @Date 2020/8/7 9:26
 */
public enum DelayTaskStatusEnum {
    INIT(1, "初始化"),
    LOAD(2, "任务已加载"),
    SENDING(3, "消息已发放"),
    SUCCESS(4, "业务处理成功"),
    FAIL(5, "业务处理失败"),
    CANCEL(6, "业务取消");

    /** 编码*/
    private int code;
    /** 类型名称*/
    private String typeName;

    public int getCode() {
        return code;
    }

    public String getTypeName() {
        return typeName;
    }

    DelayTaskStatusEnum(int code, String typeName) {
        this.code = code;
        this.typeName = typeName;
    }
}

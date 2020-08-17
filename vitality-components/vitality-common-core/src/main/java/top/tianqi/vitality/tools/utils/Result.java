package top.tianqi.vitality.tools.utils;

import java.io.Serializable;

/**
 * 响应结果实体类
 * @Author Wukh
 * @Date 2020/2/22
 */
public class Result<T> implements Serializable {

    private static final long serialVersionUID = 4671489153320147084L;

    /** 是否成功 */
    private boolean flag;

    /** 返回码 */
    private Integer code;

    /** 返回消息 */
    private String message;

    /** 返回数据 */
    private T data;

    public Result(Integer code, String message) {
        this(true, code, message);
    }

    public Result(Integer code, String message, T data) {
        this(true, code, message, data);
    }

    public Result(boolean flag, Integer code, String message, T data) {
        this.flag = flag;
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public Result(boolean flag, Integer code, String message) {
        this.flag = flag;
        this.code = code;
        this.message = message;
    }

    public Result() {
        this.flag = true;
        this.code = StatusCode.OK;
        this.message = "操作成功!";
    }

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}

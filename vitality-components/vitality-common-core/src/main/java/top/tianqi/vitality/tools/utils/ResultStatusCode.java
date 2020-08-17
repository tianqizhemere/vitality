package top.tianqi.vitality.tools.utils;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * 响应码枚举
 * @Author wkh
 * @Date 2020/6/28 18:00
 */
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum ResultStatusCode {
    Success(200, "success"),
    UserNotExist(1, "用户名不存在"),
    InValidParameter(2, "Invalid parameter"),
    DataFormatException(4, "数据格式不正确"),
    DataNotExistException(5, "数据不存在"),
    TimeFormatException(6, "时间格式不正确"),
    PictureFormatException(7, "PictureFormat Exception"),
    IllegalArgumentException(8, "不合法的参数"),
    TokenInvalidOrOverdueException(9, "Token invalid or overdue exception"),
    AuthorizationCodeError(10, "authorization code error"),
    WrongSignatureException(11, "Wrong Signature Exception"),
    SystemException(500, "系统错误"),
    BAD_REQUEST(400, "错误请求"),
    FILE_TOO_LARGE(500, "文件过大"),
    MissingServletRequestParameter(400, "请求参数不全"),
    TypeMismatchException(401, "请求参数类型不正确"),
    RequestMethodNotAllowed(405, "http请求的方法不正确"),
    ;

    /** 状态码 */
    private Integer code;

    /** 提示信息 */
    private String msg;

    ResultStatusCode(Integer code,String msg) {
        this.code = code;
        this.msg = msg;
    }

    public String getMsg(){
        return this.msg;
    }
    public Integer getCode(){
        return this.code;
    }
}


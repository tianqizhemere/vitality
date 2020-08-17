package top.tianqi.vitality.tools.exception;

import java.io.Serializable;

/**
 * <p>导入Excel异常</p>
 * 自定义异常类
 * @Author wkh
 * @Date 2020/7/30 14:37
 */
public class InputExcelException extends Exception implements Serializable {

    private static final long serialVersionUID = -4920988739438735540L;

    private String message;

    public InputExcelException(){
        super();
    }

    public InputExcelException(String message) {
        super(message);
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}

package top.tianqi.vitality.tools.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;

/**
 * 返回异常的处理
 * @author wukh
 * @Date 2020/6/28 17:11
 */
public class ExceptionUtil {

    private static final Logger logger = LoggerFactory.getLogger(ExceptionUtil.class);

    private ExceptionUtil() {
    }

    /**
     * 获取异常信息
     *
     * @param e 异常
     */
    public static String getErrorMessage(Exception e) {
        StringWriter sw = null;
        PrintWriter pw = null;
        try {
            sw = new StringWriter();
            pw = new PrintWriter(sw);
            // 将出错的栈信息输出到printWriter中
            e.printStackTrace(pw);
            pw.flush();
            sw.flush();
        } finally {
            if (sw != null) {
                try {
                    sw.close();
                } catch (IOException e1) {
                    logger.info(e1.getMessage());
                }
            }
            if (pw != null) {
                pw.close();
            }
        }
        return sw.toString();
    }
    /**
     * 异常信息-->json
     */
    public static String resultOf(ResultStatusCode resultStatusCode) {
        return JsonUtil.toJsonString(resultStatusCode);
    }
}

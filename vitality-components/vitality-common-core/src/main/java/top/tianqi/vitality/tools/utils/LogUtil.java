package top.tianqi.vitality.tools.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @Author wkh
 * @Date 2020/6/30 18:05
 */
public class LogUtil {
    /**
     * 获取最原始被调用的堆栈信息
     * @return StackTraceElement
     */
    private static StackTraceElement findCaller() {
        // 获取堆栈信息
        StackTraceElement[] callStack = Thread.currentThread().getStackTrace();

        // 最原始被调用的堆栈信息
        StackTraceElement caller = null;
        // 日志类名称
        String logClassName = LogUtil.class.getName();
        // 循环遍历到日志类标识
        boolean isEachLogClass = false;

        // 遍历堆栈信息，获取出最原始被调用的方法信息
        for (StackTraceElement s : callStack) {
            // 遍历到日志类
            if (logClassName.equals(s.getClassName())) {
                isEachLogClass = true;
            }
            // 下一个非logClassName的堆栈，就是最原始被调用的方法
            if (isEachLogClass) {
                if (!logClassName.equals(s.getClassName())) {
                    caller = s;
                    break;
                }
            }
        }

        return caller;
    }

    /**
     * 自动匹配请求类名，生成logger对象，此处 logger name 值为 className
     * @return Logger
     */
    private static Logger logger() {
        // 最原始被调用的堆栈对象
        StackTraceElement caller = findCaller();
        return null == caller ? LoggerFactory.getLogger(LogUtil.class) : LoggerFactory.getLogger(caller.getClassName());
    }

    public static void debug(String msg){
        logger().debug(msg);
    }

    public static void debug(String msg, Object... o){
        logger().debug(msg, o);
    }


    public static void info(String msg){
        logger().info(msg);
    }


    public static void info(String msg, Object... o) {
        logger().info(msg, o);
    }


    public static void error(String msg, Object... o) {
        logger().error(msg, o);
    }
}

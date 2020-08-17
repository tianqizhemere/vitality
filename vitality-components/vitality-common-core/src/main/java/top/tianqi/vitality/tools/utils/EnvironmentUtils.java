package top.tianqi.vitality.tools.utils;

/**
 * 环境工具类
 * @author wkh
 * @Date 2020/7/1
 */
public class EnvironmentUtils {

    private static String applicationName;

    private static String env;

    public static String getApplicationName() {
        return applicationName;
    }

    public static void setApplicationName(String applicationName) {
        EnvironmentUtils.applicationName = applicationName;
    }

    public static String getEnv() {
        return env;
    }

    public static void setEnv(String env) {
        EnvironmentUtils.env = env;
    }

    public static String getAppEnv() {
        return applicationName + "[" + env + "]";
    }
}
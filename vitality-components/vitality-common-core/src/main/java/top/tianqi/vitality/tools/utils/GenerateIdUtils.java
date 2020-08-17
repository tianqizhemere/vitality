package top.tianqi.vitality.tools.utils;

import java.util.UUID;

/**
 * ID生成工具类 UUID
 * @author wkh
 * @Date 2020/7/1
 */
public class GenerateIdUtils {

    /**
     * 使用UUID生成RequestId
     * @return RequestId
     */
    public static String requestIdWithUUID() {
        return UUID.randomUUID().toString().replaceAll("-", "");
    }
}
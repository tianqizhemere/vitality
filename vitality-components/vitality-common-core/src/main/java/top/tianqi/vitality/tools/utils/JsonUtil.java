package top.tianqi.vitality.tools.utils;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * JsonUtil工具类
 * @Author wkh
 * @Date 2020/6/28 17:19
 */
public class JsonUtil {

    private static final ObjectMapper mapper = new ObjectMapper();

    static {
        mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        //设置输入时忽略JSON字符串中存在而Java对象实际没有的属性
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }

    /**
     * 对象转json
     * @param o 目标对象
     * @return json
     */
    public static String toJsonString(Object o) {
        if (o == null)
            return null;

        String s = null;

        try {
            s = mapper.writeValueAsString(o);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return s;
    }

    /**
     * 集合对象转json集合
     * @param objects 多个同一类型对象
     * @return json集合
     */
    public static <T> List<String> listToListJson(List<T> objects) {
        if (objects == null)
            return null;

        List<String> lists = new ArrayList<String>();
        for (T t : objects) {
            lists.add(JsonUtil.toJsonString(t));
        }

        return lists;
    }

    /**
     * json转集合
     * @param jsons 多个json对象
     * @param c 转换类型
     * @return
     */
    public static <T> List<T> listJsonToListObject(List<String> jsons, Class<T> c) {
        if (jsons == null)
            return null;

        List<T> ts = new ArrayList<T>();
        for (String j : jsons) {
            ts.add(JsonUtil.jsonToObject(j, c));
        }

        return ts;
    }

    /**
     * json转普通对象
     * @param json json字符串
     * @param c 转换对象
     * @return
     */
    public static <T> T jsonToObject(String json, Class<T> c) {
        if (!StringUtils.hasLength(json))
            return null;

        T t = null;
        try {
            t = mapper.readValue(json, c);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return t;
    }

    /**
     * json转任意复杂类型
     * jsonToObject(json, new TypeReference<DelayTask<Order>>() {});
     * @param json json字符串
     * @param tr 类型泛型
     * @return
     */
    @SuppressWarnings("unchecked")
    public static <T> T jsonToObject(String json, TypeReference<T> tr) {
        if (!StringUtils.hasLength(json))
            return null;

        T t = null;
        try {
            t = (T) mapper.readValue(json, tr);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return (T) t;
    }
}

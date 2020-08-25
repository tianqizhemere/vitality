package top.tianqi.vitality.entity;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * nacos动态路由配置，路由断言实体类
 * @Author wkh
 * @Date 2020/8/11 13:58
 */
public class PredicateEntity {
    /** 断言对应的Name */
    private String name;

    /** 断言规则 */
    private Map<String, String> args = new LinkedHashMap<>();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Map<String, String> getArgs() {
        return args;
    }

    public void setArgs(Map<String, String> args) {
        this.args = args;
    }
}

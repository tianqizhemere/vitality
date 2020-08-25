package top.tianqi.vitality.entity;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * nacos动态路由配置，过滤器实体类
 *
 * @Author wkh
 * @Date 2020/8/11 13:56
 */
public class FilterEntity {

    /** 过滤器对应的Name*/
    private String name;

    /** 路由规则*/
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

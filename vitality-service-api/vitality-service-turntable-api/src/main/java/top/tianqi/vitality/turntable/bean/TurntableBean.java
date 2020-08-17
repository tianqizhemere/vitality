package top.tianqi.vitality.turntable.bean;

import top.tianqi.vitality.turntable.entity.Awards;

import java.util.List;

/**
 * 转盘JavaBean
 * @Author Wukh
 */
public class TurntableBean {
    /** id */
    private Long id;

    /** 选项标题 */
    private String option;

    /** 子选项 */
    private List<Awards> awards;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Awards> getAwards() {
        return awards;
    }

    public void setAwards(List<Awards> awards) {
        this.awards = awards;
    }

    public String getOption() {
        return option;
    }

    public void setOption(String option) {
        this.option = option;
    }
}

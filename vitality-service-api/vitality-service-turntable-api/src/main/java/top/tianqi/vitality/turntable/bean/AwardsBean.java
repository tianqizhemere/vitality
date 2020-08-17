package top.tianqi.vitality.turntable.bean;

/**
 * 选项JavaBean
 * @Author:Wukh
 */

public class AwardsBean {

    /** id */
    private Long id;

    /** 选项标题 */
    private String name;

    /** 颜色 */
    private String color;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }
}

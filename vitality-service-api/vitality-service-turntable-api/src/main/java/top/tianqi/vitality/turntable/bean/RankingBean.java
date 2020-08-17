package top.tianqi.vitality.turntable.bean;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 排行榜Javabean
 * @Author:Wukh
 */
public class RankingBean {

    /** id 自增*/
    private Long id;

    /** 创建时间 */
    private Date createTime;

    /** 修改时间 */
    private Date modifyTime;

    /** 转盘子选项id*/
    private Long awardsId;

    /** 子选项名称 */
    private String awardsName;

    /** 转盘标题 */
    private String option;

    /** 排行榜结果集 key awardsName  value count */
    private List<Map<String, Object>> list;

    public List<Map<String, Object>> getList() {
        return list;
    }

    public void setList(List<Map<String, Object>> list) {
        this.list = list;
    }

    public String getOption() {
        return option;
    }

    public void setOption(String option) {
        this.option = option;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }

    public Long getAwardsId() {
        return awardsId;
    }

    public void setAwardsId(Long awardsId) {
        this.awardsId = awardsId;
    }

    public String getAwardsName() {
        return awardsName;
    }

    public void setAwardsName(String awardsName) {
        this.awardsName = awardsName;
    }
}

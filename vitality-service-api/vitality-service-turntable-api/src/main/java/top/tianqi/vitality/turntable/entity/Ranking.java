package top.tianqi.vitality.turntable.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 排行榜实体类
 * @Author:Wukh
 */
@TableName(value = "ranking")
public class Ranking {

    /** id 自增*/
    @TableId(type = IdType.AUTO)
    private Long id;

    /** 创建时间 */
    private Date createTime;

    /** 修改时间 */
    private Date modifyTime;

    /** 转盘id*/
    @TableField(exist = false)
    private Long titleId;

    /** 转盘子选择id*/
    private Long awardsId;

    /** 子选项名称 */
    @TableField(exist = false)
    private String awardsName;

    /** 转盘标题 */
    @TableField(exist = false)
    private String option;

    /** 排行榜结果集 key awardsName  key count */
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

    public Long getTitleId() {
        return titleId;
    }

    public void setTitleId(Long titleId) {
        this.titleId = titleId;
    }

    public String getAwardsName() {
        return awardsName;
    }

    public void setAwardsName(String awardsName) {
        this.awardsName = awardsName;
    }
}

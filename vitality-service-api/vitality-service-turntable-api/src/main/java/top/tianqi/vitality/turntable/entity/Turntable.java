package top.tianqi.vitality.turntable.entity;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 转盘实体类
 * @Author:Wukh
 */
@TableName(value = "turntable")
public class Turntable implements Serializable {

    /** id 自增*/
    @TableId(type = IdType.AUTO)
    private Long id;

    /** 创建时间 */
    private Date createTime;

    /** 修改时间 */
    private Date modifyTime;

    /** 标题 */
    @TableField(value = "title")
    private String option;

    /** 子选择 */
    @TableField(exist = false)
    private List<Awards> awards;

    public List<Awards> getAwards() {
        return awards;
    }

    public void setAwards(List<Awards> awards) {
        this.awards = awards;
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

    public String getOption() {
        return option;
    }

    public void setOption(String option) {
        this.option = option;
    }
}

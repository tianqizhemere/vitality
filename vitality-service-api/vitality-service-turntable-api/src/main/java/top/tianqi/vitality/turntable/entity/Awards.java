package top.tianqi.vitality.turntable.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.util.Date;
import java.util.List;

/**
 * 选项实体类
 * @Author Wukh
 */
@TableName(value = "awards")
public class Awards {

    /** id */
    @TableId(type = IdType.AUTO)
    private Long id;

    /** 创建时间 */
    private Date createTime;

    /** 修改时间 */
    private Date modifyTime;

    /** 选项标题 */
    private String name;

    /** 颜色 */
    private String color;

    /**
     * 关系维护端删除时，如果中间表存在些纪录的关联信息，则会删除该关联信息;
     * 关系被维护端删除时，如果中间表存在些纪录的关联信息，则会删除失败 .
     */
    @TableField(exist = false)
    private List<Turntable> turntables;

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

    public List<Turntable> getTurntables() {
        return turntables;
    }

    public void setTurntables(List<Turntable> turntables) {
        this.turntables = turntables;
    }
}

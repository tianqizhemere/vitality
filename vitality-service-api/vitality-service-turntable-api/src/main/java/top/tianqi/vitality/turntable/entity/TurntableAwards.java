package top.tianqi.vitality.turntable.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;


/**
 * 转盘与子选择关联关系
 * @Author:Wukh
 */
@TableName(value = "turntable_awards")
public class TurntableAwards {

    @TableId(type = IdType.AUTO)
    private Long id;

    /** 轮盘id */
    private Long turntableId;

    /** 轮盘子选择id */
    private Long awardsId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getTurntableId() {
        return turntableId;
    }

    public void setTurntableId(Long turntableId) {
        this.turntableId = turntableId;
    }

    public Long getAwardsId() {
        return awardsId;
    }

    public void setAwardsId(Long awardsId) {
        this.awardsId = awardsId;
    }
}

package top.tianqi.vitality.queue.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.util.Date;

/**
 * 延时任务实体
 * @Author wkh
 * @Date 2020/8/7 9:47
 */
@TableName(value = "delay_task")
public class DelayTask {

    /** id*/
    @TableId(type = IdType.AUTO)
    private Long id;
    /** 创建时间*/
    private Date createTime;
    /** 修改时间*/
    private Date modifyTime;
    /** 队列标识id*/
    private Long delayTaskId;
    /** 任务执行时间*/
    private Date triggerTime;
    /** 任务类，json字符串*/
    private String task;
    /** 任务执行状态码, 0:未执行，1:已执行*/
    private Integer status;
    /** 字节码编码id*/
    private Integer classId;

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

    public Long getDelayTaskId() {
        return delayTaskId;
    }

    public void setDelayTaskId(Long delayTaskId) {
        this.delayTaskId = delayTaskId;
    }

    public Date getTriggerTime() {
        return triggerTime;
    }

    public void setTriggerTime(Date triggerTime) {
        this.triggerTime = triggerTime;
    }

    public String getTask() {
        return task;
    }

    public void setTask(String task) {
        this.task = task;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getClassId() {
        return classId;
    }

    public void setClassId(Integer classId) {
        this.classId = classId;
    }
}

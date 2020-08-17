package top.tianqi.vitality.tools.queue;


import top.tianqi.vitality.tools.utils.JsonUtil;

import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

/**
 * 延时队列
 * @Author wkh
 * @Date 2020/8/4 11:46
 */
public class DelayTask<T extends Runnable> implements Delayed {

    /** 任务类 */
    private T task;
    /** 执行时间(秒) */
    private Long startTime;
    /** 队列唯一id */
    private Long id;

    public T getTask() {
        return task;
    }

    public void setTask(T task) {
        this.task = task;
    }

    public Long getStartTime() {
        return startTime;
    }

    public void setStartTime(Long startTime) {
        this.startTime = System.currentTimeMillis() + startTime * 1000L;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public DelayTask() {
    }

    /**
     * @param timeout
     *              超时时间(秒)
     * @param task
     *              任务
     * @param id
     *              唯一队列id
     */
    public DelayTask(Long timeout,T task, Long id) {
        this.startTime =  System.currentTimeMillis() + timeout * 1000L;
        this.task = task;
        this.id = id;
    }

    /**
     * 获取剩余时间
     * @param unit
     * @return
     */
    @Override
    public long getDelay(TimeUnit unit) {
        return unit.convert(startTime - System.currentTimeMillis(), TimeUnit.MILLISECONDS);
    }

    /**
     * 按照剩余时间进行排序
     * @param o Delayed对象
     * @return
     */
    @Override
    public int compareTo(Delayed o) {
        if (o == this) {
            return 0;
        }
        if (o instanceof  DelayTask) {
            DelayTask task = (DelayTask) o;
            Long startTime = task.getStartTime();
            return (int) (this.startTime - startTime);
        }
        return 0;
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof DelayTask) {
            DelayTask task = (DelayTask) o;
            return id != null && task.id != null && id.equals(task.id);
        }
        return false;
    }

    /**
     * 重写hashCode，equals方法，保证数据唯一性
     * @return
     */
    @Override
    public int hashCode() {
        return id.hashCode();
    }

    @Override
    public String toString() {
        return JsonUtil.toJsonString(this);
    }
}

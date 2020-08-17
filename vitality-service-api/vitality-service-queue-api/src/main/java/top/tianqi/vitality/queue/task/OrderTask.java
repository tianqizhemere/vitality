package top.tianqi.vitality.queue.task;

/**
 * 订单任务
 * @Author wkh
 * @Date 2020/8/7 10:56
 */
public class OrderTask implements Runnable {

    private Long id;
    private Integer status;

    public OrderTask(Long id) {
        this.id = id;
    }

    public OrderTask() {
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    @Override
    public void run() {
        System.out.println("状态修改之前---状态为:" + id);
    }
}

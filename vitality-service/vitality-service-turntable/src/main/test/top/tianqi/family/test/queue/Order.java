package top.tianqi.vitality.test.queue;


/**
 * @Author wkh
 * @Date 2020/8/4 13:31
 */
public class Order implements Runnable {

    /** 订单编号*/
    private String orderId;
    /** 订单金额 */
    private Double orderMoney;

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public Double getOrderMoney() {
        return orderMoney;
    }

    public void setOrderMoney(Double orderMoney) {
        this.orderMoney = orderMoney;
    }

    @Override
    public void run() {
        System.out.println("系统提示----隐患未整改" + getOrderId());
    }

}

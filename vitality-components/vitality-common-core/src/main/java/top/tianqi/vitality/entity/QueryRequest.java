package top.tianqi.vitality.entity;

/**
 * 分页实体类
 *
 * @Author wkh
 * @Date 2020/8/19 17:20
 */
public class QueryRequest {

    /**当前页面数据量*/
    private int pageSize = 10;
    /**当前页码*/
    private int pageNum = 1;
    /**排序字段*/
    private String field;
    /**排序规则，asc升序，desc降序*/
    private String order;

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getPageNum() {
        return pageNum;
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public String getOrder() {
        return order;
    }

    public void setOrder(String order) {
        this.order = order;
    }


}

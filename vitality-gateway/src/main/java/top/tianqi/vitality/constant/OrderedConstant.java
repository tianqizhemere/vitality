package top.tianqi.vitality.constant;

import org.springframework.cloud.gateway.filter.NettyWriteResponseFilter;
import org.springframework.core.Ordered;

/**
 * filter排序码
 * @author wkh
 * @Date 2020/7/1
 */
public interface OrderedConstant extends Ordered {

    /** 日志记录 */
    int LOGGING_FILTER = NettyWriteResponseFilter.WRITE_RESPONSE_FILTER_ORDER - 1;

    /** request */
    int REQUEST_FILTER = HIGHEST_PRECEDENCE;

}
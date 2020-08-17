package top.tianqi.vitality.filter;

import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;
import top.tianqi.vitality.constant.OrderedConstant;
import top.tianqi.vitality.log.CacheServerHttpRequestDecorator;

/**
 * 日志过滤器
 *
 * @Author wkh
 * @Date 2020/7/2 11:37
 */
@Component
public class LogFilter implements GlobalFilter, Ordered {
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        CacheServerHttpRequestDecorator cacheServerHttpRequestDecorator = new CacheServerHttpRequestDecorator(exchange.getRequest());

        return chain.filter(exchange.mutate().request(cacheServerHttpRequestDecorator).build());
    }

    @Override
    public int getOrder() {
        return OrderedConstant.LOGGING_FILTER;
    }
}

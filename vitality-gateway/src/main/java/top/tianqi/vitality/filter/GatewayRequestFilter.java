package top.tianqi.vitality.filter;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.cloud.gateway.route.Route;
import org.springframework.cloud.gateway.support.ServerWebExchangeUtils;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;
import top.tianqi.vitality.entity.Result;
import top.tianqi.vitality.entity.VitalityConstant;
import top.tianqi.vitality.properties.VitalityGatewayProperties;
import top.tianqi.vitality.tools.utils.Base64Utils;
import top.tianqi.vitality.tools.utils.JsonUtil;
import top.tianqi.vitality.tools.utils.ResultStatusCode;

import java.net.URI;
import java.time.LocalDateTime;
import java.util.LinkedHashSet;
import java.util.function.Consumer;

/**
 * 全局过滤器
 *
 * @Author wkh
 * @Date 2020/8/20 11:17
 */
@Component
public class GatewayRequestFilter implements GlobalFilter {

    private static Logger logger = LoggerFactory.getLogger(GatewayRequestFilter.class);

    @Autowired
    private VitalityGatewayProperties properties;
    @Autowired
    private AntPathMatcher pathMatcher;

    /**
     * 防止客户端绕过网关，直接请求微服务
     *
     * @param exchange ServerWebExchange
     * @param chain GatewayFilterChain
     * @return
     */
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        ServerHttpRequest request = exchange.getRequest();
        ServerHttpResponse response = exchange.getResponse();

        // 禁止客户端的访问资源
        Mono<Void> checkForbidUriResult = checkForbidUri(request, response);
        if (checkForbidUriResult != null) {
            return checkForbidUriResult;
        }
        printLog(exchange);

        byte[] token = Base64Utils.encode((VitalityConstant.GATEWAY_TOKEN_VALUE)).getBytes();
        Consumer<HttpHeaders> httpHeaders = httpHeader -> httpHeader.set(VitalityConstant.GATEWAY_TOKEN_HEADER,  new String(token));
        ServerHttpRequest build = request.mutate().headers(httpHeaders).build();
        ServerWebExchange newExchange = exchange.mutate().request(build).build();
        return chain.filter(newExchange);
    }

    /**
     * 校验访问路径
     *
     * @param request ServerHttpRequest
     * @param response ServerHttpResponse
     * @return
     */
    private Mono<Void> checkForbidUri(ServerHttpRequest request, ServerHttpResponse response) {
        // 获取当前访问路径
        String uri = request.getPath().toString();
        boolean shouldForward = true;
        String forbidRequestUri = properties.getForbidRequestUri();
        String[] forbidRequestUris = StringUtils.splitByWholeSeparatorPreserveAllTokens(forbidRequestUri, ",");
        if (forbidRequestUris != null && ArrayUtils.isNotEmpty(forbidRequestUris)) {
            for (String u : forbidRequestUris) {
                if (pathMatcher.match(u, uri)) {
                    shouldForward = false;
                }
            }
        }
        if (!shouldForward) {
            Result<String> result = new Result<>(ResultStatusCode.DENY_EXTERNAL_ACCESS.getCode(), ResultStatusCode.DENY_EXTERNAL_ACCESS.getMsg());
            return makeResponse(response, result);
        }
        return null;
    }

    /**
     * 创建响应体
     *
     * @param response ServerHttpResponse
     * @param result 响应类
     * @return
     */
    private Mono<Void> makeResponse(ServerHttpResponse response, Result<String> result) {
        response.setStatusCode(HttpStatus.FORBIDDEN);
        response.getHeaders().add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_UTF8_VALUE);
        DataBuffer dataBuffer = response.bufferFactory().wrap(JsonUtil.toJsonString(result).getBytes());
        return response.writeWith(Mono.just(dataBuffer));
    }

    /**
     * 转发日志
     *
     * @param exchange
     */
    private void printLog(ServerWebExchange exchange) {
        URI url = exchange.getAttribute(ServerWebExchangeUtils.GATEWAY_REQUEST_URL_ATTR);
        Route route = exchange.getAttribute(ServerWebExchangeUtils.GATEWAY_ROUTE_ATTR);
        LinkedHashSet<URI> uris = exchange.getAttribute(ServerWebExchangeUtils.GATEWAY_ORIGINAL_REQUEST_URL_ATTR);
        URI originUri = null;
        if (uris != null) {
            originUri = uris.stream().findFirst().orElse(null);
        }
        if (url != null && route != null && originUri != null) {
            logger.info("转发请求：{}://{}{} --> 目标服务：{}，目标地址：{}://{}{}，转发时间：{}",
                    originUri.getScheme(), originUri.getAuthority(), originUri.getPath(),
                    route.getId(), url.getScheme(), url.getAuthority(), url.getPath(), LocalDateTime.now()
            );
        }
    }
}

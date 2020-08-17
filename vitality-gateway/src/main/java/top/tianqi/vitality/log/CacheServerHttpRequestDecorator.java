package top.tianqi.vitality.log;

import io.netty.buffer.UnpooledByteBufAllocator;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.core.io.buffer.NettyDataBufferFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpRequestDecorator;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;
import top.tianqi.vitality.constant.HeaderConstant;
import top.tianqi.vitality.tools.utils.IpUtils;
import top.tianqi.vitality.tools.utils.JsonUtil;
import top.tianqi.vitality.tools.utils.LogUtil;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.util.Objects;

/**
 * <p>自动缓存下请求体，读取的时候读取缓存内容</p>
 * <p>对ServerHttpRequest进行二次封装，解决requestBody只能读取一次的问题</p>
 *
 * @author wkh
 * @Date 2020/7/1
 */
public class CacheServerHttpRequestDecorator extends ServerHttpRequestDecorator {
    private DataBuffer bodyDataBuffer;
    private int getBufferTime = 0;
    private byte[] bytes;

    public CacheServerHttpRequestDecorator(ServerHttpRequest delegate) {
        super(delegate);
    }

    @Override
    public Flux<DataBuffer> getBody() {
        if (getBufferTime == 0) {
            getBufferTime++;
            Flux<DataBuffer> flux = super.getBody();
            return flux.publishOn(Schedulers.single())
                    .map(this::cache)
                    .doOnComplete(() -> trace(getDelegate()));

        } else {
            return Flux.just(getBodyMore());
        }

    }


    private DataBuffer getBodyMore() {
        NettyDataBufferFactory nettyDataBufferFactory = new NettyDataBufferFactory(new UnpooledByteBufAllocator(false));
        bodyDataBuffer = nettyDataBufferFactory.wrap(bytes);
        return bodyDataBuffer;
    }

    private DataBuffer cache(DataBuffer buffer) {
        try {
            InputStream dataBuffer = buffer.asInputStream();
            bytes = IOUtils.toByteArray(dataBuffer);
            NettyDataBufferFactory nettyDataBufferFactory = new NettyDataBufferFactory(new UnpooledByteBufAllocator(false));
            bodyDataBuffer = nettyDataBufferFactory.wrap(bytes);
            return bodyDataBuffer;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private void trace(ServerHttpRequest request) {
        URI requestUri = request.getURI();
        String uriQuery = requestUri.getQuery();
        String url = requestUri.getPath() + (StringUtils.isNotBlank(uriQuery) ? "?" + uriQuery : "");
        HttpHeaders headers = request.getHeaders();
        MediaType mediaType = headers.getContentType();
        String schema = requestUri.getScheme();
        String method = request.getMethodValue().toUpperCase();
        if ((!"http".equals(schema) && !"https".equals(schema))) {
            return;
        }
        String reqBody = null;
        if (Objects.nonNull(mediaType) && LogHelper.isUploadFile(mediaType)) {
            reqBody = "上传文件";
        } else {
            if (method.equals("GET")) {
                if (StringUtils.isNotBlank(uriQuery)) {
                    reqBody = uriQuery;
                }
            } else if (headers.getContentLength() > 0) {
                reqBody = LogHelper.readRequestBody(request);
            }
        }
        final Log logDTO = new Log();
        logDTO.setLevel(Log.LEVEL.INFO);
        logDTO.setRequestUrl(url);
        logDTO.setRequestBody(reqBody);
        logDTO.setRequestMethod(method);
        logDTO.setRequestId(headers.getFirst(HeaderConstant.REQUEST_ID));
        logDTO.setIp(IpUtils.getClientIp(request));
        LogUtil.info(JsonUtil.toJsonString(logDTO));
    }

}
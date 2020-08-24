package top.tianqi.vitality.annotation;

import org.springframework.context.annotation.Import;
import top.tianqi.vitality.config.ServerProtectConfigure;

import java.lang.annotation.*;

/**
 * 开启微服务防护，避免客户端绕过网关直接请求微服务
 *
 * @author tianQi
 * @Date 2020-08-20
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import(ServerProtectConfigure.class)
public @interface EnableServerProtect {
}

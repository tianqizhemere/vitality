package top.tianqi.vitality.annotation;

import org.springframework.context.annotation.Import;
import top.tianqi.vitality.config.OAuth2FeignConfigure;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 使OAuth2FeignConfigure生效
 *
 * @Author wkh
 * @Date 2020/8/21 10:24
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Import(OAuth2FeignConfigure.class)
public @interface EnableOauth2FeignClient {
}

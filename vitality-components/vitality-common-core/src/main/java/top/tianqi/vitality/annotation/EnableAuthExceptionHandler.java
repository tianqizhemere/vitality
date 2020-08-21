package top.tianqi.vitality.annotation;

import org.springframework.context.annotation.Import;
import top.tianqi.vitality.config.AuthExceptionConfigure;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 引入默认的资源异常处理
 *
 * @Author wkh
 * @Date 2020/8/21 10:04
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Import(AuthExceptionConfigure.class)
public @interface EnableAuthExceptionHandler {
}

package top.tianqi.vitality.annotation;

import org.springframework.context.annotation.Import;
import top.tianqi.vitality.config.LettuceRedisConfigure;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 启用lettuceRedis
 *
 * @Author wkh
 * @Date 2020/8/19 14:54
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Import(value = LettuceRedisConfigure.class)
public @interface EnableLettuceRedis {
}

package top.tianqi.vitality.annotation;

import org.springframework.context.annotation.Import;
import top.tianqi.vitality.selector.CloudApplicationSelector;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 将CloudApplicationSelector注册到IOC容器中
 *
 * @Author wkh
 * @Date 2020/8/21 10:30
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Import(CloudApplicationSelector.class)
public @interface EnableCloudApplication {
}

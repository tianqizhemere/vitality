package top.tianqi.vitality.selector;

import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.type.AnnotationMetadata;
import top.tianqi.vitality.config.AuthExceptionConfigure;
import top.tianqi.vitality.config.OAuth2FeignConfigure;
import top.tianqi.vitality.config.ServerProtectConfigure;

/**
 * 将微服务常用配置类整合在一个注解中
 *
 * @Author wkh
 * @Date 2020/8/21 10:22
 */
public class CloudApplicationSelector implements ImportSelector {
    @Override
    public String[] selectImports(AnnotationMetadata annotationMetadata) {
        return new String[]{
                AuthExceptionConfigure.class.getName(),
                OAuth2FeignConfigure.class.getName(),
                ServerProtectConfigure.class.getName()
        };
    }
}

package top.tianqi.vitality.turntable.feign;

import org.springframework.cloud.openfeign.FeignClient;
import top.tianqi.vitality.entity.VitalityServerConstant;

/**
 * 选项feign
 */
@FeignClient(value = VitalityServerConstant.VITALITY_SERVER_TURNTABLE, contextId = "awards")
public interface AwardsFeign {
}

package top.tianqi.vitality.turntable.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 选项feign
 */
@FeignClient("awards")
@RequestMapping("/awards")
public interface AwardsFeign {
}

package top.tianqi.vitality.turntable.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 转盘feign
 */
@FeignClient("turntable")
@RequestMapping("/turntable")
public interface TurntableFeign {
}

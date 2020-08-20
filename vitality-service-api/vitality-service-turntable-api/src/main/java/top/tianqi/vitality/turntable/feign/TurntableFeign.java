package top.tianqi.vitality.turntable.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import top.tianqi.vitality.entity.Result;
import top.tianqi.vitality.entity.VitalityServerConstant;
import top.tianqi.vitality.turntable.entity.Turntable;

import java.util.List;

/**
 * 转盘feign
 */
@FeignClient(value = VitalityServerConstant.VITALITY_SERVER_TURNTABLE, contextId = "turntable")
public interface TurntableFeign {

    @GetMapping(value = "/list")
    Result<List<Turntable>> list();
}

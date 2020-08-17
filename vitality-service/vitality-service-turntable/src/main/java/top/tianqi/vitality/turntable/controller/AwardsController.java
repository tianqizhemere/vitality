package top.tianqi.vitality.turntable.controller;

import org.springframework.web.bind.annotation.*;
import top.tianqi.vitality.turntable.entity.Awards;
import top.tianqi.vitality.tools.utils.Result;
import top.tianqi.vitality.tools.utils.StatusCode;
import top.tianqi.vitality.turntable.service.AwardsService;

import javax.annotation.Resource;
import java.util.List;


/**
 * 转盘子选项controller
 * @Author Wukh
 */
@RestController("awardsController")
@RequestMapping("/awards")
@CrossOrigin
public class AwardsController {

    @Resource(name = "awardsServiceImpl")
    private AwardsService awardsService;

    /**
     * 加载数据列表
     * @return 响应结果集
     */
    @GetMapping(value = "/list")
    public Result<List<Awards>> list(){
        List<Awards> list = awardsService.findList();
        return new Result<>(true, StatusCode.OK, "查询数据成功", list);
    }

    @GetMapping(value = "/findByTurntableId/{turntableId}")
    public Result<List<Awards>> findByTurntableId(@PathVariable(value = "turntableId") Long turntableId){
        List<Awards> list = awardsService.findByTurntableId(turntableId);
        return new Result<>(true, StatusCode.OK, "条件查询成功", list);
    }
}

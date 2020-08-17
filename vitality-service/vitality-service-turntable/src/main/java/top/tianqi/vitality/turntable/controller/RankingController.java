package top.tianqi.vitality.turntable.controller;

import org.springframework.web.bind.annotation.*;
import top.tianqi.vitality.turntable.bean.RankingBean;
import top.tianqi.vitality.turntable.entity.Ranking;
import top.tianqi.vitality.tools.utils.Result;
import top.tianqi.vitality.tools.utils.StatusCode;
import top.tianqi.vitality.turntable.service.RankingService;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.List;
import java.util.Map;


/**
 * 排行榜controller
 * @author wkh
 * @Date 2020/06/30
 */
@RestController("rankingController")
@RequestMapping("/ranking")
@CrossOrigin
public class RankingController {

    @Resource(name = "rankingServiceImpl")
    private RankingService rankingService;

    /**
     * 加载数据列表
     * @return 响应结果集
     */
    @GetMapping(value = "/list")
    public Result<List<Ranking>> list(){
        List<Ranking> list = rankingService.findList();
        return new Result<>(true, StatusCode.OK, "查询排行榜数据成功", list);
    }

    /**
     * 根据转盘id进行条件查询
     * @param id 转盘id
     * @return 响应结果集
     */
    @PostMapping(value = "/findByTurntableId")
    public Result<List<Map<String, Object>>> findByTurntableId(@RequestParam("id") Long id){
        List<Map<String, Object>> list = rankingService.findByTurntableId(id);
        return new Result<>(true, StatusCode.OK, "条件查询成功", list);
    }

    /**
     * 新增排行榜
     * @param rankingBean 排行榜信息
     * @return 响应结果集
     */
    @PostMapping(value = "/add")
    public Result<Object> add(@Valid @RequestBody RankingBean rankingBean){
        rankingService.add(rankingBean);
        return new Result<>(true, StatusCode.OK, "数据插入成功");
    }


    /**
     * 查询每天抽取的排行榜信息
     * @return 响应结果集
     */
    @GetMapping(value = "/day")
    public Result<List<Ranking>> findByDay() {
        List<Ranking> list = rankingService.findByDay();
        return new Result<>(true, StatusCode.OK, "条件查询成功", list);
    }
}

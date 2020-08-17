package top.tianqi.vitality.turntable.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import top.tianqi.vitality.turntable.bean.RankingBean;
import top.tianqi.vitality.turntable.entity.Ranking;
import top.tianqi.vitality.tools.utils.BeanUtils;
import top.tianqi.vitality.tools.utils.DateUtils;
import top.tianqi.vitality.turntable.dao.RankingMapper;
import top.tianqi.vitality.turntable.service.RankingService;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.Map;


/**
 * 排行榜业务层实现
 * @author wukh
 */
@Service(value = "rankingServiceImpl")
@Transactional(rollbackFor = Exception.class)
public class RankingServiceImpl extends ServiceImpl<RankingMapper, Ranking> implements RankingService {

    @Resource
    private RankingMapper rankingMapper;

    @Override
    public Integer add(RankingBean rankingBean) {
        Ranking ranking = new Ranking();
        BeanUtils.copyProperties(rankingBean, ranking);
        if (ranking.getAwardsId() != null) {
            ranking.setCreateTime(new Date());
            ranking.setModifyTime(new Date());
            return rankingMapper.insert(ranking);
        }
        return 0;
    }

    @Override
    public List<Ranking> findList() {
        List<Ranking> list = rankingMapper.findList();
        if (list != null && list.size() != 0) {
            for (Ranking ranking : list) {
                List<Map<String, Object>> awards = findByTurntableId(ranking.getTitleId());
                ranking.setList(awards);
            }
        }
        return list;
    }

    @Override
    public List<Map<String, Object>> findByTurntableId(Long id) {
        return rankingMapper.findByTurntableId(id);
    }

    @Override
    public List<Ranking> findByDay() {
        Date beginTime = DateUtils.getDayBegin();
        Date endTime = DateUtils.getDayEnd();
        List<Ranking> list = rankingMapper.findByToDay(beginTime, endTime);
        if (list != null && list.size() != 0) {
            for (Ranking ranking : list) {
                List<Map<String, Object>> awards = rankingMapper.findByDay(ranking.getTitleId(), beginTime, endTime);
                ranking.setList(awards);
            }
        }
        return list;
    }
}

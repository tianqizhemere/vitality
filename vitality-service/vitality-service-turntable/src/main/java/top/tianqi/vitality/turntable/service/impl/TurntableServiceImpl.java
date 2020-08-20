package top.tianqi.vitality.turntable.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import top.tianqi.vitality.tools.utils.BeanUtils;
import top.tianqi.vitality.turntable.bean.TurntableBean;
import top.tianqi.vitality.turntable.entity.Awards;
import top.tianqi.vitality.turntable.entity.Turntable;
import top.tianqi.vitality.turntable.entity.TurntableAwards;
import top.tianqi.vitality.turntable.mapper.AwardsMapper;
import top.tianqi.vitality.turntable.mapper.RankingMapper;
import top.tianqi.vitality.turntable.mapper.TurntableAwardsMapper;
import top.tianqi.vitality.turntable.mapper.TurntableMapper;
import top.tianqi.vitality.turntable.service.TurntableService;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * @author wukh
 */
@Service(value = "turntableServiceImpl")
@Transactional(rollbackFor = Exception.class)
public class TurntableServiceImpl extends ServiceImpl<TurntableMapper, Turntable> implements TurntableService {

    @Autowired
    private TurntableMapper turntableMapper;

    @Autowired
    private AwardsMapper awardsMapper;

    @Autowired
    private TurntableAwardsMapper turntableAwardsMapper;

    @Autowired
    private RankingMapper rankingMapper;


    @Override
    public List<Turntable> findList() {
        return turntableMapper.selectAll();
    }

    @Override
    public List<Turntable> findTreeList() {
        return turntableMapper.findTreeList();
    }

    @Override
    public void add(TurntableBean turntableBean) {
        Turntable turntable = new Turntable();
        BeanUtils.copyProperties(turntableBean, turntable);
        turntable.setCreateTime(new Date());
        turntable.setModifyTime(new Date());
        turntableMapper.insert(turntable);
        for (Awards award : turntable.getAwards()) {
            award.setCreateTime(new Date());
            award.setModifyTime(new Date());
            awardsMapper.insert(award);
            TurntableAwards turntableAwards = new TurntableAwards();
            turntableAwards.setAwardsId(award.getId());
            turntableAwards.setTurntableId(turntable.getId());
            turntableAwardsMapper.insert(turntableAwards);
        }
    }

    @Override
    public void delete(Long id) {
        List<TurntableAwards> list = turntableAwardsMapper.findByTurntableId(id);
        if (list != null) {
            for (TurntableAwards turntableAwards : list) {
                Long awardsId = turntableAwards.getAwardsId();
                Map<String, Object> awardsMap = new HashMap<>();
                awardsMap.put("awards_id", awardsId);
                rankingMapper.deleteByMap(awardsMap);
                awardsMapper.deleteById(turntableAwards.getAwardsId());
            }
        }
        turntableMapper.deleteById(id);
        Map<String, Object>  map = new HashMap<>();
        map.put("turntable_id", id);
        turntableAwardsMapper.deleteByMap(map);
    }

    @Override
    public void edit(TurntableBean turntableBean) {
        Turntable turntable = new Turntable();
        BeanUtils.copyProperties(turntableBean, turntable);
        turntable.setModifyTime(new Date());
        turntableMapper.updateById(turntable);
        turntableAwardsMapper.deleteByTurntableId(turntable.getId());
        for (Awards award : turntable.getAwards()) {
            award.setModifyTime(new Date());
            if (award.getId() == null) {
                award.setCreateTime(new Date());
                awardsMapper.insert(award);
            }
            TurntableAwards turntableAwards = new TurntableAwards();
            turntableAwards.setAwardsId(award.getId());
            turntableAwards.setTurntableId(turntable.getId());
            turntableAwardsMapper.insert(turntableAwards);
            awardsMapper.updateById(award);
        }
    }

    @Override
    public void logicDelete(Long id) {

    }
}

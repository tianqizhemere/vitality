package top.tianqi.vitality.turntable.dao;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import top.tianqi.vitality.turntable.entity.Awards;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * 子选项持久层接口
 * @Author Wukh
 */
public interface AwardsMapper extends BaseMapper<Awards> {

    /**
     * 查询所有信息
     * @return
     */
    @Select("select * from awards")
    List<Awards> selectAll();

    /**
     * 根据转盘id查询子选择信息
     * @param turntableId 转盘名称id
     * @return
     */
    List<Awards> findByTurntableId(Long turntableId);
}

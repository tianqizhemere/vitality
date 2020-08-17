package top.tianqi.vitality.turntable.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import top.tianqi.vitality.turntable.entity.TurntableAwards;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * 中间关联表持久层
 * @Author Wukh
 */
public interface TurntableAwardsMapper extends BaseMapper<TurntableAwards> {

    /**
     * 根据转盘id查找中间表信息
     * @param id 转盘id
     * @return
     */
    @Select("select * from turntable_awards where turntable_id = #{id}")
    List<TurntableAwards> findByTurntableId(@Param("id") Long id);

    /**
     * 通过转盘ID删除转盘与子项关联关系
     * @param id 转盘id
     */
    @Delete("delete from turntable_awards where turntable_id = #{id}")
    void deleteByTurntableId(@Param("id") Long id);
}

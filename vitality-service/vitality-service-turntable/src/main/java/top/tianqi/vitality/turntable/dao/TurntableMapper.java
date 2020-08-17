package top.tianqi.vitality.turntable.dao;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import top.tianqi.vitality.turntable.entity.Turntable;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * 转盘持久层接口
 * @Author Wukh
 */
public interface TurntableMapper extends BaseMapper<Turntable> {

    /**
     * 查询所有信息
     * @return
     */
    @Select("select id,title 'option' from turntable")
    List<Turntable> selectAll();

    List<Turntable> findTreeList();

    /**
     * 根据主键id查询
     * @param id
     * @return
     */
    @Select("select * from turntable where id = #{id}")
    Turntable get(@Param("id") Long id);
}

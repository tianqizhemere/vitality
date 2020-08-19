package top.tianqi.vitality.turntable.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import top.tianqi.vitality.turntable.entity.Ranking;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 排行榜持久层接口
 * @Author Wukh
 */
public interface RankingMapper extends BaseMapper<Ranking> {

    /**
     * 查询排行榜所有数据
     * @return
     */
    @Select("select count(*), t.id titleId, t.title 'option' from ranking r\n" +
            "left join awards a on r.awards_id = a.id\n" +
            "left join turntable_awards ta on ta.awards_id = a.id\n" +
            "left join turntable t on t.id = ta.turntable_id\n" +
            "group by t.title, t.id\n" +
            "order by count(*) desc")
    List<Ranking> findList();

    /**
     * 根据转盘id查询对应排行榜信息
     * @param id
     * @return
     */
    @Select("select count(*) count, a.name from ranking r\n" +
            "left join awards a on r.awards_id = a.id\n" +
            "left join turntable_awards ta on ta.awards_id = a.id\n" +
            "where ta.turntable_id = #{id}\n" +
            "group by a.name\n" +
            "order by count(*) desc")
    List<Map<String, Object>> findByTurntableId(@Param("id") Long id);

    /**
     * 获取今日抽取排行榜信息
     * @param beginTime 开始时间
     * @param endTime 结束时间
     * @return
     */
    List<Ranking> findByToDay(@Param(value = "beginTime")Date beginTime, @Param(value = "endTime") Date endTime);

    /**
     * 根据转盘id查询当天数据
     * @param titleId 转盘id
     * @param beginTime 当天开始日期
     * @param endTime 当天结束日期
     * @return
     */
    List<Map<String, Object>> findByDay(@Param(value = "titleId") Long titleId, @Param(value = "beginTime")Date beginTime, @Param(value = "endTime") Date endTime);
}

package top.tianqi.vitality.queue.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import top.tianqi.vitality.queue.entity.DelayTask;

import java.util.List;

/**
 * 延时队列持久层接口
 * @Author wkh
 * @Date 2020/8/7 9:58
 */
public interface DelayTaskMapper extends BaseMapper<DelayTask> {

    List<DelayTask> findAll();


}

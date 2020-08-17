package top.tianqi.vitality.turntable.service;

import com.baomidou.mybatisplus.extension.service.IService;
import top.tianqi.vitality.turntable.entity.Awards;

import java.util.List;

/**
 * 子选项服务层接口
 * @Author Wukh
 */
public interface AwardsService extends IService<Awards> {

    /**
     * 加载数据列表
     * @return 查询所有
     */
    List<Awards> findList();

    /**
     * 根据转盘id查询子选择属性信息
     * @param turntableId 转盘名称id
     * @return
     */
    List<Awards> findByTurntableId(Long turntableId);

    /**
     * 添加子选择
     * @param awards 子选项
     * @return
     */
    Integer add(Awards awards);
}

package top.tianqi.vitality.turntable.service;

import com.baomidou.mybatisplus.extension.service.IService;
import top.tianqi.vitality.turntable.bean.TurntableBean;
import top.tianqi.vitality.turntable.entity.Turntable;

import java.util.List;

/**
 * 转盘业务层接口
 * @Author Wukh
 */
public interface TurntableService extends IService<Turntable> {

    /**
     * 加载数据列表
     * @return 查询所有
     */
    List<Turntable> findList();

    /**
     * 加载树状图数据列表
     * @return 树形数据集
     */
    List<Turntable> findTreeList();

    /**
     * 添加转盘
     * @param turntableBean 转盘javabean
     */
    void add(TurntableBean turntableBean);

    /**
     * 删除转盘
     * @param id 转盘主键id
     */
    void delete(Long id);

    /**
     * 修改转盘
     * @param turntableBean 转盘Javabean
     */
    void edit(TurntableBean turntableBean);

    /**
     * 执行逻辑删除
     * @param id 转盘id
     */
    void logicDelete(Long id);
}

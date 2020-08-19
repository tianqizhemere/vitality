package top.tianqi.vitality.auth.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import top.tianqi.vitality.system.entity.Menu;

import java.util.List;

/**
 * 菜单持久层接口
 *
 * @Author wkh
 * @Date 2020/8/19 11:42
 */
public interface MenuMapper extends BaseMapper<Menu> {

    /**
     * 通过用户名查找用户权限集合
     *
     * @param username 用户名
     * @return 权限集合
     */
    List<Menu> findUserPermissions(String username);
}

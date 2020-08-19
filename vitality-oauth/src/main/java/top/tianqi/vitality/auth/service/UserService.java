package top.tianqi.vitality.auth.service;

import top.tianqi.vitality.system.entity.User;

/**
 * 用户服务层接口
 *
 * @Author wkh
 * @Date 2020/8/19 11:40
 */
public interface UserService {

    /**
     * 通过用户名查找用户信息
     *
     * @param userName 用户名
     * @return 用户对象
     */
    User findByUsername(String userName);

    /**
     * 通过用户名查找用户权限
     *
     * @param username 用户名
     * @return 权限
     */
    String findUserPermissions(String username);
}

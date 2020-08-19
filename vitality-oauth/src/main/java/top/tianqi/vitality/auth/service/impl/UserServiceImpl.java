package top.tianqi.vitality.auth.service.impl;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.tianqi.vitality.auth.mapper.MenuMapper;
import top.tianqi.vitality.auth.mapper.UserMapper;
import top.tianqi.vitality.auth.service.UserService;
import top.tianqi.vitality.system.entity.Menu;
import top.tianqi.vitality.system.entity.User;

import java.util.ArrayList;
import java.util.List;

/**
 * 用户服务层实现
 *
 * @Author wkh
 * @Date 2020/8/19 11:40
 */
@Service(value = "userServiceImpl")
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private MenuMapper menuMapper;

    @Override
    public User findByUsername(String userName) {
        return userMapper.findByUsername(userName);
    }

    @Override
    public String findUserPermissions(String username) {
        List<Menu> permissions = menuMapper.findUserPermissions(username);
        List<String> perms = new ArrayList<>();
        if (!permissions.isEmpty()) {
            for (Menu menu : permissions) {
                perms.add(menu.getPerms());
            }
        }
        return StringUtils.join(perms, ",");
    }
}

package top.tianqi.vitality.auth.service.impl;

import org.apache.commons.lang3.StringUtils;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import top.tianqi.vitality.auth.service.UserService;
import top.tianqi.vitality.entity.AuthUser;
import top.tianqi.vitality.system.entity.User;
import top.tianqi.vitality.tools.utils.BeanUtils;

import javax.annotation.Resource;

/**
 * 身份认证
 *
 * @Author wkh
 * @Date 2020/8/17 15:30
 */
@Service(value = "authUserDetailService")
public class AuthUserDetailServiceImpl implements UserDetailsService {

    @Resource(name = "passwordEncoder")
    private PasswordEncoder passwordEncoder;

    @Resource(name = "userServiceImpl")
    private UserService userService;

    /**
     * 根据用户名获取用户 - 用户的角色、权限等信息
     * @param username 用户名
     * @return
     * @throws UsernameNotFoundException 用户名不存在
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userService.findByUsername(username);
        if (user != null) {
            boolean notLocked = false;
            // 获取用户权限
            String permissions = userService.findUserPermissions(username);
            if (StringUtils.equals(User.STATUS_VALID, user.getStatus())) {
                notLocked = true;
            }
            AuthUser authUser = new AuthUser(username, user.getPassword(), true, true, true, notLocked, AuthorityUtils.commaSeparatedStringToAuthorityList(permissions));
            BeanUtils.copyProperties(user, authUser);
            return authUser;
        }
        throw new UsernameNotFoundException("用户名不存在");
    }
}

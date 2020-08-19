package top.tianqi.vitality.auth.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import top.tianqi.vitality.system.entity.User;

/**
 * 用户持久层接口
 *
 * @Author wkh
 * @Date 2020/8/19 11:32
 */
public interface UserMapper extends BaseMapper<User> {

    /**
     * 根据用户名查找用户
     *
     * @param username 用户名
     * @return 用户对象
     */
    User findByUsername(@Param(value = "username") String username);

}

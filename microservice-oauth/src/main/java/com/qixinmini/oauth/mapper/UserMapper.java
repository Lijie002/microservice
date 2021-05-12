package com.qixinmini.oauth.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.qixinmini.common.core.model.system.SystemUser;
import com.qixinmini.common.core.model.system.UserDataPermission;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 *  @Description 用户持久层接口
 *  @author lijie
 *  @Date 2021/5/11 10:29
 */
@Repository
public interface UserMapper extends BaseMapper<SystemUser> {
    /**
     * @Description 得到用户
     * @author lijie
     * @createTime 2021-05-11 15:39:16
     * @param username
     */
    SystemUser findByName(String username);

    /**
     * @Description 获取用户数据权限
     * @author lijie
     * @createTime 2021-05-11 16:40:35
     * @param userId
     */
    List<UserDataPermission> findUserDataPermissions(Long userId);
}

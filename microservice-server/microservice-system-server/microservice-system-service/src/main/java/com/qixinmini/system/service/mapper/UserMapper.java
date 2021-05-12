package com.qixinmini.system.service.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.qixinmini.common.core.model.system.SystemUser;
import com.qixinmini.common.core.model.system.UserDataPermission;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 *  @Description 用户持久层接口
 *  @author lijie
 *  @Date 2020/5/15 10:29
 */
@Repository
public interface UserMapper extends BaseMapper<SystemUser> {
    /**
     * @Description 得到用户
     * @author lijie
     * @createTime 2020-05-15 15:39:16
     * @param username
     * @return com.rainbow.common.core.entity.system.SystemUser
     */
    SystemUser findByName(String username);

    /**
     * @Description 获取用户数据权限
     * @author lijie
     * @createTime 2020-05-15 16:40:35
     * @param userId
     * @return java.util.List<UserDataPermission>
     */
    List<UserDataPermission> findUserDataPermissions(Long userId);

    /**
     * @Description 分页查询
     * @author lijie
     * @createTime 2020-05-26 15:47:27
     * @param page
     * @param user
     * @return com.baomidou.mybatisplus.core.metadata.IPage<SystemUser>
     */
    IPage<SystemUser> userPage(Page<SystemUser> page, @Param("user") SystemUser user);
}

package com.qixinmini.oauth.service;


import com.qixinmini.common.core.model.system.SystemUser;

/**
 *  @Description 用户业务层接口
 *  @author lijie
 *  @Date 2021-05-11 10:15
 */
public interface UserService {
    /**
     * @Description 得到用户
     * @author lijie
     * @createTime 2021-05-11 16:49:15
     * @param username
     */
    SystemUser selectUserByUsername(String username);
    /**
     * @Description 得到用户权限
     * @author lijie
     * @createTime 2021-05-11 16:49:29
     * @param username
     */
    String findUserPermissions(String username);
}

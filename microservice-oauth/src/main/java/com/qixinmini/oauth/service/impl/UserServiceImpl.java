package com.qixinmini.oauth.service.impl;

import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.qixinmini.common.core.model.system.Menu;
import com.qixinmini.common.core.model.system.SystemUser;
import com.qixinmini.common.core.model.system.UserDataPermission;
import com.qixinmini.oauth.exception.AuthException;
import com.qixinmini.oauth.mapper.MenuMapper;
import com.qixinmini.oauth.mapper.UserMapper;
import com.qixinmini.oauth.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

/**
 *  @Description 用户业务层实现类
 *  @author lijie
 *  @Date 2020/5/15 10:23
 */
@Service
@Transactional(rollbackFor = Exception.class)
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserMapper userMapper;

    private final MenuMapper menuMapper;
    
    @Override
    public SystemUser selectUserByUsername(String username) {
        try {
            SystemUser user = userMapper.findByName(username);
            List<UserDataPermission> permissions = userMapper.findUserDataPermissions(user.getUserId());
            String deptIds = permissions.stream().map(p -> String.valueOf(p.getDeptId())).collect(Collectors.joining(StringPool.COMMA));
            user.setDeptIds(deptIds);
            return user;
        }catch (Exception e){
            e.printStackTrace();
            throw new AuthException("查询用户失败");
        }
    }

    @Override
    public String findUserPermissions(String username) {
        try {
            List<Menu> userPermissions = menuMapper.findUserPermissions(username);
            return userPermissions.stream().map(Menu::getPerms).collect(Collectors.joining(StringPool.COMMA));
        }catch (Exception e){
            e.printStackTrace();
            throw new AuthException("查询用户失败");
        }
    }
}

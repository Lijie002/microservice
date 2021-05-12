package com.qixinmini.system.service.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.qixinmini.common.core.constant.MicroserviceConstant;
import com.qixinmini.common.core.model.QueryRequest;
import com.qixinmini.common.core.model.system.Menu;
import com.qixinmini.common.core.model.system.SystemUser;
import com.qixinmini.common.core.model.system.UserDataPermission;
import com.qixinmini.common.core.model.system.UserRole;
import com.qixinmini.common.core.utils.MicroserviceUtil;
import com.qixinmini.system.api.dto.MicroserviceMail;
import com.qixinmini.system.api.feign.RainbowMailFeign;
import com.qixinmini.system.service.exception.SystemException;
import com.qixinmini.system.service.mapper.MenuMapper;
import com.qixinmini.system.service.mapper.UserDataPermissionMapper;
import com.qixinmini.system.service.mapper.UserMapper;
import com.qixinmini.system.service.mapper.UserRoleMapper;
import com.qixinmini.system.service.service.UserDataPermissionService;
import com.qixinmini.system.service.service.UserRoleService;
import com.qixinmini.system.service.service.UserService;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author lijie
 * @Description 用户业务层实现类
 * @Date 2020/5/15 10:23
 */
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserMapper userMapper;

    private final MenuMapper menuMapper;

    private final UserRoleMapper userRoleMapper;

    private final UserRoleService userRoleService;

    private final UserDataPermissionService dataPermissionService;

    private final PasswordEncoder passwordEncoder;

    private final UserDataPermissionMapper userDataPermissionMapper;

    private final RainbowMailFeign rainbowMailFeign;

    @Override
    public SystemUser selectUserByUsername(String username) {
        try {
            SystemUser user = userMapper.findByName(username);
            List<UserDataPermission> permissions = userMapper.findUserDataPermissions(user.getUserId());
            String deptIds = permissions.stream().map(p -> String.valueOf(p.getDeptId())).collect(Collectors.joining(StringPool.COMMA));
            user.setDeptIds(deptIds);
            return user;
        } catch (Exception e) {
            e.printStackTrace();
            throw new SystemException("查询用户失败");
        }
    }

    @Override
    public String findUserPermissions(String username) {
        String userPermission = "";
        try {
            List<Menu> userPermissions = menuMapper.findUserPermissions(username);
            userPermission = userPermissions.stream().map(Menu::getPerms).collect(Collectors.joining(StringPool.COMMA));
        } catch (Exception e) {
            e.printStackTrace();
            throw new SystemException("查询用户失败");
        }
        return userPermission;
    }

    @Override
    public IPage<SystemUser> userPage(QueryRequest queryRequest, SystemUser user) {
        IPage<SystemUser> iPage = null;
        try {
            Page<SystemUser> page = new Page<>(queryRequest.getPageNum(), queryRequest.getPageSize());
            iPage = userMapper.userPage(page, user);
        } catch (Exception e) {
            e.printStackTrace();
            throw new SystemException("查询用户失败");
        }
        return iPage;
    }

    @Override
    public SystemUser addUser(SystemUser user) {
        try {
            user.setPassword(passwordEncoder.encode(MicroserviceConstant.DEFAULT_PASSWORD));
            user.setAvatar(passwordEncoder.encode(MicroserviceConstant.DEFAULT_AVATAR));
            userMapper.insert(user);
            // 保存角色对应关系
            if (StringUtils.isNotBlank(user.getRoleIds())) {
                String[] roleIds = user.getRoleIds().split(StringPool.COMMA);
                saveUserRole(user.getUserId(),roleIds);
            }
            // 增加部门
            if (StringUtils.isNotBlank(user.getDeptIds())) {
                String[] deptIds = user.getDeptIds().split(StringPool.COMMA);
                saveDept(user.getUserId(),deptIds);
            }
            // 保存菜单对应关系
        } catch (Exception e) {
            e.printStackTrace();
            throw new SystemException("新增用户是不是");
        }
        return user;
    }

    @Override
    public void deleteUser(String ids) {
        try {
            // 删除用户
            String[] idList = ids.split(StringPool.COMMA);
            Long userId = MicroserviceUtil.getCurrentUser().getUserId();
            if(Arrays.asList(idList).contains(String.valueOf(userId))){
                throw new SystemException("删除的用户包含本身，不允许删除");
            }
            userMapper.deleteBatchIds(Arrays.asList(idList));
            // 删除用户角色关系表
            userRoleService.deleteUserRolesByUserId(idList);
            // 删除部门用户中间表
            dataPermissionService.deleteByUserIds(idList);
        } catch (Exception e) {
            e.printStackTrace();
            throw new SystemException("查询用户失败");
        }
    }

    @Override
    public void resetPassword(Long id, String newPassword, String oldPassword) {
        try {
            if(!StringUtils.equals(newPassword,oldPassword)){
                throw new SystemException("新密码不能与旧密码一致");
            }else{
                SystemUser systemUser = userMapper.selectById(id);
                systemUser.setPassword(passwordEncoder.encode(newPassword));
                userMapper.updateById(systemUser);
            }
        }catch (Exception e){
            e.printStackTrace();
            throw new SystemException("更改用户密码失败");
        }
    }

    @Override
    public SystemUser getUser(long id) {
        SystemUser systemUser = null;
        try {
             systemUser = userMapper.selectById(id);
             // TODO 增加角色和 部门等信息
        }catch (Exception e){
            e.printStackTrace();
            throw new SystemException("通过ID得到用户失败");
        }
        return systemUser;
    }

    @Override
    public void success() {
        sendEmail();
        // TODO 记录登录日志
    }
    /**
     * @Description 发送登录邮件
     * @author lijie
     * @createTime 2020-06-11 09:17:33
     * @param
     * @return void
     */
    public void sendEmail(){
        MicroserviceMail rainbowMail = new MicroserviceMail();
        rainbowMail.setFromMailAddress("1649471814@qq.com");
        rainbowMail.setToMailAddress("1649471814@qq.com");
        rainbowMail.setSubject(MicroserviceConstant.LOGIN_MSG);
        rainbowMail.setText("尊贵的用户"+MicroserviceUtil.getCurrentUsername()+"您好，您于"+ LocalDateTime.now() +"登录成功");
        rainbowMailFeign.send(rainbowMail);
    }
    /**
     * @Description 用户部门中间表
     * @author lijie
     * @createTime 2020-05-26 17:21:23
     * @param userId
     * @param deptIds
     * @return void
     */
    private void saveDept(Long userId, String[] deptIds) {
        Arrays.asList(deptIds).forEach(deptId->{
            UserDataPermission   dataPermission = new UserDataPermission();
            dataPermission.setDeptId(Long.valueOf(deptId));
            dataPermission.setUserId(userId);
            userDataPermissionMapper.insert(dataPermission);
        });
    }

    /**
     * @Description 保存中间表
     * @author lijie
     * @createTime 2020-05-26 17:14:30
     * @param userId
     * @param roleIds
     * @return void
     */
    public void saveUserRole(Long userId, String[] roleIds) {
        Arrays.asList(roleIds).forEach(roleId->{
            UserRole userRole = new UserRole();
            userRole.setRoleId(Long.valueOf(roleId));
            userRole.setUserId(userId);
            userRoleMapper.insert(userRole);
        });
    }
}

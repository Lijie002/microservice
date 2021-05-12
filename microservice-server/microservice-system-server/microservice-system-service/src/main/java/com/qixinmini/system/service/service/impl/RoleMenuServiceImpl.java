package com.qixinmini.system.service.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.qixinmini.common.core.model.system.RoleMenu;
import com.qixinmini.system.service.mapper.RoleMenuMapper;
import com.qixinmini.system.service.service.RoleMenuService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 *  @Description 角色菜单中间表
 *  @author lijie
 *  @Date 2020/5/27 14:41
 */
@Service
@Transactional(rollbackFor = Exception.class)
@RequiredArgsConstructor
public class RoleMenuServiceImpl  implements RoleMenuService {

    private final RoleMenuMapper roleMenuMapper;

    @Override
    public void deleteRoleMenusByRoleId(String[] roleIds) {
        LambdaQueryWrapper<RoleMenu> queryWrapper =  new LambdaQueryWrapper<>();
        roleMenuMapper.delete(queryWrapper.in(RoleMenu::getRoleId,roleIds));
    }

    @Override
    public void deleteRoleMenusByMenuId(String[] menuIds) {

    }

    @Override
    public List<RoleMenu> getRoleMenusByRoleId(String roleId) {
        return null;
    }
}

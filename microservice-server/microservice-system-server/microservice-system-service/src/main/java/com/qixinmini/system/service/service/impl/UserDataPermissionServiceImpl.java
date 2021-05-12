package com.qixinmini.system.service.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.qixinmini.common.core.model.system.UserDataPermission;
import com.qixinmini.system.service.exception.SystemException;
import com.qixinmini.system.service.mapper.UserDataPermissionMapper;
import com.qixinmini.system.service.service.UserDataPermissionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 *  @Description 用户部门中间表
 *  @author lijie
 *  @Date 2020/5/27 10:50
 */
@Service
@Transactional(rollbackFor = Exception.class)
@RequiredArgsConstructor
public class UserDataPermissionServiceImpl  implements UserDataPermissionService {

    private final UserDataPermissionMapper dataPermissionMapper;

    @Override
    public void deleteByDeptIds(List<String> deptIds) {

    }

    @Override
    public void deleteByUserIds(String[] userIds) {
        try {
            LambdaQueryWrapper<UserDataPermission> dataPermissionQueryWrapper = new LambdaQueryWrapper<>();
            dataPermissionMapper.delete(dataPermissionQueryWrapper.in(UserDataPermission::getUserId,userIds));
        }catch (Exception e){
            e.printStackTrace();
            throw new SystemException("删除用户部门关联关系失败");
        }
    }

    @Override
    public String findByUserId(String userId) {
        return null;
    }
}

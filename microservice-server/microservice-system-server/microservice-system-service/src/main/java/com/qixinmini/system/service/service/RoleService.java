package com.qixinmini.system.service.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.qixinmini.common.core.model.QueryRequest;
import com.qixinmini.common.core.model.system.Role;
import org.apache.ibatis.annotations.Param;

/**
 *  @Description 角色业务层接口
 *  @author lijie
 *  @Date 2020/5/15 10:15
 */
public interface RoleService {
    /**
     * @Description 角色分页
     * @author lijie
     * @createTime 2020-05-27 11:58:39
     * @param queryRequest
     * @param role
     * @return com.baomidou.mybatisplus.core.metadata.IPage<com.rainbow.common.core.entity.system.Role>
     */
    IPage<Role> page(QueryRequest queryRequest, @Param("role") Role role);

    /**
     * @Description 新增角色
     * @author lijie
     * @createTime 2020-05-27 13:39:12
     * @param role
     * @return com.rainbow.common.core.entity.system.Role
     */
    Role addRole(Role role);

    /**
     * @Description 通过Id得到角色
     * @author lijie
     * @createTime 2020-05-27 14:19:02
     * @param roleId
     * @return com.rainbow.common.core.entity.system.Role
     */
    Role getRoleById(Long roleId);

    /**
     * @Description 删除角色
     * @author lijie
     * @createTime 2020-05-27 14:33:40
     * @param roleIds
     * @return void
     */
    void deleteRole(String roleIds);
}

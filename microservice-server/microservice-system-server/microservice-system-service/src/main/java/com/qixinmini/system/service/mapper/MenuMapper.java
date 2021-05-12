package com.qixinmini.system.service.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.qixinmini.common.core.model.system.Menu;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 *  @Description 菜单业务层实现类
 *  @author lijie
 *  @Date 2020/5/15 10:27
 */
@Repository
public interface MenuMapper extends BaseMapper<Menu> {
    /**
     * @Description
     * @author lijie
     * @createTime 2020-05-15 16:52:26
     * @param username
     * @return java.util.List<com.rainbow.common.core.entity.system.Menu>
     */
    List<Menu> findUserPermissions(String username);
    /**
     * @Description 得到用户所属菜单
     * @author lijie
     * @createTime 2020-05-21 16:57:14
     * @param username
     * @return java.util.List<com.rainbow.common.core.entity.system.Menu>
     */
    List<Menu> selectUserMenu(String username);
}

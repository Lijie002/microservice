package com.qixinmini.oauth.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.qixinmini.common.core.model.system.Menu;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 *  @Description 菜单业务层实现类
 *  @author lijie
 *  @Date 2021/5/11 10:27
 */
@Repository
public interface MenuMapper extends BaseMapper<Menu> {
    /**
     * @Description
     * @author lijie
     * @createTime 2021-05-11 16:52:26
     * @param username
     */
    List<Menu> findUserPermissions(String username);
}

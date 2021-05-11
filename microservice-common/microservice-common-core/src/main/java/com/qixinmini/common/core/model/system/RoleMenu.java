package com.qixinmini.common.core.model.system;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 *  @Description 角色菜单中间表
 *  @author lijie
 *  @Date 2020/11/26 16:45
 */
@TableName("t_role_menu")
@Data
public class RoleMenu implements Serializable {
	
	private static final long serialVersionUID = -7573904024872252113L;

	@TableId(value = "ROLE_ID")
    private Long roleId;
    @TableId(value = "MENU_ID")
    private Long menuId;
}